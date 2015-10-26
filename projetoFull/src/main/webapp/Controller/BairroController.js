var moduleBairro = angular.module('escoladeti.bairro', ['ngRoute']);

moduleBairro.controller('BairroController', ['$scope', '$http', '$rootScope', '$routeParams', 'growl', function($scope, $http, $rootScope, $routeParams, growl) {

        $scope.message = 'Controller das bairro!';
        $scope.page = {pageNumber: 0};
        $scope.bairroAtual = undefined;
        $scope.search = "";
        $scope.listaCidade = [];
        $scope.nome = "Bairro";
        $scope.form = "Bairro";
        $scope.caminholistar = "./View/Localizacao/Bairro/listarBairro.html";
        $scope.caminhoeditar = "./View/Localizacao/Bairro/editarBairro.html";
        $scope.caminhoinicial = "#/Localizacao/Bairro/";
        $scope.displayBtnRelatorio = "none";
        $scope.bairro = [];

        $scope.init = function() {
            $scope.load(0);
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
            if (index === '') {
                var excluir = $scope.bairroAtual;
            } else {
                var excluir = $scope.page.list[index];
                $scope.bairro = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'nome');
        };

        $scope.loadTodasCidades = function() {
            $http({
                method: "GET",
                url: "./cidade/todas",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listaCidade = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar as Cidades!");
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
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function(data, status) {
                if (status == 500) {
                    growl.error("Não foi possível excluir o Bairro. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir o Bairro!");
                }
            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./bairro/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar os Bairros!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./bairro/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.bairroAtual)
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

        $scope.procurarPorNome = function(nome) {
            if (nome != '') {
                $http({
                    method: "GET",
                    url: "./bairro/procurarPorNome/" + nome,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function(data, status) {
                    $scope.page = data;
                }).error(function(data, status) {
                    growl.info("Não foi possível localizar o Bairro!");
                });
            } else {
                $scope.load(0);
            }
        };
    }
]);
