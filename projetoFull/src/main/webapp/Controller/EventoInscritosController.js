var moduleEvento = angular.module('escoladeti.inscritos', ['ngRoute']);

moduleEvento.controller('EventoInscritosController', ['$scope', '$http', '$rootScope', '$routeParams', 'growl', function($scope, $http, $rootScope, $routeParams, growl) {

        $scope.message = 'Controller de Inscrição de Participantes no Evento!';
        $scope.page = {pageNumber: 0};
        $scope.inscritoAtual = [];
        $scope.search = "";
        $scope.listapessoas = [];
        $scope.nome = "Participantes";
        $scope.form = "evento";
        $scope.caminholistar = "./View/Operacao/Evento/listarEvento.html";
        $scope.caminhoeditar = "./View/Operacao/Evento/inscricaoEvento.html";
        $scope.caminhoinicial = "#Operacao/Evento/";
        $scope.participantes = [];
        $scope.daoInscrito = {id: "", idPessoa: "", idEvento: ""};

        $scope.loadTodasPessoas = function() {
            $http({
                method: "GET",
                url: "./pessoafisica/todas",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listapessoas = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar as Pessoas!");
            });
        };

        $scope.initEditar = function() {
            $scope.inscritoAtual = {
                id: "",
                pessoa: "",
                evento: ""
            };
            $scope.loadTodasPessoas();
            $scope.eventoDadoId($routeParams.objeto);
            $scope.carregaParticipantes($routeParams.objeto);
        };

        $scope.eventoDadoId = function(id) {
            $http({
                method: "GET",
                url: "./evento/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.inscritoAtual.evento = data;
            }).error(function(data, status) {
                $("<div></div>")
                        .css({overflow: "auto"})
                        .html(data)
                        .dialog({
                            autoOpen: true,
                            title: 'Erro ao carregar evento ' + id,
                            width: 800,
                            height: 450,
                            modal: true
                        });
            });
        };

        $scope.addParticipante = function() {
            $scope.daoInscrito.id = undefined;
            $scope.daoInscrito.idPessoa = $scope.inscritoAtual.pessoa.id;
            $scope.daoInscrito.idEvento = $scope.inscritoAtual.evento.id;

            $http({
                method: "POST",
                url: "./inscricao/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson($scope.daoInscrito)
            }).success(function(data, status) {
                $scope.initEditar();
                growl.success("Cadastro efetuado com sucesso!");
            }).error(function(data, status) {
                console.log(angular.toJson($scope.inscritoAtual));
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
            });
        };

        $scope.salvar = function() {
            var atualizar = [];
            for (i = 0; i < $scope.participantes.length; i++) {
                atualizar.push({id: $scope.participantes[i].id, pago: $scope.participantes[i].pago});
            }

            $http({
                method: "POST",
                url: "./inscricao/atualizarInscricao",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson(atualizar)
            }).success(function(data, status) {
                window.location.href = $scope.caminhoinicial;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
            });
        };

        $scope.carregaParticipantes = function(id) {
            $http({
                method: "POST",
                url: "./inscricao/participantes",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson(id)
            }).success(function(data, status) {
                $scope.participantes = data;
                if ($scope.participantes.length === 0) {
                    $(".form-control.btn.btn-success").css("display", "none");
                } else {
                    $(".form-control.btn.btn-success").css("display", "");
                }
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
            });
        };

        $scope.remover = function(index) {
            var excluir = $scope.participantes[index];
            excluir.nome = excluir.pessoa.nome;
            $rootScope.confirmaExclusao(excluir, "nome");
        };

        $scope.removerServidor = function(inscricao) {
            $http({
                method: "DELETE",
                url: "./inscricao/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(inscricao.id))
            }).success(function(data, status) {
                $scope.initEditar();
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                if (status === 500) {
                    alert("Não foi possível excluir a inscrição!\n\
    Inscrito possui outras referências no sistema");

                } else {
                    alert("Não foi possível excluir o registro!");
                }
            });
        };

        $scope.atualizarInscricoes = function() {
            $http({
                method: "POST",
                url: "./inscricao/atualizarInscricao",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson($scope.participantes)
            }).success(function(data, status) {
                window.location.href = $scope.caminhoinicial;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar os Participantes!");
            });
        };

        $scope.validarQuantidadeVagas = function() {
            var vagasEsgotadas = $scope.participantes.length === $scope.inscritoAtual.evento.quantidade_vagas;
            if (vagasEsgotadas) {
                $('#adicionarNovoParticipante').val("Esgotado!");
            } else {
                $('#adicionarNovoParticipante').val("Inscrever novo participante")
            }

            return vagasEsgotadas;
        };
    }
]);


