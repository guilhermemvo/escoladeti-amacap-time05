var moduleAluno = angular.module('escoladeti.outros', ['angularFileUpload', 'ngRoute']);

moduleAluno.controller('OutrosController', ['$scope', '$http', '$rootScope', '$routeParams','growl' ,'$upload', function($scope, $http, $rootScope,$routeParams,growl,$upload) {

        $scope.message = 'Controller de Outros!';
        $scope.page = {pageNumber: 0};
        $scope.material = [];
        $scope.search = "";
        $scope.nome = "Outros";
        $scope.form = "outros";
        $scope.caminholistar = "./View/Material/Outros/listarOutros.html";
        $scope.caminhoeditar = "./View/Material/Outros/editarOutros.html";
        $scope.caminhoinicial = "#/Material/Outros/";
        $scope.caminhorelatorio = "outros/relatorio/listaOutros";
        $scope.displayBtnRelatorio = "inline";
        $scope.listatipos = [];
        $scope.listamateriaprima = [];
        $scope.arquivo = [];
        $scope.arquivos = [];
        $scope.materiaprima = [];
        $scope.selectedFiles = {};

        $scope.onFileSelect = function($files) {
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

        $scope.remover = function(filename) {
            delete $scope.selectedFiles[filename];
        };

        $scope.upload = function() {
            console.log("upload");

            for (var filename in $scope.selectedFiles) {
                console.log("uploadAtual");
                $scope.uploadAtual = $upload.upload({
                    url: './rest/uploadRespostasDiscursivas/upload',
                    file: $scope.selectedFiles[filename].file
                }).progress(function(evt) {
                    console.log(parseInt(100 * evt.loaded / evt.total));
                }).success(function(data, status) {
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
                }).error(function() {
                    for (var filename in $scope.selectedFiles) {
                        $scope.selectedFiles[filename].status = "danger";
                        $scope.selectedFiles[filename].message = "Arquivo esta corrompido, removido ou excluido";
                    }
                });
            }
            console.log("Upload concluido!");
        };

        $scope.novaMateriaPrima = function() {
            $scope.materiaprima.quantidade = "";
            $scope.materiaprima.materiaprima = [];
            $scope.materiaprima.tipomaterial = [];
        };

        $scope.validaMateriaPrima = function() {
            return  $scope.materiaprima.quantidade &&
                    $scope.materiaprima.materiaprima.id &&
                    $scope.materiaprima.tipomaterial.id;
        };

        $scope.novoArquivo = function() {
            $scope.arquivo.origem = "";
            $scope.arquivo.observacao = "";
            $scope.arquivo.tipomaterial = [];
        };

        $scope.validaArquivo = function() {
            return  $scope.arquivo.origem &&
                   // $scope.arquivo.observacao &&
                    $scope.arquivo.tipomaterial.id;
        };

        $scope.editarMaterialPrima = function(index) {
            $scope.materiaprima = $scope.material.materiaprima[index];
            $('#modalMateriaPrima').modal('show');
            $scope.material.materiaprima.splice(index, 1);
            $scope.loadTodosMateriaPrima();
        };

        $scope.delMateriaPrima = function(index) {
            $scope.material.materiaprima.splice(index, 1);
            $scope.loadTodosMateriaPrima();
        };

        $scope.addMateriaPrima = function() {
            $scope.material.materiaprima.push({quantidade: $scope.materiaprima.quantidade, materiaprima: $scope.materiaprima.materiaprima, tipomaterial: $scope.materiaprima.tipomaterial});
            $scope.loadTodosMateriaPrima();
        };

        $scope.loadTodosMateriaPrima = function() {
            $http({
                method: "GET",
                url: "./materiaprima/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listamateriaprima = data;
                for (var i in $scope.listamateriaprima) {
                    for (var x in $scope.material.materiaprima) {
                        console.log($scope.material.materiaprima[x].materiaprima.id);
                        if ($scope.listamateriaprima[i].id == $scope.material.materiaprima[x].materiaprima.id) {
                            $scope.listamateriaprima.splice(i, 1);
                        }
                    }
                }
            }).error(function(data, status) {
                growl.error("Não foi possível listar as Matérias-primas!");
            });
        };

        $scope.editarArquivo = function(index) {
            $scope.arquivo = $scope.material.arquivos[index];
            $('#modalArquivos').modal('show');
            $scope.material.arquivos.splice(index, 1);
        };

        $scope.delArquivo = function(index) {
            $scope.material.arquivos.splice(index, 1);
        };

        $scope.addArquivo = function() {
            $scope.material.arquivos.push({origem: $scope.arquivo.origem.name, observacao: $scope.arquivo.observacao, tipomaterial: $scope.arquivo.tipomaterial});
        };

        $scope.loadTodosTipo = function() {
            $http({
                method: "GET",
                url: "./tipomaterial/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listatipos = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar os Tipos!");
            });
        };

        $scope.incluir = function() {
            window.location.href = "#Material/Outros/Novo/Editar";
        };
        $scope.init = function() {
            $scope.load(0);
            $scope.loadTodosTipo();
            $scope.loadTodosMateriaPrima();
        };
        $scope.initEditar = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.material = {
                    id: "",
                    arquivos: [],
                    materiaprima: [],
                    type: "outros"
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.outrosDadoId($routeParams.objeto);
            }
            $scope.loadTodosTipo();
            $scope.loadTodosMateriaPrima();
        };

        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.material;
            } else {
                var excluir = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'nome');
        };

        $scope.removerServidor = function(outros) {
            $http({
                method: "DELETE",
                url: "./outros/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(outros))
            }).success(function(data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function(data, status) {
                //console.log(JSON.stringify(data));
                // console.log(JSON.stringify(status));
                if (status == 500) {
                    growl.error("Não foi possível excluir o Material. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir o Material!");
                }

            });
        };

        $scope.load = function(origemPagina) {
            $http({
                method: "GET",
                url: "./outros/listar/pag/" + origemPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = data;
                //console.log($scope.material);
            }).error(function(data, status) {
                // console.log(data);

            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./outros/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson($scope.material)
            }).success(function(data, status) {
                window.location.href = $scope.caminhoinicial;
                $scope.upload();
                //  console.log(JSON.stringify(status));
                growl.success("Cadastro efetuado com sucesso!");
            }).error(function(data, status) {
                //  console.log(JSON.stringify(status));
                //   console.log(angular.toJson($scope.material));
                growl.error("Não foi possível efetuar o cadastro!");
            });
        };

        $scope.outrosDadoId = function(id) {
            $http({
                method: "GET",
                url: "./outros/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                //    console.log($scope.material);
                $scope.material = data;
                $scope.loadTodosMateriaPrima();
            }).error(function(data, status) {
                $("<div></div>")
                        .css({overflow: "auto"})
                        .html(data)
                        .dialog({
                            autoOpen: true,
                            title: 'Erro ao carregar a outros',
                            width: 800,
                            height: 450,
                            modal: true
                        });
            });
        };

    }
]);