var moduleAssociadoPJ = angular.module('escoladeti.movassociadopj', ['ngRoute']);

moduleAssociadoPJ.controller('MovimentacaoAssociadoPJController', ['$scope', '$http', '$rootScope', '$routeParams','growl', function($scope, $http, $rootScope, $routeParams, growl) {

        $scope.message = 'Controller de Pagamento de Associados';
        $scope.page = {pageNumber: 0};
        $scope.caminholistar = "./View/Pessoa/AssociadoPJ/listarAssociadoPJ.html";
        $scope.caminhoeditar = "./View/Pessoa/AssociadoPJ/movAssociadoPJ.html";
        $scope.caminhoinicial = "#Pessoa/AssociadoPJ/";
        $scope.nome = "Pagamento de Associados";
        $scope.associadosPJ = [];
        $scope.listamovimentacoes = [];
       // $scope.visualizacaoAtiva = true;
        $scope.daoMov = {
        		id: '', 
				dataPagamento: '',
				valorPago: '',
				isPago:'true',
				associadoPJ:''
		};

        $scope.initEditar = function() {
            $scope.movimentacaoAtual = {
            	id:'',	
            	dataPagamento:'',
            	valorPago: '',
            	isPago:'',
                associadoPJ: ''
            };
              $scope.loadMovimentacoes();
              //$scope.loadTodasMovimentacoes($routeParams.objeto);
              $scope.getIdAssociado($routeParams.objeto);
              //$scope.loadMovimentacoesPorAssociado($routeParams.objeto);
        };
        	

        $scope.getIdAssociado = function(id) {
            $http({
                method: "GET",
                url: "./associadopj/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.movimentacaoAtual.associadoPJ = data;
            }).error(function(data, status) {
                $("<div></div>")
                        .css({overflow: "auto"})
                        .html(data)
                        .dialog({
                            autoOpen: true,
                            title: 'Erro ao carregar associado ' + id,
                            width: 800,
                            height: 450,
                            modal: true
                        });
            });
        };
        
        $scope.salvar = function() {
           $scope.daoMov.id = undefined;
           $scope.daoMov.dataPagamento = $scope.movimentacaoAtual.dataPagamento;
           $scope.daoMov.valorPago = $scope.movimentacaoAtual.valorPago;
           $scope.daoMov.isPago = 'true';
           $scope.daoMov.associadoPJ = $scope.movimentacaoAtual.associadoPJ;
           
            $http({
                method: "POST",
                url: "./associadopjmovimentacao/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson($scope.daoMov)
            }).success(function(data, status) {
                window.location.href = $scope.caminhoinicial;
                growl.success("Movimentação efetuada com sucesso!");
            }).error(function(data, status) {
                console.log(angular.toJson($scope.movimentacaoAtual));
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar a movimentação!");
            });
        };
        	
        
        $scope.loadMovimentacoesPorAssociado = function(id) {
            $http({
                method: "POST",
                url: "./associadopjmovimentacao/associados",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson(id)
            }).success(function(data, status) {
                $scope.associadosPJ = data;
                if ($scope.associadosPJ.length === 0) {
                    $(".form-control.btn.btn-success").css("display", "none");
                }
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
            });
        };
        	
        	$scope.loadMovimentacoes = function() {
            $http({
                method: "GET",
                url: "./associadopjmovimentacao/todas",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listamovimentacoes = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar as Movimentaçoes!");
            });
        };
      
        
        $scope.loadTodasMovimentacoes = function(id) {
            $http({
                method: "GET",
                url: "./associadopjmovimentacao/todasmovimentacoes",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
            	data: angular.toJson(id)
            }).success(function(data, status) {
                $scope.listamovimentacoes = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar as Movimentaçoes!");
            });
        };
    }
]);


