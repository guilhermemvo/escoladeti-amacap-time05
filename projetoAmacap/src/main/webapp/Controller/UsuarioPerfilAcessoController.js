var moduleUsuarioPerfilAcesso = angular.module('escoladeti.usuarioPerfilAcesso', ['ngRoute']);

moduleUsuarioPerfilAcesso.controller('UsuarioPerfilAcessoController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

        $scope.message = 'Controller de Usuario Perfil de Acesso!';
        $scope.page = {pageNumber: 0};
        $scope.usuarioPerfilAcessoAtual = undefined;
        $scope.search = "";

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
            var usuarioPerfilAcesso = $scope.page.list[index];
            if (confirm('Deseja excluir o usuarioPerfilAcesso ' + usuarioPerfilAcesso.nome + '?')) {
                $scope.removerServidor(usuarioPerfilAcesso);
            }
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
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir o UsuarioPerfilAcesso!");
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
                alert("Erro ao buscar Usuarios!");
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
                alert("Erro ao buscar Usuario!");
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
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao Salvar UsuarioPerfilAcesso!");
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