var moduleDistrito = angular.module('escoladeti.distrito', ['ngRoute']);

moduleDistrito.controller('DistritoController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

        $scope.message = 'Controller das Distritos!';
        $scope.page = {pageNumber: 0};
        $scope.distritoAtual = undefined;
        $scope.search = "";
        $scope.listacidades = [];

        $scope.init = function() {
            $scope.loadDistritos($scope.page.pageNumber);
        };

        $scope.incluir = function() {
            window.location.href = "#Localizacao/Distrito/Novo";
        };

        $scope.initEditarDistrito = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.distritoAtual = {
                    id: "",
                    cidade: "",
                    nome: "",
                    inicioVigencia: ""
                };
            } else {
                $scope.distritoDadoId($routeParams.objeto);
            }
            $scope.loadTodosCidades();
        };

        $scope.remover = function(index) {
            var distrito = $scope.page.list[index];
            if (confirm('Deseja excluir a distrito ' + distrito.nome + '?')) {
                $scope.removerServidor(distrito);
            }
        };
        $scope.loadTodosCidades = function() {
            $http({
                method: "GET",
                url: "./cidade/todas",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(status));
                $scope.listacidades = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar Cidades");
            });
        };

        $scope.removerServidor = function(distrito) {
            console.log(JSON.stringify(distrito));
            $http({
                method: "DELETE",
                url: "./distrito/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(distrito))
            }).success(function(data, status) {
                $scope.loadDistritos($scope.page.pageNumber);
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir distritos!");
            });
        };

        $scope.loadDistritos = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./distrito/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.page = data;
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar Distritos!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./distrito/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.distritoAtual)
            }).success(function(data, status) {
                window.location.href = "#Localizacao/Distrito";
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir distritos!");
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

    }
]);
