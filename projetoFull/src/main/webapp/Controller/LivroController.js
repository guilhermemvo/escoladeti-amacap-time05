var moduleAluno = angular.module('escoladeti.livro', ['angularFileUpload', 'ngRoute']);

moduleAluno.controller('LivroController', ['$scope', '$http', '$rootScope', '$routeParams','growl' ,'$upload', function($scope, $http, $rootScope, $routeParams,growl ,$upload) {

        $scope.message = 'Controller de Livro!';
        $scope.page = {pageNumber: 0};
        $scope.material = [];
        $scope.search = "";
        $scope.nome = "Livro";
        $scope.form = "livro";
        $scope.listaDisciplina = [];
        $scope.caminholistar = "./View/Material/Livro/listarLivro.html";
        $scope.caminhoeditar = "./View/Material/Livro/editarLivro.html";
        $scope.caminhoinicial = "#/Material/Livro/";
        $scope.caminhorelatorio = "livro/relatorio/listaLivro";
        $scope.displayBtnRelatorio = "inline";
        $scope.listatipos = [];
        $scope.listamateriaprima = [];
        $scope.arq = [];
        $scope.arquivos = [];
        $scope.materiaprima = [];
        $scope.livro = [];
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
            $scope.arq.origem = "";
            $scope.arq.observacao = "";
            $scope.arq.tipomaterial = [];
        };

        $scope.validaArquivo = function() {
            return  $scope.arq.origem &&
                    //$scope.arq.observacao &&
                    $scope.arq.tipomaterial.id;
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
            $scope.arq = $scope.material.arquivos[index];
            $('#modalArquivos').modal('show');
            $scope.material.arquivos.splice(index, 1);
        };

        $scope.delArquivo = function(index) {
            $scope.material.arquivos.splice(index, 1);
        };

        $scope.addArquivo = function() {
            $scope.material.arquivos.push({origem: $scope.arq.origem.name, observacao: $scope.arq.observacao, tipomaterial: $scope.arq.tipomaterial});
        };

        $scope.loadTodosTipo = function() {
            $http({
                method: "GET",
                url: "./tipomaterial/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.listatipos = data;
            }).error(function(data, status) {
                growl.error("Não foi possível listar os Tipos de Material!");
            });
        };

        $scope.incluir = function() {
            window.location.href = "#Material/Livro/Novo/Editar";
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
                    type: "livro"
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.livroDadoId($routeParams.objeto);
            }
            $scope.loadTodasDisciplinas();
            $scope.loadTodosTipo();
            $scope.loadTodosMateriaPrima();
        };
        $scope.loadTodasDisciplinas = function() {
            $http({
                method: "GET",
                url: "./disciplina/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(status));
                $scope.listaDisciplina = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível listar as Disciplinas!");
            });
        };
        $scope.remover = function(index) {
            if (index === '') {
                var excluir = $scope.material;
            } else {
                var excluir = $scope.page.list[index];
                $scope.livro = $scope.page.list[index];
            }
            $rootScope.confirmaExclusao(excluir, 'nome');
        };

        $scope.removerServidor = function(livro) {
            $http({
                method: "DELETE",
                url: "./livro/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(livro))
            }).success(function(data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                if (status == 500) {
                    growl.error("Não foi possível excluir o Livro. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir o Livro!");
                }

            });
        };

        $scope.load = function(origemPagina) {
            $http({
                method: "GET",
                url: "./livro/listar/pag/" + origemPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                $scope.page = formatPageList(data);
                console.log($scope.material);
            }).error(function(data, status) {
                console.log(data);

            });
        };

        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./livro/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson($scope.material)
            }).success(function(data, status) {
                window.location.href = $scope.caminhoinicial;
                $scope.upload();
                console.log(JSON.stringify(status));
                growl.success("Cadastro efetuado com sucesso!");
            }).error(function(data, status) {
                console.log(JSON.stringify(status));
                console.log(angular.toJson($scope.material));
                growl.error("Não foi possível efetuar o cadastro!");
            });
        };

        $scope.livroDadoId = function(id) {
            $http({
                method: "GET",
                url: "./livro/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log($scope.material);
                $scope.material = data;
                console.log(data);
                $scope.loadTodosMateriaPrima();
            }).error(function(data, status) {
                $("<div></div>")
                        .css({overflow: "auto"})
                        .html(data)
                        .dialog({
                            autoOpen: true,
                            title: 'Erro ao carregar a livro',
                            width: 800,
                            height: 450,
                            modal: true
                        });
            });
        };

        $scope.procurarPorNome = function(nome) {
            if (nome != '') {
                $http({
                    method: "GET",
                    url: "./livro/procurarPorNome/" + nome,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function(data, status) {
                    $scope.page = data;
                }).error(function(data, status) {
                    growl.info("Não foi possível localizar o Livro!");
                });
            } else {
                $scope.load(0);
            }
        };

    }
]);
