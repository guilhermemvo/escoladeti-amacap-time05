var moduleDisciplina = angular.module('escoladeti.disciplina', ['ngRoute']);

moduleDisciplina.controller('DisciplinaController', ['$scope', '$http','$rootScope','$routeParams','growl', function($scope, $http, $rootScope,$routeParams, growl) {

        $scope.message = 'Controller das Disciplina!';
        $scope.page = {pageNumber: 0};
        $scope.search = "";
        $scope.disciplinaAtual;
        $scope.nome = "Disciplina";
        $scope.form = "Disciplina";
        $scope.listaescola = [];
        $scope.caminholistar = "./View/Configuracao/Disciplina/listarDisciplina.html";
        $scope.caminhoeditar = "./View/Configuracao/Disciplina/editarDisciplina.html";
        $scope.caminhoinicial = "#/Configuracao/Disciplina/";
        $scope.displayBtnRelatorio = "none";
        $scope.disciplina = [];

        $scope.incluir = function() {
            window.location.href = "#Configuracao/Disciplina/Novo/Editar";
        };
        $scope.init = function() {
            $scope.load(0);
        };
        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.disciplinaAtual = {
                    id: "",
                    descricao: ""
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.disciplinaDadoId($routeParams.objeto);
            }
        };

        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.disciplinaAtual;
            } else {
                var excluir = $scope.page.list[index];
                $scope.disciplina = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'descricao');
        };

        $scope.removerServidor = function(disciplina) {
            $http({
                method: "DELETE",
                url: "./disciplina/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(disciplina))
            }).success(function(data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function(data, status) {

                if (status == 500) {
                    growl.error("Não foi possível excluir a Disciplina. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir a Disciplina!");
                }

            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./disciplina/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar as Disciplinas!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./disciplina/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.disciplinaAtual)
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

        $scope.disciplinaDadoId = function(id) {
            $http({
                method: "GET",
                url: "./disciplina/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.disciplinaAtual = data;
            }).error(function(data, status) {
            });
        };
        
        $scope.procurarPorNome = function(nome) {
            if(nome != ''){                                 
                $http({
                    method: "GET",
                    url: "./disciplina/procurarPorNome/" + nome,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function(data, status) {
                    $scope.page = data;
                }).error(function(data, status) {
                    growl.info("Não foi possível localizar Disciplina!");
                });
            }else{
                $scope.load(0);
            }                        
        };        

    }
]);
