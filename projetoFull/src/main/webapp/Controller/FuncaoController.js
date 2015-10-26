var moduleFuncao = angular.module('escoladeti.funcao', ['ngRoute']);

moduleFuncao.controller('FuncaoController', ['$scope', '$http', '$rootScope', '$routeParams','growl', function($scope, $http, $rootScope, $routeParams, growl) {

        $scope.message = 'Controller das Funções!';
        $scope.page = {pageNumber: 0};
        $scope.funcaoAtual = undefined;
        $scope.search = "";
        $scope.nome = "Função";
        $scope.form = "funcao";
        $scope.caminholistar = "./View/Configuracao/Funcao/listarFuncao.html";
        $scope.caminhoeditar = "./View/Configuracao/Funcao/editarFuncao.html";
        $scope.caminhoinicial = "#/Configuracao/Funcao/";
        $scope.displayBtnRelatorio = "none";
        $scope.funcao = [];

        $scope.incluir = function() {
            window.location.href = "#Configuracao/Funcao/Novo/Editar";
        };
        $scope.init = function() {
                $scope.load(0);
        };
        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.funcaoAtual = {
                    id: "",
                    descricao: ""
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.funcaoDadoId($routeParams.objeto);
            }
        };

       $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.funcaoAtual;
            } else {
                var excluir = $scope.page.list[index];
                $scope.funcao = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'descricao');
        };

        $scope.removerServidor = function(funcao) {
            $http({
                method: "DELETE",
                url: "./funcao/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(funcao))
            }).success(function(data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function(data, status) {
                if (status == 500) {
                    growl.error("Não foi possível excluir a Função. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir a Função!");
                }
            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./funcao/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar as Funções!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./funcao/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.funcaoAtual)
            }).success(function(data, status) {
                window.location.href = "#Configuracao/Funcao/";
                console.log(JSON.stringify(status));
                growl.success("Cadastro efetuado com sucesso!");
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
            });
        };

        $scope.funcaoDadoId = function(id) {
            $http({
                method: "GET",
                url: "./funcao/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.funcaoAtual = data;
            }).error(function(data, status) {
                $("<div></div>")
                        .css({overflow: "auto"})
                        .html(data)
                        .dialog({
                            autoOpen: true,
                            title: 'Erro ao carregar função ' + id,
                            width: 800,
                            height: 450,
                            modal: true
                        });
            });
        };
        
        $scope.procurarPorNome = function(nome) {
            if(nome != ''){                                 
                $http({
                    method: "GET",
                    url: "./funcao/procurarPorNome/" + nome,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function(data, status) {
                    $scope.page = data;
                }).error(function(data, status) {
                    growl.info("Não foi possível localizar Funções!");
                });
            }else{
                $scope.load(0);
            }                        
        };        

    }
]);