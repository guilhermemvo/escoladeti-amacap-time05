var moduleUnidadeProduca = angular.module('escoladeti.unidadeProducao', ['ngRoute']);

moduleUnidadeProduca.controller('UnidadeProducaoController', ['$scope', '$http','$rootScope', '$routeParams','growl', function($scope, $http, $rootScope, $routeParams, growl) {

        $scope.message = 'Controller das Unidades de Produção!';
        $scope.page = {pageNumber: 0};
        $scope.unidadeProducaAtual = undefined;
        $scope.search = "";
        $scope.nome = "Unidade de Produção";
        $scope.form = "unidadedeproducao";
        $scope.caminholistar = "./View/Pessoa/UnidadeProducao/listarUnidadeProducao.html";
        $scope.caminhoeditar = "./View/Pessoa/UnidadeProducao/editarUnidadeProducao.html";
        $scope.caminhoinicial = "#/Pessoa/UnidadeProducao/";
        $scope.caminhorelatorio = "unidadeProducao/relatorio/listaRelatorio";
        $scope.displayBtnRelatorio = "inline";
        $scope.unidadeProducao = [];

        $scope.incluir = function() {
            window.location.href = "#Pessoa/UnidadeProducao/Novo/Editar";
        };
        $scope.init = function() {
                $scope.load(0);
        };
        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.unidadeProducaoAtual = {
                    nome: ""
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.unidadeProducaoDadoId($routeParams.objeto);
            }
        };

        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.pessoa;
            } else {
                var excluir = $scope.page.list[index];
                $scope.unidadeProducao = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'nome');
        };

        $scope.removerServidor = function(unidadeProducao) {
            $http({
                method: "DELETE",
                url: "./unidadeProducao/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(unidadeProducao))
            }).success(function(data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function(data, status) {
                if (status == 500) {
                    growl.error("Não foi possível excluir a Unidade de Produção. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir a Unidade de Produção!");

                }
            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./unidadeProducao/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar as Unidades de Produção!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./unidadeProducao/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.unidadeProducaoAtual)
            }).success(function(data, status) {
                window.location.href = $scope.caminhoinicial;
                console.log(JSON.stringify(status));
                growl.success("Cadastro efetuado com sucesso!");
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
            });
        };

        $scope.unidadeProducaoDadoId = function(id) {
            $http({
                method: "GET",
                url: "./unidadeProducao/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.unidadeProducaoAtual = data;
            }).error(function(data, status) {
            });
        };              

    }
]);
