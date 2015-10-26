var moduleEvento = angular.module('escoladeti.galeriaEvento', ['angularFileUpload', 'ngRoute']);

moduleEvento.controller('EventoGaleriaController', ['$scope', '$http', '$rootScope', '$routeParams', 'growl', '$upload', function ($scope, $http, $rootScope, $routeParams, growl, $upload) {

        $scope.message = 'Controller de Fotos do Evento!';
        $scope.page = {pageNumber: 0};
        $scope.galeriaAtual = [];
        $scope.nome = "Fotos";
        $scope.form = "evento";
        $scope.caminholistar = "./View/Operacao/Evento/listarEvento.html";
        $scope.caminhoeditar = "./View/Operacao/Evento/galeriaEvento.html";
        $scope.caminhoinicial = "#Operacao/Evento/";
        $scope.arq = [];
        $scope.evento = [];
        $scope.arquivos = [];
        $scope.selectedFiles = {};
        $scope.caminho = "";
        $scope.initEditar = function () {
            if ($routeParams.objeto === 'Novo') {
                $scope.eventoAtual = {
                    id: "",
                    nome: "",
                    quantidade_vagas: "",
                    descricao: "",
                    cargaHoraria: "",
                    data_inicio: "",
                    data_fim: "",
                    valor: "",
                    local_evento: "",
                    instrutor: "",
                    fotos: [],
                    agendaEvento: [],
                    enderecos: []
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.eventoDadoId($routeParams.objeto);
            }
        };
        $scope.salvar = function () {
            $http({
                method: "POST",
                url: "./evento/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson($scope.eventoAtual)
            }).success(function (data, status) {
                $scope.upload();
                window.location.href = "#Operacao/Evento/";
                growl.success("Cadastro efetuado com sucesso!");
            }).error(function (data, status) {
                console.log(JSON.stringify($scope.eventoAtual));
                growl.error("Não foi possível efetuar o cadastro!");
            });
        };

        $scope.eventoDadoId = function (id) {
            $http({
                method: "GET",
                url: "./evento/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.eventoAtual = data;
                $scope.rootPath();
            }).error(function (data, status) {
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
        $scope.rootPath = function () {
            $http({
                method: "GET",
                url: "./rest/uploadRespostasDiscursivas/root",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.root = data;
            });
        };

        $scope.onFileSelect = function ($files) {
            if (angular.isUndefined($scope.selectedFiles))
                $scope.selectedFiles = {};

            for (var i = 0; i < $files.length; i++) {
                $scope.selectedFiles[$files[i].name] = {
                    'file': $files[i],
                    status: "info",
                    message: "Aguardando envio."
                };
            }
            console.log("selectedFiles...");
            console.log($scope.selectedFiles);
        };

        $scope.remover = function (filename) {
            delete $scope.selectedFiles[filename];
        };

        $scope.upload = function () {
            console.log("upload");

            for (var filename in $scope.selectedFiles) {
                console.log("uploadAtual");
                console.log($scope.selectedFiles[filename].file);
                $scope.uploadAtual = $upload.upload({
                    url: './rest/uploadRespostasDiscursivas/upload',
                    file: $scope.selectedFiles[filename].file
                }).progress(function (evt) {
                    console.log(parseInt(100 * evt.loaded / evt.total));
                }).success(function (data, status) {
                    console.log("data:");
                    console.log(data);
                    var result = data;
                    console.log($scope.selectedFiles[filename].file);

                    if (angular.isUndefined(result)) {
                        alert("Ocorreu alguma falha grave no envio.");
                        return;
                    }

                    var selectionResult = $scope.selectedFiles[result.filename];
                    selectionResult.status = result.status.toLowerCase();
                    selectionResult.message = result.message;
                }).error(function () {
                    for (var filename in $scope.selectedFiles) {
                        $scope.selectedFiles[filename].status = "danger";
                        $scope.selectedFiles[filename].message = "Arquivo esta corrompido, removido ou excluido";
                    }
                });
            }
            console.log("Upload concluido!");
        };
        $scope.novoArquivo = function () {
            $scope.arq.origem = "";
            $scope.arq.descricao = "";
        };

        $scope.validaArquivo = function () {
            return  $scope.arq.origem;
        };
        $scope.editarArquivo = function (index) {
            $scope.arq = $scope.eventoAtual.fotos[index];
            $('#modalArquivos').modal('show');
            $scope.eventoAtual.fotos.splice(index, 1);
        };

        $scope.deletarArquivo = function (arquivo) {
            $http({
                method: "POST",
                url: "./rest/uploadRespostasDiscursivas/delete/",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson(arquivo)
            }).success(function (data, status) {
                console.log("arquivo apagado:" + arquivo);
            });
        };

        $scope.delArquivo = function (index) {
            $scope.deletarArquivo($scope.eventoAtual.fotos[index].origem);
            $scope.eventoAtual.fotos.splice(index, 1);

        };

        $scope.addArquivo = function () {
            $scope.eventoAtual.fotos.push({origem: $scope.arq.origem.name, descricao: $scope.arq.descricao});
            $scope.upload();
        };

    }
]);
