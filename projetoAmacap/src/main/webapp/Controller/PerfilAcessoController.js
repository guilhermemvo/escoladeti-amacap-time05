var modulePerfilAcesso = angular.module('escoladeti.perfilAcesso', ['ngRoute']);

modulePerfilAcesso.controller('PerfilAcessoController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

        $scope.message = 'Controller de Perfil de Acesso!';
        $scope.page = {pageNumber: 0};
        $scope.perfilAcessoAtual = undefined;
        $scope.search = "";

        $scope.init = function() {
            $scope.loadPerfilAcessos($scope.page.pageNumber);
        };
        $scope.usuarios = function() {
            $scope.loadUsuarios($scope.page.pageNumber);
        };

        $scope.adicionarUsuario = function(usuario) {
            $scope.addUsuario(usuario);
        };

        $scope.incluir = function() {
            window.location.href = "#Configuracao/PerfilAcesso/Novo";
        };

        $scope.initEditarPerfilAcesso = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.perfilAcessoAtual = {
                    id: "",
                    descricao: ""
                };
            } else {
                $scope.perfilAcessoDadoId($routeParams.objeto);
            }
        };
        $scope.remover = function(index) {
            var perfilAcesso = $scope.page.list[index];
            if (confirm('Deseja excluir o perfilAcesso ' + perfilAcesso.nome + '?')) {
                $scope.removerServidor(perfilAcesso);
            }
        };

        $scope.removerServidor = function(perfilAcesso) {
            console.log(JSON.stringify(perfilAcesso));
            $http({
                method: "DELETE",
                url: "./perfilAcesso/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(perfilAcesso))
            }).success(function(data, status) {
                $scope.loadPerfilAcessos($scope.page.pageNumber);
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir o PerfilAcesso!");
            });
        };

        $scope.loadPerfilAcessos = function(numeroPagina) {
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
                alert("Erro ao buscar PerfilAcesso!");
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
                alert("Erro ao buscar PerfilAcesso!");
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
                alert("Erro ao Salvar PerfilAcesso!");
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
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao Salvar PerfilAcesso!");
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
                            title: 'Erro ao carregar questão ' + id,
                            width: 800,
                            height: 450,
                            modal: true
                        });
            });
        };

    }
]);