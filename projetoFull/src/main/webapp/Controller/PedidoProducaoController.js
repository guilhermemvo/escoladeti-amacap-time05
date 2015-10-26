var modulePedido = angular.module('escoladeti.pedidoProducao', ['ngRoute', 'escoladeti.pedidoService', 'escoladeti.validacaoService']);

modulePedido.controller('PedidoProducaoController', ['$scope', '$http', '$rootScope', '$routeParams', 'pedidoService',
    '$timeout', 'validacaoService', 'growl', function ($scope, $http, $rootScope, $routeParams, pedidoService, $timeout, validacaoService, growl) {

        $scope.message = 'Controller dos Materiais em Produção!';
        $scope.page = {pageNumber: 0};
        $scope.pedido = undefined;
        $scope.search = "";
        $scope.listaPedidos = [];
        $scope.nome = "Materiais em Produção";
        $scope.form = "MaterialProducao";
        $scope.caminholistar = "./View/Pedido/Producao/listarPedido.html";
        $scope.caminhoinicial = "#/Pedido/Producao/";
        $scope.pedidoMaterialAtual = undefined;

        $scope.materialAtual = undefined;
        $scope.listapedido = [];
        $scope.listaStatusProducao = [
            {id: 1, descricao: "Aguardando Produção"},
            {id: 2, descricao: "Produzindo"},
            {id: 3, descricao: "Aguardando Revisão"},
            {id: 4, descricao: "Revisando"},
            {id: 5, descricao: "Aguardando Impressão"},
            {id: 6, descricao: "Imprimindo"},
            {id: 7, descricao: "Aguardando Envio"},
            {id: 8, descricao: "Enviado"},
            {id: 9, descricao: "Cancelado"}
        ];

        $scope.incluir = function () {
            window.location.href = "#Pedido/Pedido/Novo/Editar";
        };
        $scope.init = function () {
            $scope.load(0);
        };
        $scope.gerenciarVolumes = function (index) {
            $scope.pedidoMaterialAtual = $scope.page.list[index];
            console.log($scope.pedidoMaterialAtual);
             $('#modalVisualizarVolumes').modal('show');
        };
        $scope.load = function (origemPagina) {
            $http({
                method: "GET",
                url: "./pedidomaterial/listar/pag/" + origemPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.page = formatPageList(data);
            }).error(function (data, status) {
                console.log(data);

            });
        };
    }
]);

