var moduleCidade = angular.module('escoladeti.cidade', ['ngRoute']);

moduleCidade.controller('CidadeController', ['$scope', '$http', '$rootScope', '$routeParams', 'growl', function($scope, $http, $rootScope, $routeParams, growl) {

        $scope.message = 'Controller das Cidades!';
        $scope.page = {pageNumber: 0};
        $scope.cidadeAtual = undefined;
        $scope.search = "";
        $scope.listaestados = [];
        $scope.nome = "Cidade";
        $scope.form = "Cidade";
        $scope.caminholistar = "./View/Localizacao/Cidade/listarCidade.html";
        $scope.caminhoeditar = "./View/Localizacao/Cidade/editarCidade.html";
        $scope.caminhoinicial = "#/Localizacao/Cidade/";
        $scope.displayBtnRelatorio = "none";
        $scope.cidade = [];

        $scope.incluir = function() {
            window.location.href = "#Localizacao/Cidade/Novo/Editar";
        };
        $scope.init = function() {
            $scope.load(0);
        };

        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.cidadeAtual = {
                    id: "",
                    unidadefederativa: "",
                    nome: "",
                    dataFundacao: "",
                    codigoIBGE: ""
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.cidadeDadoId($routeParams.objeto);
            }
            $scope.loadTodosEstados();
        };

        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.cidadeAtual;
            } else {
                var excluir = $scope.page.list[index];
                $scope.cidade = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'nome');
        };

        $scope.loadTodosEstados = function() {
            $http({
                method: "GET",
                url: "./unidadefederativa/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(status));
                $scope.listaestados = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar os Estados!");
            });
        };

        $scope.removerServidor = function(cidade) {
            console.log(JSON.stringify(cidade));
            $http({
                method: "DELETE",
                url: "./cidade/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(cidade))
            }).success(function(data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function(data, status) {
                if (status == 500) {
                    growl.error("Não foi possível excluir a Cidade. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir a Cidade!");
                }

            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./cidade/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar as Cidades!");
            });
        };


        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./cidade/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.cidadeAtual)
            }).success(function(data, status) {
                if (data === "OK") {
                    window.location.href = $scope.caminhoinicial;
                    console.log(JSON.stringify(status));
                    growl.success("Cadastro efetuado com sucesso!");
                } else {
                    growl.info("Código do IBGE já cadastrado!");
                    $scope.estadoAtual.codigoIBGE = "";
                }
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
            });
        };


        $scope.cidadeDadoId = function(id) {
            $http({
                method: "GET",
                url: "./cidade/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.cidadeAtual = data;
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
                    url: "./cidade/procurarPorNome/" + nome,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function(data, status) {
                    $scope.page = data;
                }).error(function(data, status) {
                    growl.info("Não foi possível localizar a Cidade!");
                });
            } else {
                $scope.load(0);
            }
        };

    }
]);
