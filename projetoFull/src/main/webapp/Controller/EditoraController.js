var moduleEditora = angular.module('escoladeti.editora', ['ngRoute', 'escoladeti.validacaoService','escoladeti.pessoaService']);

moduleEditora.controller('EditoraController', ['$scope', '$http', '$rootScope', '$routeParams','growl','validacaoService', 'pessoaService',function($scope, $http, $rootScope, $routeParams, growl,validacaoService, pessoaService) {

        $scope.message = 'Controller dos Editoras!';
        $scope.page = {pageNumber: 0};
        $scope.pessoa = undefined;
        $scope.search = "";
        $scope.nome = "Editoras";
        $scope.form = "Editoras";
        $scope.caminholistar = "./View/Pessoa/Editora/listarEditora.html";
        $scope.caminhoeditar = "./View/Pessoa/Editora/editarEditora.html";
        $scope.caminhoinicial = "#/Pessoa/Editora/";
        $scope.caminhorelatorio = "editora/relatorio/listaRelatorio";
        $scope.displayBtnRelatorio = "inline";
        $scope.listatipos = [];
        $scope.telefones = [];
        $scope.emails = [];
        $scope.listaBairro = [];
        $scope.listaLogradouro = [];
        $scope.enderecos = [];
        $scope.listaCidade = [];
        $scope.listaEstados = [];
        $scope.listaPaises = [];
        $scope.editora = [];

        $scope.novoEmail = function() {
            $scope.emails.email = "";
        };

        $scope.novoTelefone = function() {
            $scope.telefones.numero = "";
            $scope.telefones.tipotelefone = [];
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
        $scope.novoEndereco = function() {
            $scope.enderecos.logradouro = [];
            $scope.enderecos.bairro = [];
            $scope.enderecos.numero = "";
            $scope.enderecos.complemento = "";
            $scope.enderecos.cep = "";
            $scope.enderecos.cidade = [];
            $scope.enderecos.uf = [];
            $scope.enderecos.pais = [];
        };

        $scope.validaTelefone = function() {
            return $scope.telefones.numero && $scope.telefones.tipotelefone.id;
        };

        $scope.validaEndereco = function() {
            return  ($scope.enderecos.logradouro !== undefined ? $scope.enderecos.logradouro.id : "") &&
                    $scope.enderecos.bairro.id &&
                    $scope.enderecos.numero &&
                    $scope.enderecos.cep &&
                    $scope.enderecos.cidade.id &&
                    $scope.enderecos.uf.id &&
                    $scope.enderecos.pais.id;
        };

        $scope.editarTelefone = function(index) {
            $scope.telefones = $scope.pessoa.telefones[index];
            $('#modalTelefone').modal('show');
            $scope.pessoa.telefones.splice(index, 1);
        };
        
        $scope.delTelefone = function(index) {
            $scope.pessoa.telefones.splice(index, 1);
        };

        $scope.addTelefone = function() {
            if(!validacaoService.verificarSeExisteTelefoneIguais($scope.pessoa.telefones, $scope.telefones)){
                $scope.pessoa.telefones.push({numero: $scope.telefones.numero, tipotelefone: $scope.telefones.tipotelefone});
            }
        };
        $scope.loadTodosTipo = function() {
            $http({
                method: "GET",
                url: "./tipotelefone/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listatipos = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar os Tipos de Telefone!");
            });
        };

        $scope.editarEmail = function(index) {
            $scope.emails = $scope.pessoa.emails[index];
            $('#modalEmail').modal('show');
            $scope.pessoa.emails.splice(index, 1);
        };
        $scope.delEmail = function(index) {
            $scope.pessoa.emails.splice(index, 1);
        };
        $scope.addEmail = function() {
            if(!validacaoService.verificarSeExisteEmailsIguais($scope.pessoa.emails, $scope.emails)){
                $scope.pessoa.emails.push({email: $scope.emails.email});
            }
        };

        $scope.removerEndereco = function(index) {
            $scope.pessoa.enderecos.splice(index, 1);
        };

        $scope.editarEndereco = function(index) {
            $scope.enderecos = $scope.pessoa.enderecos[index];
            $scope.procurarUfPorIdPais($scope.enderecos.pais.id);
            $scope.procurarCidadePorIdUf($scope.enderecos.uf.id);
            $scope.procurarBairroPorIdCidade($scope.enderecos.cidade.id);
            $scope.procurarLogradouroPorIdBairro($scope.enderecos.bairro.id);            
            
            $('#modalEndereco').modal('show');
            $scope.pessoa.enderecos.splice(index, 1);
        };

        $scope.adicionarEndereco = function() {
            if(!validacaoService.verificarSeExisteEnderecosIguais($scope.pessoa.enderecos, $scope.enderecos)){
                $scope.pessoa.enderecos.push(
                    {
                        logradouro: $scope.enderecos.logradouro,
                        bairro: $scope.enderecos.bairro,
                        numero: $scope.enderecos.numero,
                        complemento: $scope.enderecos.complemento,
                        cep: $scope.enderecos.cep,
                        cidade: $scope.enderecos.cidade,
                        uf: $scope.enderecos.uf,
                        pais: $scope.enderecos.pais
                    }
                );
            }
        };
        
        $scope.procurarBairroPorIdCidade = function(id) {
            $http({
                method: "GET",
                url: "./bairro/procurarBairroPorIdCidade/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listaBairro = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar os Bairros!");
            });
        };

        $scope.procurarCidadePorIdUf = function(id) {
            $http({
                method: "GET",
                url: "./cidade/procurarCidadePorIdUf/"+ id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listaCidade = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar as Cidades!");
            });
        };

        $scope.procurarLogradouroPorIdBairro = function(id) {
            $http({
                method: "GET",
                url: "./logradouro/procurarLogradouroPorIdBairro/"+id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listaLogradouro = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar os Logradouros!");
            });
        };

        $scope.procurarUfPorIdPais = function(id) {
            $http({
                method: "GET",
                url: "./unidadefederativa/findbyCodigoPais/"+id,                
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(status));
                $scope.listaEstados = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar os Estados!");
            });
        };

        $scope.loadTodosPaises = function() {
            $http({
                method: "GET",
                url: "./pais/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listaPaises = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar os Países!");
            });
        };
 

        $scope.incluir = function() {
            window.location.href = "#Pessoa/Editora/Novo/Editar";
        };
        $scope.init = function() {
            $scope.load(0);
            $scope.loadTodosTipo();
            $scope.loadTodosPaises();   
        };
        
        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.pessoa = {
                    id: "",
                    telefones: [],
                    emails: [],
                    enderecos: []
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.editoraDadoId($routeParams.objeto);
            }
            $scope.loadTodosTipo();
            $scope.loadTodosPaises(); 
        };

        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.pessoa;
            } else {
                var excluir = $scope.page.list[index];
                $scope.editora = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'razao');
        };

        $scope.removerServidor = function(editora) {
            $http({
                method: "DELETE",
                url: "./editora/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(editora))
            }).success(function(data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function(data, status) {
                if (status == 500) {
                   growl.error("Não foi possível excluir a Editora. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir a Editora!");
                }
            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./editora/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = formatPageList(data);
            }).error(function(data, status) {
                growl.error("Não foi possível listar as Editoras!");
            });
        };
        $scope.consultaCnpj = function(cnpj) {
            $http({
                method: "GET",
                url: "./editora/consulta/" + cnpj,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                if (data.cnpj == $scope.pessoa.cnpj) {
                    $scope.Editoras.cnpj.$setValidity('cnpjcadastrado', false);
                } else {
                    $scope.Editoras.cnpj.$setValidity('cnpjcadastrado', true);
                }
            });
        };
        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./editora/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson($scope.pessoa)
            }).success(function(data, status) {
                window.location.href = "#Pessoa/Editora/";
                console.log(JSON.stringify(status));
                growl.success("Cadastro efetuado com sucesso!");
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
            });
        };

        $scope.editoraDadoId = function(id) {
            $http({
                method: "GET",
                url: "./editora/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.pessoa = data;
            }).error(function(data, status) {
                $("<div></div>")
                        .css({overflow: "auto"})
                        .html(data)
                        .dialog({
                            autoOpen: true,
                            title: 'Erro ao carregar questão ' + id,
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
                    url: "./editora/procurarPorNome/" + nome,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function(data, status) {
                    $scope.page = data;
                }).error(function(data, status) {
                    growl.info("Não foi possível localizar a Editora!");
                });
            }else{
                $scope.load(0);
            }                        
        };        

    }
]);
