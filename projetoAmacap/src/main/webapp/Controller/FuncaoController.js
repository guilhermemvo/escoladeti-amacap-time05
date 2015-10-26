var moduleFuncao = angular.module('escoladeti.funcao', ['ngRoute']);

moduleFuncao.controller('FuncaoController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

        $scope.message = 'Controller das Funções!';
        $scope.page = {pageNumber: 0};
        $scope.funcaoAtual = undefined;
        $scope.search = "";
        $scope.nome = "Funcao";
        $scope.caminholistar = "./View/Configuracao/Funcao/listarFuncao.html";
        $scope.caminhoeditar = "./View/Configuracao/Funcao/editarFuncao.html";
        $scope.caminhoinicial = "#/Configuracao/Funcao/";

        $scope.init = function() {
            $scope.load(1);
        };

        $scope.incluir = function() {
            window.location.href = "#Configuracao/Funcao/Novo/Editar";
        };

        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.funcaoAtual = {
                    id: "",
                    nome: ""
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.funcaoDadoId($routeParams.objeto);
            }
        };
        $scope.removeratual = function() {
            var funcao = $scope.funcaoAtual;
            if (confirm('Deseja excluir a função ' + funcao.nome + '?')) {
                $scope.removerServidor(funcao);
            }
        };
        $scope.remover = function(index) {
            var funcao = $scope.page.list[index];
            if (confirm('Deseja excluir a Função  ' + funcao.nome + '?')) {
                $scope.removerServidor(funcao);
            }
        };

        $scope.removerServidor = function(funcao) {
            console.log(JSON.stringify(funcao));
            $http({
                method: "DELETE",
                url: "./funcao/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(funcao))
            }).success(function(data, status) {
                window.location.href = $scope.caminhoinicial;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir a Função!");
            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./funcao/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.page = data;
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar Função!");
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
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao Salvar Função!");
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
                            title: 'Erro ao carregar Função ' + id,
                            width: 800,
                            height: 450,
                            modal: true
                        });
            });
        };

    }
]);