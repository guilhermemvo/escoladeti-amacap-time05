var moduleDistrito = angular.module('escoladeti.distrito', ['ngRoute']);

moduleDistrito.controller('DistritoController', ['$scope', '$http', '$rootScope', '$routeParams', 'growl', function($scope, $http, $rootScope, $routeParams, growl) {

        $scope.message = 'Controller das Distritos!';
        $scope.page = {pageNumber: 0};
        $scope.distritoAtual = undefined;
        $scope.search = "";
        $scope.listacidades = [];
        $scope.nome = "Distrito";
        $scope.form = "Distrito";
        $scope.caminholistar = "./View/Localizacao/Distrito/listarDistrito.html";
        $scope.caminhoeditar = "./View/Localizacao/Distrito/editarDistrito.html";
        $scope.caminhoinicial = "#/Localizacao/Distrito/";
        $scope.displayBtnRelatorio = "none";
        $scope.distrito = [];

        $scope.incluir = function() {
            window.location.href = "#Localizacao/Distrito/Novo/Editar";
        };
        $scope.init = function() {
            $scope.load(0);
        };

        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.distritoAtual = {
                    id: "",
                    cidade: "",
                    nome: "",
                    inicioVigencia: undefined
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.distritoDadoId($routeParams.objeto);
            }
            $scope.loadTodosCidades();
        };

        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.distritoAtual;
            } else {
                var excluir = $scope.page.list[index];
                $scope.distrito = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'nome');
        };

        $scope.loadTodosCidades = function() {
            $http({
                method: "GET",
                url: "./cidade/todas",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listacidades = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar as Cidades!");
            });
        };

        $scope.removerServidor = function(distrito) {
            $http({
                method: "DELETE",
                url: "./distrito/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(distrito))
            }).success(function(data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function(data, status) {
                if (status == 500) {
                    growl.error("Não foi possível excluir o Distrito. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir o Distrito!");
                }
            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./distrito/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                //console.log(JSON.stringify(data));
                $scope.page = formatPageList(data);
                // console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar os Distritos!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./distrito/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson($scope.distritoAtual)
            }).success(function(data, status) {
                window.location.href = $scope.caminhoinicial;
                console.log(JSON.stringify(status));
                growl.success("Cadastro efetuado com sucesso!");

                //window.location.href = "#Localizacao/Distrito";
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
                //console.log($scope.distritoAtual);
                //alert("Erro ao salvar distritos!");
            });
        };

        $scope.distritoDadoId = function(id) {
            $http({
                method: "GET",
                url: "./distrito/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.distritoAtual = data;
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
                    url: "./distrito/procurarPorNome/" + nome,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function(data, status) {
                    $scope.page = data;
                }).error(function(data, status) {
                    growl.info("Não foi possível localizar o Distrito!");
                });
            } else {
                $scope.load(0);
            }
        };
    }
]);
