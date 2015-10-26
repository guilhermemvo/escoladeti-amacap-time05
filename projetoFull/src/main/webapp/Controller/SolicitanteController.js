var moduleSolicitante = angular.module('escoladeti.solicitante', ['ngRoute','escoladeti.solicitanteService', 'escoladeti.validacaoService','escoladeti.pessoaService']);
moduleSolicitante.controller('SolicitanteController', ['$scope', '$http', '$rootScope', '$routeParams', 'solicitanteService','validacaoService', 'pessoaService',function($scope, $http, $rootScope, $routeParams, solicitanteService, validacaoService, pessoaService) {

        $scope.message = 'Controller dos Solicitante!';
        $scope.page = {pageNumber: 0};
        $scope.pessoa = undefined;
        $scope.search = "";
        $scope.nome = "Solicitante";
        $scope.form = "solicitante";
        $scope.caminholistar = "./View/Pessoa/Solicitante/listarSolicitante.html";
        $scope.caminhoeditar = "./View/Pessoa/Solicitante/editarSolicitante.html";
        $scope.caminhoinicial = "#/Pessoa/Solicitante/";
        $scope.caminhorelatorio = "solicitante/relatorio/listaRelatorio";
        $scope.displayBtnRelatorio = "inline";
        $scope.listatipos = [];
        $scope.telefones = [];
        $scope.listaBairro = [];
        $scope.emails = [];
        $scope.listaLogradouro = [];
        $scope.enderecos = [];
        $scope.listaCidade = [];
        $scope.listaEstados = [];
        $scope.listaPaises = [];

        
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
                alert("Erro ao buscar os tipos");
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
                alert("Erro ao buscar Bairros!");
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
                alert("Erro ao buscar Cidades!");
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
                alert("Erro ao buscar Logradouros!");
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
                alert("Erro ao buscar Estados");
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
                alert("Erro ao buscar Paises!");
            });
        };
  
        $scope.incluir = function() {
            window.location.href = "#Pessoa/Solicitante/Novo/Editar";
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
                    enderecos: [],
                    emails: []
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.SolicitanteDadoId($routeParams.objeto);
            }
            $scope.loadTodosTipo();
            $scope.loadTodosPaises();                     
        };
                
        $scope.consultaCpf = function(cpf) {
        };

        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.pessoa;
            } else {
                var excluir = $scope.page.list[index];
                $scope.solicitante = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'nome');
        };
        $scope.removerServidor = function(solicitante) {
            solicitanteService.removeSolicitante(solicitante);
        };

        $scope.load = function(numeroDaPagina) {
            solicitanteService
                    .getPageSolicitante(numeroDaPagina)
                    .success(function(data){                        
                        $scope.page = formatPageList(data);
                    });
        };

        $scope.salvar = function() {
            solicitanteService.saveSolicitante($scope.pessoa);
            window.location.href = "#Pessoa/Solicitante/";
        };

        $scope.SolicitanteDadoId = function(id) {
            solicitanteService
                    .findSolicitanteById(id)
                    .success(function(data){
                        $scope.pessoa = data;
                    });
        };
        
        $scope.procurarPorNome = function(nome) {
            if(nome !== ''){
                solicitanteService
                        .findSolicitanteByName(nome)                 
                        .success(function(data){
                            $scope.page = data;
                        });
            }
            else {
                $scope.load(0);
            }
        };    
        
    }
]);
