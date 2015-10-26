var moduleUnidadeProducao = angular.module('escoladeti.unidadeProducao', ['ngRoute']);

moduleUnidadeProducao.controller('UnidadeProducaoController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

        $scope.message = 'Controller das Unidades de Produção!';
        $scope.page = {pageNumber : 0};
        $scope.unidadeProducaoAtual = undefined;
        $scope.search = "";

        $scope.init = function() {
            $scope.loadUnidadeProducao($scope.page.pageNumber);
        };

        $scope.incluir = function() {            
            window.location.href = "#Operacao/UnidadeProducao/Novo";
        };

        $scope.initEditarUnidadeProducao = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.unidadeProducaoAtual = {
                    id : "",
                    nome : ""
                };
            } else {                
                $scope.unidadeProducaoDadoId($routeParams.objeto);
            }
        };
        
         $scope.confirmaExclusao = function(index){
            $scope.modalExcluir = index;
            $('#modalExcluir').modal('show');
        }

        $scope.remover = function(index) {
            var unidadeProducao = $scope.page.list[index];
            $scope.removerServidor(unidadeProducao);  
        };
        
        $scope.removerServidor = function(unidadeProducao) {
            console.log(JSON.stringify(unidadeProducao));
            $http({
                method: "DELETE",
                url: "./unidadeProducao/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(unidadeProducao))
            }).success(function(data, status) {
                $scope.loadUnidadeProducao($scope.page.pageNumber);
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir a Unidade de Produção!");
            });
        };

        $scope.loadUnidadeProducao = function(numeroPagina) {        	
            $http({
                method: "GET",
                url: "./unidadeProducao/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.page = data;
                console.log(JSON.stringify(status));                
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar Unidade de Produção!");
            });
        };
        
        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./unidadeProducao/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.unidadeProducaoAtual)
            }).success(function(data, status) {
                window.location.href = "#Operacao/UnidadeProducao/";
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao Salvar Unidade de Produção!");
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
        		$("<div></div>")
        		.css({overflow : "auto"})
        		.html(data)
        		.dialog({
        			autoOpen: true,
        			title: 'Erro ao carregar Unidade de Produção ' + id,
        			width: 800,
        			height: 450,
        			modal: true
        		});
        	});
        };
        
    }
]);


