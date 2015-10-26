var moduleLogradouro = angular.module('escoladeti.logradouro', ['ngRoute']);

moduleLogradouro.controller('LogradouroController', ['$scope', '$http', '$routeParams',  function($scope, $http, $routeParams) {

        $scope.message = 'Controller das logradouro!';
        $scope.page = {pageNumber: 0};
        $scope.logradouroAtual = undefined;
        $scope.search = "";
        $scope.listaCidade = [];
        $scope.listaBairro = [];
        $scope.listaTipoEndereco = [];

        $scope.init = function() {
            $scope.loadLogradouro($scope.page.pageNumber);
        };

        $scope.incluir = function() {
            window.location.href = "#Localizacao/Logradouro/Novo";
        };

        $scope.initEditarLogradouro = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.logradouroAtual = {
                    id: "",
                    nome: "",
                    cidade: "",
                    bairros:"",
                    tipologradouro:""
                    
                };
            } else {
                $scope.logradouroDadoId($routeParams.objeto);
            }
            $scope.loadTodasCidades();
            $scope.loadTodosBairros();
            $scope.loadTodosTipoLogradouro();
        };

        $scope.remover = function(index) {
            var logradouro = $scope.page.list[index];
            if (confirm('Deseja excluir a logradouro ' + logradouro.nome + '?')) {
                $scope.removerServidor(logradouro);
            }
        };
        $scope.loadTodasCidades = function() {
            $http({
                method: "GET",
                url: "./cidade/todas",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(status));
                $scope.listaCidade = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));                
                alert("Erro ao buscar Cidades");
            });
        };
        $scope.loadTodosBairros = function() {
            $http({
                method: "GET",
                url: "./bairro/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(status));
                $scope.listaBairro = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar Bairro");
            });
        };      
        $scope.loadTodosTipoLogradouro = function() {
            $http({
                method: "GET",
                url: "./tipologradouro/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(status));
                console.log(JSON.stringify(data));
                $scope.listaTipoEndereco = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar Tipo logradouro");
            });
        };         


        $scope.removerServidor = function(logradouro) {
            console.log(JSON.stringify(logradouro));
            $http({
                method: "DELETE",
                url: "./logradouro/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(logradouro))
            }).success(function(data, status) {
                $scope.loadLogradouro($scope.page.pageNumber);
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir logradouro!");
            });
        };

        $scope.loadLogradouro = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./logradouro/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.page = data;
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));                
                alert("Erro ao buscar logradouro!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./logradouro/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.logradouroAtual)
            }).success(function(data, status) {
                window.location.href = "#Localizacao/Logradouro";
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));                
                alert("Erro ao salvar logradouro!");
            });
        };

        $scope.logradouroDadoId = function(id) {
            $http({
                method: "GET",
                url: "./logradouro/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
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


    }
]);
