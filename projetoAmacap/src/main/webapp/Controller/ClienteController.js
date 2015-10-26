/**
 *
 * @author Paulo Henrique
 */

var moduleCliente = angular.module('escoladeti.cliente', ['ngRoute']);

moduleCliente.controller('ClienteController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {
        
        $scope.message = 'Controller dos Usuários!';
        $scope.page = {pageNumber: 0};
        $scope.clienteAtual = undefined;
        $scope.search = "";

        $scope.init = function() {
            $scope.loadClientes($scope.page.pageNumber);
        };

        $scope.incluir = function() {
            window.location.href = "#Pessoa/Cliente/Novo";
        };

        $scope.initEditarCliente = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.clienteAtual = {
                    tipo: "",
                    tipoPessoa: "",
                    cpf: "",
                    rg: "",
                    nome: "",
                    apelidoFantasia:"",
                    nomeRazao:"",
                    dataNascimento:"",
                    sexo:"",
                    cnpj:""
                    
                };
            } else {
                $scope.clienteDadoId($routeParams.objeto);
            }
            $scope.loadTodosEstados();
        };

        $scope.remover = function(index) {
            var cliente = $scope.page.list[index];
            if (confirm('Deseja excluir a usuário ' + cliente.nome + '?')) {
                $scope.removerServidor(cliente);
            }
        };
        $scope.removerServidor = function(cliente) {
            console.log(JSON.stringify(cliente));
            $http({
                method: "DELETE",
                url: "./pessoa/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(cliente))
            }).success(function(data, status) {
                $scope.loadClientes($scope.page.pageNumber);
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir usuário!");
            });
        };

        $scope.loadClientes = function(numeroPagina) {
            $http({
                method: "GET",
                url: "./pessoa/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.page = data;
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar Clientes!");
            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./pessoa/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.clienteAtual)
            }).success(function(data, status) {
                window.location.href = "#Localizacao/Cliente/listar";
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                console.log(JSON.stringify($scope.clienteAtual));
                alert("Erro ao salvar usuários!");
            });
        };

        $scope.clienteDadoId = function(id) {
            $http({
                method: "GET",
                url: "./pessoa/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.clienteAtual = data;
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
        //console.log($scope);

    }
]);