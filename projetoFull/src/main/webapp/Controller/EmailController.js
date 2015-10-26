var moduleEmail = angular.module('escoladeti.email', ['ngRoute']);

modulePais.controller('EmailController', ['$scope', '$http', '$rootScope', '$routeParams', 'growl', function($scope, $http, $rootScope, $routeParams, growl) {

        $scope.message = 'Controller dos Emails!';
        $scope.caminhoinicial = "#/Site/Contato.html/";
        $scope.email = true;


        $scope.salvar = function() {
            console.log("teste");
            $http({
                method: "POST",
                url: "./email/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.email)
            }).success(function(data, status) {
                if (data === "OK") {
                    window.location.href = $scope.caminhoinicial;
                    console.log(JSON.stringify(status));
                    growl.success("Cadastro efetuado com sucesso!");
                } else {
                    growl.info("Erro  do IBGE já cadastrado!");
                   
                }
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
            });
        };


    }
]);
