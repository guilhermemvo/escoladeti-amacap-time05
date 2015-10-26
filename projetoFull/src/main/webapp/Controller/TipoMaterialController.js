var moduleAluno = angular.module('escoladeti.tipomaterial', ['ngRoute']);

moduleAluno.controller('TipoMaterialController', ['$scope', '$http','$rootScope', '$routeParams','growl', function($scope, $http,$rootScope, $routeParams, growl) {

        $scope.message = 'Controller da Tipo Material!';
        $scope.page = {pageNumber: 0};
        $scope.tipoMaterialAtual = undefined;
        $scope.search = "";
        $scope.nome = "Tipo Material";
        $scope.form = "tipomaterial";
        $scope.listaFornecedores = [];
        $scope.caminholistar = "./View/Configuracao/TipoMaterial/listarTipoMaterial.html";
        $scope.caminhoeditar = "./View/Configuracao/TipoMaterial/editarTipoMaterial.html";
        $scope.caminhoinicial = "#/Configuracao/TipoMaterial/";
        $scope.displayBtnRelatorio = "none";

        $scope.incluir = function() {
            window.location.href = "#Configuracao/TipoMaterial/Novo/Editar";
        };
        $scope.init = function() {
            $scope.load(0);
        };
        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.tipoMaterialAtual = {
                    id: ""
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.tipoMaterialDadoId($routeParams.objeto);
            }
        };

        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.tipoMaterialAtual;
            } else {
                var excluir = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'descricao');
        };
        
        $scope.removerServidor = function(tipomaterial) {
            $http({
                method: "DELETE",
                url: "./tipomaterial/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(tipomaterial))
            }).success(function(data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                if (status == 500) {
                   growl.error("Não foi possível excluir o Tipo de Material. O cadastro possui outras referências no sistema!");

                } else {
                    growl.error("Não foi possível excluir o Tipo de Material!");
                }

            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./tipomaterial/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar os Tipos de Material!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./tipomaterial/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.tipoMaterialAtual)
            }).success(function(data, status) {
                window.location.href = $scope.caminhoinicial;
                console.log(JSON.stringify(status));
                growl.success("Cadastro efetuado com sucesso!");
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
                $("<div></div>")
                        .css({overflow: "auto"})
                        .html(data)
                        .dialog({
                            autoOpen: true,
                            title: 'Erro ao Salvar Tipo Material!!',
                            width: 800,
                            height: 450,
                            modal: true
                        });
            });
        };

        $scope.tipoMaterialDadoId = function(id) {
            $http({
                method: "GET",
                url: "./tipomaterial/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.tipoMaterialAtual = data;
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
