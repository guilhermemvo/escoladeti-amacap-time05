var moduleTipoLogradouro = angular.module('escoladeti.tipologradouro', ['ngRoute']);

moduleTipoLogradouro.controller('TipoLogradouroController', ['$scope', '$http', '$rootScope', '$routeParams', 'growl', function($scope, $http, $rootScope, $routeParams, growl) {

        $scope.message = 'Controller dos Tipos de Logradouro!';
        $scope.page = {pageNumber: 0};
        $scope.tipoLogradouroAtual = undefined;
        $scope.search = "";
        $scope.nome = "Tipo Logradouro";
        $scope.form = "tipologradouro";
        $scope.caminholistar = "./View/Configuracao/TipoLogradouro/listarTipoLogradouro.html";
        $scope.caminhoeditar = "./View/Configuracao/TipoLogradouro/editarTipoLogradouro.html";
        $scope.caminhoinicial = "#/Configuracao/TipoLogradouro/";
        $scope.displayBtnRelatorio = "none";
        $scope.tipologradouro = [];

        $scope.init = function() {
            $scope.load(0);
        };

        $scope.incluir = function() {
            window.location.href = "#Configuracao/TipoLogradouro/Novo/Editar";
        };

        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.tipoLogradouroAtual = {
                    id: "",
                    descricao: ""
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.tipoLogradouroDadoId($routeParams.objeto);
            }
        };

        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.tipoLogradouroAtual;
            } else {
                var excluir = $scope.page.list[index];
                $scope.tipoLogradouroAtual = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'descricao');
        };

        $scope.removerServidor = function(tipoLogradouro) {
            console.log(JSON.stringify(tipoLogradouro));
            $http({
                method: "DELETE",
                url: "./tipologradouro/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(tipoLogradouro))
            }).success(function(data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function(data, status) {
                if (status == 500) {
                    growl.error("Não foi possível excluir o Tipo de Logradouro. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir o Tipo de Logradouro!");
                }
            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./tipologradouro/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.page = data;
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar os Logradouros!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./tipologradouro/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.tipoLogradouroAtual)
            }).success(function(data, status) {
                window.location.href = "#Localizacao/TipoLogradouro/";
                console.log(JSON.stringify(status));
                growl.success("Cadastro efetuado com sucesso!");
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
            });
        };

        $scope.tipoLogradouroDadoId = function(id) {
            $http({
                method: "GET",
                url: "./tipologradouro/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.tipoLogradouroAtual = data;
            }).error(function(data, status) {
                $("<div></div>")
                        .css({overflow: "auto"})
                        .html(data)
                        .dialog({
                            autoOpen: true,
                            title: 'Erro ao carregar o tipo de logradouro ' + id,
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
                    url: "./tipologradouro/procurarPorNome/" + nome,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function(data, status) {
                    $scope.page = data;
                }).error(function(data, status) {
                    growl.info("Não foi possível localizar o Logradouro!");
                });
            } else {
                $scope.load(0);
            }
        };
    }
]);
