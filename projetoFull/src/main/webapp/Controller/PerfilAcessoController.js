var modulePerfilAcesso = angular.module('escoladeti.perfilAcesso', ['ngRoute']);

modulePerfilAcesso.controller('PerfilAcessoController', ['$scope', '$http','$rootScope', '$routeParams','growl', function($scope, $http,$rootScope,$routeParams, growl) {

        $scope.message = 'Controller de Perfil de Acesso!';
        $scope.page = {pageNumber: 0};
        $scope.perfilAcessoAtual = undefined;
        $scope.search = "";
        $scope.nome = "Perfil de Acesso";
        $scope.form = "perfildeacesso";
        $scope.caminholistar = "./View/Configuracao/PerfilAcesso/listarPerfilAcesso.html";
        $scope.caminhoeditar = "./View/Configuracao/PerfilAcesso/editarPerfilAcesso.html";
        $scope.caminhoinicial = "#/Configuracao/PerfilAcesso/";
        $scope.displayBtnRelatorio = "none";
        $scope.perfilDeAcesso = [];
        $scope.init = function() {
            $scope.load(1);
        };
        $scope.usuarios = function() {
            $scope.loadUsuarios($scope.page.pageNumber);
        };

        $scope.adicionarUsuario = function(usuario) {
            $scope.addUsuario(usuario);
        };

        $scope.incluir = function() {
            window.location.href = "#Configuracao/PerfilAcesso/Novo/Editar";
        };

        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.perfilAcessoAtual = {
                    id:"",
                    descricao:""
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.perfilAcessoDadoId($routeParams.objeto);
            }
        };
        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.perfilAcessoAtual;
            } else {
                var excluir = $scope.page.list[index];
                $scope.perfilAcesso = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'descricao');
        };

        $scope.removerServidor = function(perfilAcesso) {
            console.log(JSON.stringify(perfilAcesso));
            $http({
                method: "DELETE",
                url: "./perfilAcesso/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(perfilAcesso))
            }).success(function(data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(1);
            }).error(function(data, status) {
                if (status == 500) {
                    growl.error("Não foi possível excluir o Perfil de Acesso. O cadastro possui outras referências no sistema!");      
                } else {
                    growl.error("Não foi possível excluir o Perfil de Acesso!");
                }
            });
        };

        $scope.load = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./perfilAcesso/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.page = data;
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar os Perfis de Acesso!");
            });
        };
        $scope.loadUsuarios = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./usuario/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.page = data;
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar os Usuários!");
            });
        };

        $scope.addUsuario = function(usuario) {
            $http({
                method: "POST",
                url: "./perfilAcesso/salvarUsuario",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.perfilAcessoAtual)
            }).success(function(data, status) {
                window.location.href = "#Configuracao/PerfilAcesso/";
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
            });
        };
        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./perfilAcesso/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.perfilAcessoAtual)
            }).success(function(data, status) {
                window.location.href = "#Configuracao/PerfilAcesso/";
                console.log(JSON.stringify(status));
                growl.success("Cadastro efetuado com sucesso!");
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
            });
        };

        $scope.perfilAcessoDadoId = function(id) {
            $http({
                method: "GET",
                url: "./perfilAcesso/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.perfilAcessoAtual = data;
            }).error(function(data, status) {
                $("<div></div>")
                        .css({overflow: "auto"})
                        .html(data)
                        .dialog({
                            autoOpen: true,
                            title: 'Erro ao carregar perfil de acesso ' + id,
                            width: 800,
                            height: 450,
                            modal: true
                        });
            });
        };

    }
]);