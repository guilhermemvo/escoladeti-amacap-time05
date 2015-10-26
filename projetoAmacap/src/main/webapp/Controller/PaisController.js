var modulePais = angular.module('escoladeti.pais', ['ngRoute']);

modulePais.controller('PaisController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

        $scope.message = 'Controller dos Paises!';
        $scope.page = {pageNumber: 0};
        $scope.paisAtual = undefined;
        $scope.search = "";
        $scope.nome = "Pais";
        $scope.caminholistar = "./View/Localizacao/Pais/listarPais.html";
        $scope.caminhoeditar = "./View/Localizacao/Pais/editarPais.html";
        $scope.caminhoinicial = "#/Localizacao/Pais/";

        $scope.init = function() {
            $scope.loadPaises($scope.page.pageNumber);
        };

        $scope.incluir = function() {
            window.location.href = "#Localizacao/Pais/Novo/Editar";
        };

        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.paisAtual = {
                    id: "",
                    nome: "",
                    codigoIBGE: "",
                    sigla: ""
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.paisDadoId($routeParams.objeto);
            }
        };

        $scope.remover = function(index) {
            var pais = $scope.page.list[index];
            if (confirm('Deseja excluir o pais ' + pais.nome + '?')) {
                $scope.removerServidor(pais);
            }
        };

        $scope.removeratual = function() {
            var pais = $scope.paisAtual;
            if (confirm('Deseja excluir o pais ' + pais.nome + '?')) {
                $scope.removerServidor(pais);
            }
        };

        $scope.removerServidor = function(pais) {
            console.log(JSON.stringify(pais));
            $http({
                method: "DELETE",
                url: "./pais/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(pais))
            }).success(function(data, status) {
                $scope.loadPaises($scope.page.pageNumber);
                window.location.href = "#Localizacao/Pais";
            }).error(function(data, status) {
                alert("Erro ao excluir o Pais!");
            });
        };

        $scope.loadPaises = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./pais/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.page = data;
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar Paises!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./pais/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.paisAtual)
            }).success(function(data, status) {
                window.location.href = "#Localizacao/Pais/";
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao Salvar Paises!");
            });
        };

        $scope.paisDadoId = function(id) {
            $http({
                method: "GET",
                url: "./pais/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.paisAtual = data;
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
