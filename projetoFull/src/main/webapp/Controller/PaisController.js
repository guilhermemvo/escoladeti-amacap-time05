var modulePais = angular.module('escoladeti.pais', ['ngRoute']);

modulePais.controller('PaisController', ['$scope', '$http', '$rootScope', '$routeParams', 'growl', function($scope, $http, $rootScope, $routeParams, growl) {

        $scope.message = 'Controller dos Paises!';
        $scope.page = {pageNumber: 0};
        $scope.paisAtual = undefined;
        $scope.search = "";
        $scope.nome = "País";
        $scope.form = "pais";
        $scope.caminholistar = "./View/Localizacao/Pais/listarPais.html";
        $scope.caminhoeditar = "./View/Localizacao/Pais/editarPais.html";
        $scope.caminhoinicial = "#/Localizacao/Pais/";
        $scope.displayBtnRelatorio = "none";
        $scope.pais = [];

        $scope.incluir = function() {
            window.location.href = "#Localizacao/Pais/Novo/Editar";
        };
        $scope.init = function() {
            $scope.load(0);
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
            if (index === '') {
                var excluir = $scope.paisAtual;
            } else {
                var excluir = $scope.page.list[index];
                $scope.pais = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'nome');
        };

        $scope.removerServidor = function(pais) {
            $http({
                method: "DELETE",
                url: "./pais/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(pais))
            }).success(function(data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function(data, status) {
                if (status == 500) {
                    growl.error("Não foi possível excluir o País. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir o País!");
                }
            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./pais/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar os Países!");
            });
        };


        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./pais/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.paisAtual)
            }).success(function(data, status) {
                if (data === "OK") {
                    window.location.href = $scope.caminhoinicial;
                    console.log(JSON.stringify(status));
                    growl.success("Cadastro efetuado com sucesso!");
                } else {
                    growl.info("Código do IBGE já cadastrado!");
                    $scope.estadoAtual.codigoIBGE = "";
                }
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
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
                            title: 'Erro ao carregar pais ' + id,
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
                    url: "./pais/procurarPorNome/" + nome,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function(data, status) {
                    $scope.page = data;
                }).error(function(data, status) {
                    growl.info("Não foi possível localizar o País!");
                });
            } else {
                $scope.load(0);
            }
        };

    }
]);
