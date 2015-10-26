var moduleCidade = angular.module('escoladeti.cidade', ['ngRoute']);

moduleCidade.controller('CidadeController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

        $scope.message = 'Controller das Cidades!';
        $scope.page = {pageNumber: 0};
        $scope.cidadeAtual = undefined;
        $scope.search = "";
        $scope.listaestados = [];
        $scope.nome = "Cidade";
        $scope.caminholistar = "./View/Localizacao/Cidade/listarCidade.html";
        $scope.caminhoeditar = "./View/Localizacao/Cidade/editarCidade.html";
        $scope.caminhoinicial = "#/Localizacao/Cidade/";

        $scope.init = function() {
            $scope.loadCidades($scope.page.pageNumber);
        };

        $scope.incluir = function() {
            window.location.href = "#Localizacao/Cidade/Novo/Editar";
        };

        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.cidadeAtual = {
                    id: "",
                    unidadefederativa: "",
                    nome: "",
                    dataFundacao: "",
                    codigoIBGE: ""
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.cidadeDadoId($routeParams.objeto);
            }
            $scope.loadTodosEstados();
        };

        $scope.remover = function(index) {
            var cidade = $scope.page.list[index];
            if (confirm('Deseja excluir a cidade ' + cidade.nome + '?')) {
                $scope.removerServidor(cidade);
            }
        };
        $scope.loadTodosEstados = function() {
            $http({
                method: "GET",
                url: "./unidadefederativa/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(status));
                $scope.listaestados = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar Estados");
            });
        };

        $scope.removerServidor = function(cidade) {
            console.log(JSON.stringify(cidade));
            $http({
                method: "DELETE",
                url: "./cidade/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(cidade))
            }).success(function(data, status) {
                $scope.loadCidades($scope.page.pageNumber);
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir cidades!");
            });
        };

        $scope.loadCidades = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./cidade/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.page = data;
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar Cidades!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./cidade/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.cidadeAtual)
            }).success(function(data, status) {
                window.location.href = "#Localizacao/Cidade";
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir cidades!");
            });
        };

        $scope.cidadeDadoId = function(id) {
            $http({
                method: "GET",
                url: "./cidade/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.cidadeAtual = data;
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

    }
]);
