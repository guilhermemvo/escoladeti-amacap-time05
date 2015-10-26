
myApp.directive('ngPaginacao', function() {
    return {
        restrict: 'AEC',
        controller: ['$scope', function($scope) {
                var carregar = $scope.load;
                $scope.bigCurrentPage = 0;

                $scope.voltar = function() {
                    return carregar(($scope.page.pageNumber - 2) < 0 ? 0 : ($scope.page.pageNumber - 2));
                };

                $scope.avancar = function() {
                    return carregar($scope.page.pageNumber);
                };

                $scope.paginar = function(numeroPagina) {
                    return carregar(numeroPagina);
                };
                
                $scope.numeroPagina = function() {                    
                    $scope.bigTotalItems = $scope.page.pageCount * 8;
                    $scope.maxSize = 10;
                    $scope.numPages = $scope.page.pageCount;                    
                    return $scope.page.pageNumber;
                };

                $scope.habilitarBotao = function(max, posicao) {
                    if (max <= posicao) {
                        return 'disabled';
                    } else {
                        return '';
                    }
                };

                $scope.habilitarVoltar = function() {
                    return $scope.habilitarBotao($scope.page.pageNumber, 1);
                };

                $scope.habilitarAvancar = function() {
                    return $scope.habilitarBotao($scope.page.pageCount, $scope.page.pageNumber);
                };

                $scope.initPagina = function () {                              
                    $scope.bigTotalItems = $scope.page.pageCount * 8;
                    $scope.maxSize = 10;
                    $scope.numPages = $scope.page.pageCount; 
                };
                $scope.carregarPagina = function (){
                    carregar($scope.bigCurrentPage-1);
                };
                
               
                
                
                                
            }],
        templateUrl: 'View/Componentes/Paginacao.html'

    };
});

