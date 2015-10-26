var moduleEstado = angular.module('escoladeti.estado', ['ngRoute']);

moduleEstado.controller('EstadoController', ['$scope', '$http', '$rootScope', '$routeParams', 'growl', function($scope, $http, $rootScope, $routeParams, growl) {

        $scope.message = 'Controller dos Estados!';
        $scope.page = {pageNumber: 0};
        $scope.estadoAtual = undefined;
        $scope.search = "";
        $scope.listapaises = [];
        $scope.nome = "Estado";
        $scope.form = "Estado";
        $scope.caminholistar = "./View/Localizacao/Estado/listarEstado.html";
        $scope.caminhoeditar = "./View/Localizacao/Estado/editarEstado.html";
        $scope.caminhoinicial = "#/Localizacao/Estado/";
        $scope.displayBtnRelatorio = "none";
        $scope.estado = [];

        $scope.incluir = function() {
            window.location.href = "#Localizacao/Estado/Novo/Editar";
        };
        $scope.init = function() {
            $scope.load(0);
        };
        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.estadoAtual = {
                    id: "",
                    nome: "",
                    codigoIBGE: "",
                    sigla: "",
                    pais: ""
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.estadoDadoId($routeParams.objeto);
            }
            $scope.loadTodosPaises();
        };

        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.estadoAtual;
            } else {
                var excluir = $scope.page.list[index];
                $scope.estado = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'nome');
        };

        $scope.loadTodosPaises = function() {
            $http({
                method: "GET",
                url: "./pais/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listapaises = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar os Países!");
            });
        };

        $scope.removerServidor = function(estado) {
            $http({
                method: "DELETE",
                url: "./unidadefederativa/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(estado))
            }).success(function(data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function(data, status) {
                if (status == 500) {
                    growl.error("Não foi possível excluir o Estado. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir o Estado!");
                }
            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./unidadefederativa/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar os Estados!");
            });
        };


        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./unidadefederativa/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.estadoAtual)
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

        $scope.estadoDadoId = function(id) {
            $http({
                method: "GET",
                url: "./unidadefederativa/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.estadoAtual = data;
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
                    url: "./unidadefederativa/procurarPorNome/" + nome,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function(data, status) {
                    $scope.page = data;
                }).error(function(data, status) {
                    growl.error("Não foi possível listar os Estados!");
                });
            } else {
                $scope.load(0);
            }
        };

    }
]);
