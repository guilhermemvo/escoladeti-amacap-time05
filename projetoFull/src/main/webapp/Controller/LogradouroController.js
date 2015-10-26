var moduleLogradouro = angular.module('escoladeti.logradouro', ['ngRoute']);

moduleLogradouro.controller('LogradouroController', ['$scope', '$http', '$rootScope', '$routeParams', 'growl', function($scope, $http, $rootScope, $routeParams, growl) {

        $scope.message = 'Controller dos Logradouros!';
        $scope.page = {pageNumber: 0};
        $scope.logradouroAtual = undefined;
        $scope.search = "";
        $scope.listaBairro = [];
        $scope.listaCidade = [];
        $scope.listaTipoLogradouro = [];
        $scope.nome = "Logradouro";
        $scope.form = "logradouro";
        $scope.caminholistar = "./View/Localizacao/Logradouro/listar.html";
        $scope.caminhoeditar = "./View/Localizacao/Logradouro/editar.html";
        $scope.caminhoinicial = "#/Localizacao/Logradouro/";
        $scope.displayBtnRelatorio = "none";
        $scope.logradouro = [];

        $scope.incluir = function() {
            window.location.href = "#Localizacao/Logradouro/Novo/Editar";
        };
        $scope.init = function() {
            $scope.load(0);
        };
        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.logradouroAtual = {
                    id: "",
                    nome: "",
                    cidade: "",
                    bairro: "",
                    tipoLogradouro: ""
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.logradouroDadoId($routeParams.objeto);
            }
            $scope.loadTodosBairros();
            $scope.loadTodasCidades();
            $scope.loadTodosTipoLogradouro();
        };

        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.logradouroAtual;
            } else {
                var excluir = $scope.page.list[index];
                $scope.logradouro = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'nome');
        };

        $scope.loadTodosBairros = function() {
            $http({
                method: "GET",
                url: "./bairro/todos",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                }
            }).success(function(data, status) {
                $scope.listaBairro = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar os Bairros!");
            });
        };

        $scope.loadTodosTipoLogradouro = function() {
            $http({
                method: "GET",
                url: "./tipologradouro/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            })
                    .success(function(data, status) {
                        $scope.listaTipoLogradouro = data;
                    })
                    .error(function(data, status) {
                        growl.error("Não foi possível listar os Tipos de Logradouro!");
                    });
        }

        $scope.loadTodasCidades = function() {
            $http({
                method: "GET",
                url: "./cidade/todas",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                }
            }).success(function(data, status) {
                $scope.listaCidade = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar as Cidades!");
            });
        };

        $scope.removerServidor = function(logradouro) {
            $http({
                method: "DELETE",
                url: "./logradouro/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(logradouro))
            })
                    .success(function(data, status) {
                        growl.success("Cadastro excluído com sucesso!");
                        $scope.load(0);
                    }).error(function(data, status) {
                if (status == 500) {
                    growl.error("Não foi possível excluir o Logradouro. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir o Logradouro!");
                }
            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./logradouro/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar os logradouros!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./logradouro/salvar",
                headers: {
                    'Content-Type': 'application/json; charset=UTF-8'
                },
                data: JSON.stringify($scope.logradouroAtual)
            })
                    .success(function(data, status) {
                        window.location.href = "#Localizacao/Logradouro/";
                        growl.success("Cadastro efetuado com sucesso!");
                    })
                    .error(function(data, status) {
                        console.log(JSON.stringify(data));
                        console.log(JSON.stringify(status));
                        growl.error("Não foi possível efetuar o cadastro!");
                    });
        };

        $scope.logradouroDadoId = function(id) {
            $http({
                method: "GET",
                url: "./logradouro/carregar/" + id,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                }
            }).success(function(data, status) {
                $scope.logradouroAtual = data;
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
            if (nome != '') {
                $http({
                    method: "GET",
                    url: "./logradouro/procurarPorNome/" + nome,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function(data, status) {
                    $scope.page = data;
                }).error(function(data, status) {
                    growl.info("Não foi possível localizar Logradouro!");
                });
            } else {
                $scope.load(0);
            }
        };

    }]);
