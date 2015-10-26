var moduleAssociadoPF = angular.module('escoladeti.movassociadopf', ['ngRoute']);

moduleAssociadoPF.controller('MovimentacaoAssociadoPFController', ['$scope', '$http', '$rootScope', '$routeParams','growl', function($scope, $http, $rootScope, $routeParams, growl) {

        $scope.message = 'Controller de Pagamento de Associados';
        $scope.page = {pageNumber: 0};
        $scope.caminholistar = "./View/Pessoa/AssociadoPF/listarAssociadoPF.html";
        $scope.caminhoeditar = "./View/Pessoa/AssociadoPF/movAssociadoPF.html";
        $scope.caminhoinicial = "#Pessoa/AssociadoPF/";
        $scope.nome = "Pagamento de Associados";
        $scope.associadosPF = [];
        $scope.listamovimentacoespf = [];
       // $scope.visualizacaoAtiva = true;
        $scope.daoMovPF = {
        		id: '', 
				dataPagamento: '',
				valorPago: '',
				isPago:'true',
				associadoPF:''
		};

        $scope.initEditar = function() {
            $scope.movimentacaoAtualPF = {
            	id:'',	
            	dataPagamento:'',
            	valorPago: '',
            	isPago:'',
                associadoPF: ''
            };
              $scope.loadMovimentacoes();
              //$scope.loadTodasMovimentacoes($routeParams.objeto);
              $scope.getIdAssociado($routeParams.objeto);
              //$scope.loadMovimentacoesPorAssociado($routeParams.objeto);
        };
        	

        $scope.getIdAssociado = function(id) {
            $http({
                method: "GET",
                url: "./associadopf/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.movimentacaoAtualPF.associadoPF = data;
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
           $scope.daoMovPF.id = undefined;
           $scope.daoMovPF.dataPagamento = $scope.movimentacaoAtualPF.dataPagamento;
           $scope.daoMovPF.valorPago = $scope.movimentacaoAtualPF.valorPago;
           $scope.daoMovPF.isPago = 'true';
           $scope.daoMovPF.associadoPF = $scope.movimentacaoAtualPF.associadoPF;
           
            $http({
                method: "POST",
                url: "./associadopfmovimentacao/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson($scope.daoMovPF)
            }).success(function(data, status) {
                window.location.href = $scope.caminhoinicial;
                growl.success("Movimentação efetuada com sucesso!");
            }).error(function(data, status) {
                console.log(angular.toJson($scope.movimentacaoAtualPF));
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar a movimentação!");
            });
        };
        	
        
        $scope.loadMovimentacoesPorAssociado = function(id) {
            $http({
                method: "POST",
                url: "./associadopfmovimentacao/associados",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson(id)
            }).success(function(data, status) {
                $scope.associadosPF = data;
                if ($scope.associadosPF.length === 0) {
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
                url: "./associadopfmovimentacao/todas",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listamovimentacoespf = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar as Movimentaçoes!");
            });
        };
      
        
        $scope.loadTodasMovimentacoes = function(id) {
            $http({
                method: "GET",
                url: "./associadopfmovimentacao/todasmovimentacoes",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
            	data: angular.toJson(id)
            }).success(function(data, status) {
                $scope.listamovimentacoespf = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar as Movimentaçoes!");
            });
        };
    }
]);


