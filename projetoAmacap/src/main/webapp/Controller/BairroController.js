var moduleBairro = angular.module('escoladeti.bairro', ['ngRoute']);

moduleBairro.controller('BairroController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

        $scope.message = 'Controller das bairro!';
        $scope.page = {pageNumber: 0};
        $scope.bairroAtual = undefined;
        $scope.search = "";
        $scope.listaCidade = [];
        $scope.nome = "Bairro";
        $scope.caminholistar = "./View/Localizacao/Bairro/listarBairro.html";
        $scope.caminhoeditar = "./View/Localizacao/Bairro/editarBairro.html";
        $scope.caminhoinicial = "#/Localizacao/Bairro/";


        $scope.init = function() {
            $scope.loadBairro($scope.page.pageNumber);
        };

        $scope.incluir = function() {
            window.location.href = "#Localizacao/Bairro/Novo/Editar";
        };

        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.bairroAtual = {
                    id: "",
                    nome: "",
                    cidade: ""
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.bairroDadoId($routeParams.objeto);
            }
            $scope.loadTodasCidades();
        };

        $scope.remover = function(index) {
            var bairro = $scope.page.list[index];
            if (confirm('Deseja excluir a bairro ' + bairro.nome + '?')) {
                $scope.removerServidor(bairro);
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


        $scope.removerServidor = function(bairro) {
            console.log(JSON.stringify(bairro));
            $http({
                method: "DELETE",
                url: "./bairro/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(bairro))
            }).success(function(data, status) {
                $scope.loadBairro($scope.page.pageNumber);
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir bairro!");
            });
        };

        $scope.loadBairro = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./bairro/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.page = data;
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar bairro!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./bairro/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.bairroAtual)
            }).success(function(data, status) {
                window.location.href = "#Localizacao/Bairro";
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao salvar bairro!");
            });
        };

        $scope.bairroDadoId = function(id) {
            $http({
                method: "GET",
                url: "./bairro/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.bairroAtual = data;
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
