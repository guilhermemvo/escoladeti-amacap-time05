var moduleEndereco = angular.module('escoladeti.endereco', ['ngRoute']);

moduleEndereco.config(['$routeProvider', '$locationProvider',
    function($routeProvider, $locationProvider) {
        $routeProvider.
                when('/cadastros/endereco/listar', {
                    templateUrl: './pages/cadastro/endereco/enderecoListagem.html',
                    //controller: 'EnderecoController'
                });
        $locationProvider.html5Mode(false);
    }]);

moduleEndereco.controller('EnderecoController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

        $scope.message = 'Controller de Enderecos!';
        $scope.page = {pageNumber: 1};
        $scope.usuarioAtual = undefined;

        $scope.init = function() {
            
        };
    }
]);


