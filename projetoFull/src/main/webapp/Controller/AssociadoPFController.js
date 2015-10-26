var moduleAssociadoPF = angular.module('escoladeti.associadopf', ['ngRoute','escoladeti.pessoaService']);

moduleAssociadoPF.controller('AssociadoPFController', ['$scope', '$http', '$rootScope', '$routeParams','pessoaService', function($scope, $http, $rootScope, $routeParams, pessoaService) {

        $scope.message = 'Controller dos Associados Pessoa Física!';
        $scope.page = {pageNumber: 0};
        $scope.associadoPFAtual = undefined;
        $scope.search = "";
        $scope.listaPessoaFisica = [];
        $scope.nome = "Associado Pessoa Física";
        $scope.form = "AssociadoPF";
        $scope.caminholistar = "./View/Pessoa/AssociadoPF/listarAssociadoPF.html";
        $scope.caminhoeditar = "./View/Pessoa/AssociadoPF/editarAssociadoPF.html";
        $scope.caminhoinicial = "#/Pessoa/AssociadoPF/";
        $scope.caminhorelatorio = "associadopf/relatorio/listaRelatorio";
        $scope.displayBtnRelatorio = "inline";
        $scope.associadoPF = [];

        $scope.incluir = function() {
            window.location.href = "#Pessoa/AssociadoPF/Novo/Editar";
        };
        $scope.init = function() {
            $scope.load(0);
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
                $scope.associadoPFAtual = {
                    id: "",
                    ativo: "",
                    valorContribuicao: "",
                    pessoaFisica: ""

                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.associadoDadoId($routeParams.objeto);
            }
            $scope.loadTodasPessoasFisicas();
        };

        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.associadoPFAtual;
            } else {
                var excluir = $scope.page.list[index];
                $scope.associadoPF = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'id');
        };

        $scope.loadTodasPessoasFisicas = function() {
            $http({
                method: "GET",
                url: "./pessoafisica/todas",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listaPessoaFisica = data;
            }).error(function(data, status) {
                alert("Erro ao buscar Pessoas Fisicas!");
            });
        };

        $scope.removerServidor = function(associado) {
            $http({
                method: "DELETE",
                url: "./associadopf/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(associado))
            }).success(function(data, status) {
                window.location.href = $scope.caminhoinicial;
            }).error(function(data, status) {
                alert("Não foi possível excluir o registro!");

            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./associadopf/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = data;
            }).error(function(data, status) {
                alert("Erro ao buscar Associado!");
            });
        };


        $scope.salvar = function() {
            var objectVO = {
                id: $scope.associadoPFAtual.id,
                ativo: $scope.associadoPFAtual.ativo,
                valorContribuicao: $scope.associadoPFAtual.valorContribuicao,
                idPessoaFisica: $scope.associadoPFAtual.pessoaFisica.id
            };

            $http({
                method: "POST",
                url: "./associadopf/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(objectVO)
            }).success(function(data, status) {
                window.location.href = $scope.caminhoinicial;
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao Salvar Associado!" + data + status);
            });
        };

        $scope.associadoDadoId = function(id) {
            $http({
                method: "GET",
                url: "./associadopf/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.associadoPFAtual = data;
            }).error(function(data, status) {
                $("<div></div>")
                        .css({overflow: "auto"})
                        .html(data)
                        .dialog({
                            autoOpen: true,
                            title: 'Erro ao carregar Associado ' + id,
                            width: 800,
                            height: 450,
                            modal: true
                        });
            });
        };

//        $scope.procurarPorNome = function(nome) {
//            if(nome != ''){                                 
//                $http({
//                    method: "GET",
//                    url: "./associadopf/procurarPorNome/" + nome,
//                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
//                }).success(function(data, status) {
//                    $scope.page = data;
//                }).error(function(data, status) {
//                    alert("Erro ao buscar Associado!");
//                });
//            }else{
//                $scope.load(0);
//            }                        
//        };          

    }
]);
