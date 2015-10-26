var moduleUsuario = angular.module('escoladeti.usuario', ['ngRoute','escoladeti.pessoaService']);

moduleUsuario.controller('UsuarioController', ['$scope', '$http', '$rootScope','$routeParams','growl', 'pessoaService',function($scope, $http,$rootScope, $routeParams, growl, pessoaService) {

        $scope.message = 'Controller dos Usuários!';
        $scope.page = {pageNumber: 0};
        $scope.pessoa = undefined;
        $scope.listafuncionario = [];
        $scope.listaperfilacesso = [];
        $scope.search = "";
        $scope.confirmarSenha;
        $scope.nome = "Usuário";
        $scope.form = "usuario";
        $scope.caminholistar = "./View/Pessoa/Usuario/listarUsuario.html";
        $scope.caminhoeditar = "./View/Pessoa/Usuario/editar.html";
        $scope.caminhoinicial = "#/Pessoa/Usuario/";
        $scope.displayBtnRelatorio = "none";
        $scope.usuario = [];
        
        $scope.init = function() {
            $scope.load(1);
        };

        $scope.incluir = function() {
            window.location.href = "#Pessoa/Usuario/Novo/Editar";
        };
$scope.encontraEnderecoPorCEP = function (value) {
            $scope.enderecos;
            pessoaService.getLogradouroCep(value)
                    .success(function (data) {
                        $scope.loadTodosPaises();
                        $scope.enderecos.pais = data.cidade.unidadefederativa.pais;
                        $scope.procurarUfPorIdPais(data.cidade.unidadefederativa.pais.id);
                        $scope.enderecos.uf = data.cidade.unidadefederativa;
                        $scope.procurarCidadePorIdUf(data.cidade.unidadefederativa.id);
                        $scope.enderecos.cidade = data.cidade;
                        $scope.procurarBairroPorIdCidade(data.cidade.id);
                        $scope.enderecos.bairro = data.bairro;
                        $scope.procurarLogradouroPorIdBairro(data.bairro.id);
                        $scope.enderecos.tipologradouro = data.tipologradouro;
                        $scope.enderecos.logradouro = data;
                        console.log($scope.enderecos);
                    });
        };
        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.pessoa = {
                    id: "",
                    login: "",
                    senha: "",
                    ativo: "",
                    perfilAcesso:""
                };
                $scope.confirmarSenha = "";
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.usuarioDadoId($routeParams.objeto);
            }
            $scope.loadTodasFuncionarios();
            $scope.loadTodosPerfilAcesso();
        };
            
        $scope.loadTodasFuncionarios = function() {
            $http({
                method: "GET",
                url: "./funcionario/todas",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(status));
                $scope.listafuncionario = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar os Funcionários!");
            });
        };
        
        $scope.loadTodosPerfilAcesso = function() {
            $http({
                method: "GET",
                url: "./perfilAcesso/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(status));
                $scope.listaperfilacesso = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar os Perfis de Acesso!");
            });
        };
        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.pessoa;
            } else {
                var excluir = $scope.page.list[index];
                $scope.usuario = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'login');
        };
        $scope.removerServidor = function(usuario) {
            console.log(JSON.stringify(usuario));
            $http({
                method: "DELETE",
                url: "./usuario/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(usuario))
            }).success(function(data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(1);
            }).error(function(data, status) {
                if (status == 500) {
                    growl.error("Não foi possível excluir Usuário. O cadastro possui outras referências no sistema!");
      
                } else {
                    growl.error("Não foi possível excluir o Usuário!");
                }
            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./usuario/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar os Usuários!");
            });
        };
       $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./usuario/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.pessoa)
            }).success(function(data, status) {
                window.location.href = "#Pessoa/Usuario/";
                console.log(JSON.stringify(status));
                growl.success("Cadastro efetuado com sucesso!");
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
            });
        };
        
        $scope.usuarioDadoId = function(id) {
            $http({
                method: "GET",
                url: "./usuario/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.pessoa = data;
                $scope.confirmarSenha = $scope.pessoa.senha;
            }).error(function(data, status) {
                $("<div></div>")
                        .css({overflow: "auto"})
                        .html(data)
                        .dialog({
                            autoOpen: true,
                            title: 'Erro ao carregar usuário ' + id,
                            width: 800,
                            height: 450,
                            modal: true
                        });
            });
        };

        $scope.procurarPorNome = function(nome) {
            if(nome != ''){                                 
                $http({
                    method: "GET",
                    url: "./usuario/procurarPorNome/" + nome,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function(data, status) {
                    $scope.page = data;
                }).error(function(data, status) {
                   growl.info("Não foi possível localizar o Usuário!");
                });
            }else{
                $scope.load(1);
            }                        
        };
    }
]);