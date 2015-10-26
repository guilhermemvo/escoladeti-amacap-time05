var moduleUsuario = angular.module('escoladeti.usuario', ['ngRoute']);

moduleUsuario.controller('UsuarioController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

        $scope.message = 'Controller dos Usuários!';
        $scope.page = {pageNumber: 0};
        $scope.usuarioAtual = undefined;
        $scope.search = "";
        $scope.confirmarSenha;
        
        
        
        $scope.init = function() {
            $scope.loadUsuarios($scope.page.pageNumber);
        };

        $scope.incluir = function() {
            window.location.href = "#Pessoa/Usuario/Novo";
        };

        $scope.initEditarUsuario = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.usuarioAtual = {
                    id: "",
                    login: "",
                    senha: "",
                    ativo: ""
                };
                $scope.confirmarSenha="";
            } else {
                $scope.usuarioDadoId($routeParams.objeto);
                
            }
        };

        $scope.remover = function(index) {
            var usuario = $scope.page.list[index];
            if (confirm('Deseja excluir a usuário ' + usuario.nome + '?')) {
                $scope.removerServidor(usuario);
            }
        };
        $scope.removerServidor = function(usuario) {
            console.log(JSON.stringify(usuario));
            $http({
                method: "DELETE",
                url: "./usuario/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(usuario))
            }).success(function(data, status) {
                $scope.loadUsuarios($scope.page.pageNumber);
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir usuário!");
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
                alert("Erro ao buscar Usuarios!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./usuario/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.usuarioAtual)
            }).success(function(data, status) {
                window.location.href = "#Pessoa/Usuario";
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao salvar usuários!");
            });
        };

        $scope.usuarioDadoId = function(id) {
            $http({
                method: "GET",
                url: "./usuario/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.usuarioAtual = data;
                $scope.confirmarSenha = $scope.usuarioAtual.senha;
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