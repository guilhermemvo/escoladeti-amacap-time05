var moduleMateriaPrima = angular.module('escoladeti.materiaprima', ['ngRoute']);

moduleMateriaPrima.controller('MateriaPrimaController', ['$scope', '$http','$rootScope', '$routeParams','growl', function($scope, $http,$rootScope, $routeParams, growl) {

        $scope.message = 'Controller da Materia Prima!';
        $scope.page = {pageNumber: 0};
        $scope.materiaPrimaAtual = undefined;
        $scope.search = "";
        $scope.nome = "Materia Prima";
        $scope.form = "materiaprima";
        $scope.listaFornecedores = [];
        $scope.listaUnidadeMedida = [];
        $scope.caminholistar = "./View/Material/MateriaPrima/listarMateriaPrima.html";
        $scope.caminhoeditar = "./View/Material/MateriaPrima/editarMateriaPrima.html";
        $scope.caminhoinicial = "#/Material/MateriaPrima/";
        $scope.caminhorelatorio = "materiaprima/relatorio/listaRelatorio";
        $scope.caminhorelatorio = "materiaprima/relatorio/listaRelatorio";
        $scope.displayBtnRelatorio = "inline";
        $scope.materiaprima = [];
        $scope.incluir = function() {
            window.location.href = "#Material/MateriaPrima/Novo/Editar";
        };
        $scope.init = function() {
            $scope.load(0);
        };
        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.materiaPrimaAtual = {
                    id: "",
                    nome:"",
                    quantidade:"",
                    valorUnitario:"",
                    unidadeMedida:""
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.materiaPrimaDadoId($routeParams.objeto);
            }
            
            $scope.loadTodasUnidadeMedida();
        };
        
        $scope.loadTodasUnidadeMedida = function() {
            $http({
                method: "GET",
                url: "./unidadeMedida/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listaUnidadeMedida = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar as Unidades de Medida!");
            });
        };

        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.materiaPrimaAtual;
            } else {
                var excluir = $scope.page.list[index];
                $scope.materiaprima = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'nome');
        };
        
        $scope.removerServidor = function(materiaprima) {
            $http({
                method: "DELETE",
                url: "./materiaprima/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(materiaprima))
            }).success(function(data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function(data, status) {
                if (status == 500) {
                    growl.error("Não foi possível excluir a Materia Prima. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir a Materia Prima!");
                }

            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./materiaprima/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar as Matéria Primas!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./materiaprima/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.materiaPrimaAtual)
            }).success(function(data, status) {
                 window.location.href = $scope.caminhoinicial;
                console.log(JSON.stringify(status));
                growl.success("Cadastro efetuado com sucesso!");
            }).error(function(data, status) {
            	alert(data,status);
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");

                $("<div></div>")
                        .css({overflow: "auto"})
                        .html(data)
                        .dialog({
                            autoOpen: true,
                            title: 'Erro ao Salvar Materia Prima!!',
                            width: 800,
                            height: 450,
                            modal: true
                        });
            });
        };

        $scope.materiaPrimaDadoId = function(id) {
            $http({
                method: "GET",
                url: "./materiaprima/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.materiaPrimaAtual = data;
            }).error(function(data, status) {
                $("<div></div>")
                        .css({overflow: "auto"})
                        .html(data)
                        .dialog({
                            autoOpen: true,
                            title: 'Erro ao carregar a materia prima',
                            width: 800,
                            height: 450,
                            modal: true
                        });
            });
        };

    }
]);
