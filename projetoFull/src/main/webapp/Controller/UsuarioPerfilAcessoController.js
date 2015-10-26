var moduleUsuarioPerfilAcesso = angular.module('escoladeti.usuarioPerfilAcesso', ['ngRoute']);

moduleUsuarioPerfilAcesso.controller('UsuarioPerfilAcessoController', ['$scope', '$http', '$rootScope', '$routeParams','growl', function($scope, $http, $rootScope, $routeParams, growl) {

        $scope.message = 'Controller de Usuario Perfil de Acesso!';
        $scope.page = {pageNumber: 0};
        $scope.usuarioPerfilAcessoAtual = undefined;
        $scope.search = "";
        $scope.nome = "Usuário Perfil Acesso";
        $scope.form = "usuarioPerfilAcesso";
        $scope.displayBtnRelatorio = "none";
        $scope.perfilDeAcesso = [];

        $scope.init = function() {
            $scope.loadUsuarioPerfilAcessos($scope.page.pageNumber);
        };
        $scope.usuarios = function() {
            $scope.loadUsuariosDisponiveis($scope.page.pageNumber);
        };

        $scope.incluir = function() {
            window.location.href = "#Configuracao/UsuarioPerfilAcesso/Novo";
        };

        $scope.initEditarUsuarioPerfilAcesso = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.usuarioPerfilAcessoAtual = {
                    id: "",
                    descricao: ""
                };
            } else {
                $scope.usuarioPerfilAcessoDadoId($routeParams.objeto);
            }
        };

        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.pessoa;
            } else {
                var excluir = $scope.page.list[index];
                $scope.perfilDeAcesso = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'nome');
        };

        $scope.removerServidor = function(usuarioPerfilAcesso) {
            console.log(JSON.stringify(usuarioPerfilAcesso));
            $http({
                method: "DELETE",
                url: "./usuarioPerfilAcesso/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(usuarioPerfilAcesso))
            }).success(function(data, status) {
                $scope.loadUsuarioPerfilAcessos($scope.page.pageNumber);
                console.log(JSON.stringify(status));
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function(data, status) {
                if (status == 500) {
                   growl.error("Não foi possível excluir o Perfil de Acesso. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir o Perfil de Acesso!");
                }
            });
        };

        $scope.loadUsuariosDisponiveis = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./usuario/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.pages = data;
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar os Usuários!");
            });
        };

        $scope.loadUsuarioPerfilAcessos = function(numeroPagina) {
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

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./usuarioPerfilAcesso/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.usuarioPerfilAcessoAtual)
            }).success(function(data, status) {
                window.location.href = "#Configuracao/UsuarioPerfilAcesso/";
                console.log(JSON.stringify(status));
                growl.success("Cadastro efetuado com sucesso!");
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
            });
        };

        $scope.usuarioPerfilAcessoDadoId = function(id) {
            $http({
                method: "GET",
                url: "./usuarioPerfilAcesso/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.usuarioPerfilAcessoAtual = data;
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
