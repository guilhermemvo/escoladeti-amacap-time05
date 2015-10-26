var moduleAluno = angular.module('escoladeti.volume', ['ngRoute']);

moduleAluno.controller('VolumeController', ['$scope', '$http', '$rootScope', '$routeParams', function($scope, $http, $rootScope, $routeParams) {

        $scope.message = 'Controller de Volume!';
        $scope.nome = "Volume";
        $scope.form = "volume";
        $scope.listaTransportadoras = [];
        $scope.listaUnidadesProducao = [];
        $scope.listaDeVolumes = [];
        $scope.statusVolume = [];
        $scope.notificacao = "";
        $scope.index = 0;
        $scope.nomeMaterial = "";

//        $scope.statusVolume = [
//            {id: 1, descricao: "Aguardando Produção"},
//            {id: 2, descricao: "Produzindo"},
//            {id: 3, descricao: "Aguardando Revisão"},
//            {id: 4, descricao: "Revisando"},
//            {id: 5, descricao: "Aguardando Impressão"},
//            {id: 6, descricao: "Imprimindo"},
//            {id: 7, descricao: "Aguardando Envio"},
//            {id: 8, descricao: "Enviado"}
//        ];

        $rootScope.carregaVolumesMaterial = function(index) {

            $scope.index = index;
            $scope.nomeMaterial = $scope.pedido.pedidoMaterial[$scope.index].material.nome;

            $scope.listaDeVolumes = $scope.pedido.pedidoMaterial[$scope.index].volumes !== [] ? $scope.pedido.pedidoMaterial[$scope.index].volumes : [];
            $scope.carregaTransportadoras();
            $scope.carregaUnidadeProducao();
        };

        $rootScope.confirmarVolumes = function(volumes) {
            $scope.pedido.pedidoMaterial[$scope.index].volumes = volumes;
        };

        $scope.carregaTransportadoras = function() {
            $http({
                method: "GET",
                url: "./transportadora/todas",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listaTransportadoras = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar os Tipos de Telefone!");
            });
        };

        $scope.carregaUnidadeProducao = function() {
            $http({
                method: "GET",
                url: "./unidadeProducao/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listaUnidadesProducao = data;
            }).error(function(data, status) {
            });
        };

        $scope.addVolume = function() {
            if ($scope.validaVolumes() || $scope.listaDeVolumes.length === 0) {
                $scope.listaDeVolumes.push({
                    id: "",
                    identificacao: "Volume " + ($scope.listaDeVolumes.length + 1),
                    status: 1,
                    paginaInicial: $scope.listaDeVolumes.length === 0 ? 0 : $scope.listaDeVolumes[$scope.listaDeVolumes.length - 1].paginaFinal + 1,
                    paginaFinal: $scope.listaDeVolumes.length === 0 ? 0 : $scope.listaDeVolumes[$scope.listaDeVolumes.length - 1].paginaFinal + 1,
                    transportadora: "",
                    unidadeProducao: ""
                });
            }
        };

        $scope.deletaVolume = function(index) {
            $scope.listaDeVolumes.splice(index, 1);
        };

        $scope.validaVolumes = function() {
            $scope.notificacao = "";
            for (var i = 0; i < $scope.listaDeVolumes.length; i++) {

                var identificacaoInvalida = $scope.listaDeVolumes[i].identificacao === "";
                var identificacaoPgInicial = $scope.listaDeVolumes[i].paginaInicial === "";
                var identificacaoPgFinal = $scope.listaDeVolumes[i].paginaFinal === "";
                var identificacaoTransportadora = $scope.listaDeVolumes[i].transportadora === "";
                var identificacaoUnProducao = $scope.listaDeVolumes[i].unidadeProducao === "";

                if (identificacaoInvalida || identificacaoPgInicial || identificacaoPgFinal || identificacaoTransportadora || identificacaoUnProducao) {
                    $scope.notificacao = "Existe informações não preenchida. Por favor verifique!";
                    return false;
                }

                if ($scope.listaDeVolumes[i].paginaInicial === 0) {
                    $scope.notificacao = "Pagina inicial não informado.Por favor verifique!";
                    return false;
                }

                if ($scope.listaDeVolumes[i].paginaFinal === 0) {
                    $scope.notificacao = "Pagina final não informado.Por favor verifique!";
                    return false;
                }

                if ($scope.listaDeVolumes[i].paginaInicial > $scope.listaDeVolumes[i].paginaFinal) {
                    $scope.notificacao = "Pagina inicial não pode ser maior que a pagina final.Por favor verifique!";
                    return false;
                }
                
                if (!$scope.validaVolumesRepetidos()){
                    return false;
                }
            }
            return $scope.listaDeVolumes.length > 0;
        };

        $scope.validaVolumesRepetidos = function() {
            for (var i = 0; i < $scope.listaDeVolumes.length - 1; i++) {
                for (var j = i + 1; j < $scope.listaDeVolumes.length; j++) {
                    if ($scope.listaDeVolumes[i].paginaInicial === $scope.listaDeVolumes[j].paginaInicial) {
                        $scope.notificacao = "Pagina inicial já informada. Por favor verifique!";
                        return false;
                    }
                    if ($scope.listaDeVolumes[i].paginaFinal === $scope.listaDeVolumes[j].paginaFinal) {
                        $scope.notificacao = "Pagina final já informada. Por favor verifique!";
                        return false;
                    }
                }
            }
            return true;
        };
    }
]);
