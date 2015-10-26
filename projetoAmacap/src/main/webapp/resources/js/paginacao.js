
myApp.directive('ngPaginacao', function() {
    return {
        restrict: 'A',
        controller: ['$scope', function(scope) {
                var carregar = scope.load;

                scope.voltar = function() {
                    return carregar((scope.page.pageNumber - 2) < 0 ? 0 : (scope.page.pageNumber - 2));
                };

                scope.avancar = function() {
                    return carregar(scope.page.pageNumber);
                };

                scope.paginar = function(numeroPagina) {
                    return carregar(numeroPagina);
                };

                scope.numeroPagina = function() {
                    return scope.page.pageNumber;
                };

                scope.habilitarBotao = function(max, posicao) {
                    if (max <= posicao) {
                        return 'disabled';
                    } else {
                        return '';
                    }
                };

                scope.habilitarVoltar = function() {
                    return scope.habilitarBotao(scope.page.pageNumber, 1);
                };

                scope.habilitarAvancar = function() {
                    return scope.habilitarBotao(scope.page.pageCount, scope.page.pageNumber);
                };

            }],
        templateUrl: 'View/Componentes/Paginacao.html'
    };
});

