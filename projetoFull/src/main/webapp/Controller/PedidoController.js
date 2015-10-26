var modulePedido = angular.module('escoladeti.pedido', ['ngRoute', 'escoladeti.pedidoService']);

modulePedido.controller('PedidoController', ['$scope', '$http', '$rootScope', '$routeParams', 'pedidoService', '$timeout', '$location', 'growl', function ($scope, $http, $rootScope, $routeParams, pedidoService, $timeout, $location, growl) {

        $scope.message = 'Controller do Pedido!';
        $scope.page = {pageNumber: 0};
        $scope.pedido = undefined;
        $scope.search = "";
        $scope.nome = "Pedido";
        $scope.form = "Pedido";
        $scope.listapedido = [];
        $scope.tipoMaterial;
        $scope.consultaMaterial = "";
        $scope.listaprofessor = [];
        $scope.listaaluno = [];
        $scope.caminholistar = "./View/Pedido/Pendente/listarPedido.html";
        $scope.caminhoeditar = "./View/Pedido/Pedido/editarPedido.html";
        $scope.caminhoinicial = "#/Pedido/Pendente/";
        $scope.listaEscola = [];
        $scope.consultaEscolaNome = "";
        $scope.materialAtual = "";
        $scope.consultaSolicitanteNome = "";
        $scope.solicitante = '';
        $scope.aluno = "";
        $scope.professor = false;
        $scope.escola = '';
        $scope.listaEscolas = [];
        $scope.qtdMaterial = 0;
        $scope.listatipos = [];
        $scope.telefones = [];
        $scope.listaBairro = [];
        $scope.emails = [];
        $scope.listaLogradouro = [];
        $scope.enderecos = [];
        $scope.listaCidade = [];
        $scope.listaEstados = [];
        $scope.listaPaises = [];
        $scope.listaTipoMaterial = [];
        $scope.listaMaterial = [];


        $scope.listaTipoLivroOutros = [
            {id: '1', descricao: 'Braile'},
            {id: '2', descricao: 'Tinta'}
        ];
        $scope.incluir = function () {
            window.location.href = "#Pedido/Pedido/Novo/Editar";
        };
        $scope.init = function () {
            $scope.load(0);
            $scope.loadTodosPaises();
            $scope.loadTodosTipoDeMaterial();
            $scope.fecharEscola();
            $scope.loadTodosTipo();
            $scope.loadTodosDisciplina();
            $scope.getCountPendentes();
            $scope.pedido.pedidoMaterial = [];
            $scope.pedido.solicitante = {enderecos: [], telefones: [], emails: []};
            $scope.pedido.escola = {enderecos: [], telefones: [], emails: []};
            $scope.pedido.professor = {enderecos: [], telefones: [], emails: []};
            $scope.pedido.aluno = {enderecos: [], telefones: [], emails: []};
            
            $scope.loadTodosPedido();

        };
        $scope.initEditar = function () {
            if ($routeParams.objeto === 'Novo') {
                $scope.pedido = {
                    id: "",
                    pedidoMaterial: []
                };
                $scope.pedido.pedidoMaterial = [];
                $scope.pedido.solicitante = {enderecos: [], telefones: [], emails: []};
                $scope.pedido.escola = {enderecos: [], telefones: [], emails: []};
                $scope.pedido.professor = {enderecos: [], telefones: [], emails: []};
                $scope.pedido.aluno = {enderecos: [], telefones: [], emails: []};
               
                $scope.loadTodosPedido();

            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.pedidoDadoId($routeParams.objeto);
            }
            $scope.loadTodosPedido();
            $scope.loadListaMaterial();
            $scope.loadTodosTipoDeMaterial();
        };
        $scope.loadTodosPedido = function () {
            pedidoService
                    .getAllPedido()
                    .success(function (data) {
                        $scope.listapedido = data;
                    });
        };

        $scope.remover = function (index) {
            if (index === '') {
                var excluir = $scope.pedido.id;
            } else {
                var excluir = $scope.page.list[index].id;
            }
            console.log(excluir);
            $rootScope.confirmaExclusao(excluir, 'descricao');
        };

        $scope.removerServidor = function (pedido) {

            pedidoService
                    .deletePedido(pedido)
                    .success(function (status) {
                        window.location.href = $scope.caminhoinicial;
                    });
        };

        $scope.load = function (numeroPagina) {
            $scope.loadTodosPedido();
            pedidoService
                    .getPagePedido(numeroPagina)
                    .success(function (data) {
                        $scope.page = data;

                    });

        };

        $scope.salvarAluno = function () {
            pedidoService
                    .saveAluno($scope.pedido.aluno)
                    .success(function (data) {
                        $scope.pedido.aluno = data;
                        $timeout(function () {
                            console.log($scope.pedido.aluno);
                            $scope.fecharAluno();
                            $scope.consultaAluno($scope.pedido.aluno.cpf);
                        }, 500);
                    });

        };

        $scope.salvarSolicitante = function () {
            pedidoService
                    .saveSolicitante($scope.pedido.solicitante)
                    .success(function (data) {
                        $scope.fecharSolicitante();
                        $scope.pedido.solicitante = data;
                        $timeout(function () {
                            console.log($scope.pedido.solicitante);
                            $scope.solicitante = false;
                            $scope.consultaCPF($scope.pedido.solicitante.cpf);
                        }, 500);
                    });

        };
        $scope.salvarEscola = function () {
            console.log($scope.pedido.escola);
            pedidoService
                    .saveEscola($scope.pedido.escola)
                    .success(function (data) {
                        $scope.pedido.escola = data;
                        $timeout(function () {
                            console.log($scope.pedido.escola);
                            $scope.fecharEscola();
                            $scope.consultaEscola($scope.pedido.escola.cnpj);
                        }, 500);
                    });
        };

        $scope.salvarProfessor = function () {
            console.log($scope.pedido.professor);
            $scope.pedido.professor.escola = $scope.pedido.escola;

            pedidoService
                    .saveProfessor($scope.pedido.professor)
                    .success(function (data) {
                        $scope.pedido.professor = data;
                        $timeout(function () {
                            console.log($scope.pedido.professor);
                            $scope.fecharProfessor();
                            $scope.consultaProfessor($scope.pedido.professor.cpf);
                        }, 500);
                    });

        };

        $scope.validarPedido = function () {
            if (!$scope.verificarSeExisteDadosEmSolicitante()) {
                delete $scope.pedido.solicitante;
            }
            if (!$scope.verificarSeExisteDadosEmEscola()) {
                delete $scope.pedido.escola;
            }
            if (!$scope.verificarSeExisteDadosEmAluno()) {
                delete $scope.pedido.aluno;
            }
            if (!$scope.verificarSeExisteDadosEmProfessor()) {
                delete $scope.pedido.professor;
            }

        };

        $scope.salvarPedido = function () {

            $scope.validarPedido();
            pedidoService.savePedido($scope.pedido);
            $scope.load(0);
            window.location.href = $scope.caminhoinicial;


        };

        $scope.salvar = function () {
            console.log($scope.pedido);
            $scope.validarPedido();
            pedidoService.savePedido($scope.pedido);
            $scope.load(0);
            window.location.href = $scope.caminhoinicial;
            $scope.loadTodosPedido();
        };
        $scope.abrirEscola = function () {
            $scope.esc = true;
            $scope.escola = true;

        };
        $scope.fecharEscola = function () {
            $scope.esc = '';
        };
        $scope.fecharMaterial = function () {
            $scope.material = '';
        };
        $scope.abrirSolicitante = function () {
            $scope.sol = true;
            $scope.solicitante = true;

        };
        $scope.fecharSolicitante = function () {
            $scope.sol = '';
            $scope.solicitante = '';
        };
        $scope.abrirAluno = function () {
            $scope.alu = true;
            $scope.aluno = true;

        };
        $scope.fecharAluno = function () {
            $scope.alu = '';
        };
        $scope.abrirProfessor = function () {
            $scope.pro = true;
            $scope.professor = true;

        };
        $scope.fecharProfessor = function () {
            $scope.pro = '';
        };

        $scope.verificarSeExisteDadosEmSolicitante = function () {
            if ($scope.pedido.hasOwnProperty("solicitante") && $scope.pedido.solicitante !== null) {
                return  $scope.pedido.solicitante.cpf !== undefined &&
                        $scope.pedido.solicitante.cpf !== "" &&
                        $scope.pedido.solicitante.nome !== undefined &&
                        $scope.pedido.solicitante.nome !== "" &&
                        $scope.pedido.solicitante.rg !== undefined &&
                        $scope.pedido.solicitante.rg !== "";
            } else
                return false;
        };

        $scope.verificarSeExisteDadosEmEscola = function () {
            if ($scope.pedido.hasOwnProperty("escola") && $scope.pedido.escola !== null) {
                return $scope.pedido.escola.cnpj !== undefined &&
                        $scope.pedido.escola.cnpj !== "" &&
                        $scope.pedido.escola.ie !== undefined &&
                        $scope.pedido.escola.ie !== "" &&
                        $scope.pedido.escola.fantasia !== undefined &&
                        $scope.pedido.escola.fantasia !== "";
            } else
                return false;
        };

        $scope.verificarSeExisteDadosEmAluno = function () {
            if ($scope.pedido.hasOwnProperty("aluno") && $scope.pedido.aluno !== null) {
                return $scope.pedido.aluno.cpf !== undefined &&
                        $scope.pedido.aluno.cpf !== "" &&
                        $scope.pedido.aluno.nome !== undefined &&
                        $scope.pedido.aluno.nome !== "" &&
                        $scope.pedido.aluno.rg !== undefined &&
                        $scope.pedido.aluno.rg !== "";
            } else
                return false;
        };


        $scope.verificarSeExisteDadosEmProfessor = function () {
            if ($scope.pedido.hasOwnProperty("professor") && $scope.pedido.professor !== null) {
                return  $scope.pedido.professor.cpf !== undefined &&
                        $scope.pedido.professor.cpf !== "" &&
                        $scope.pedido.professor.nome !== undefined &&
                        $scope.pedido.professor.nome !== "" &&
                        $scope.pedido.professor.rg !== undefined &&
                        $scope.pedido.professor.rg !== "";
            } else
                return false;

        };


        $scope.recuperarTodasEscolas = function () {
            pedidoService
                    .getAllEscola()
                    .success(function (data) {
                        $scope.listaEscola = data;
                    });
        };

        $scope.consultaCPF = function (cpf) {
            if (validaCPF(cpf)) {
                $http({
                    method: "GET",
                    url: "./pessoafisica/carregar/cpf/" + cpf,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function (data, status) {
                    if (data.id != null) {
                        $scope.solicitante = false;
                        console.log(data);
                        $scope.pedido.solicitante = data;
                        $scope.pedido.solicitante.cpf = cpf;
                    } else {
                        // $scope.pedido.solicitante = {id: "", enderecos: [], telefones: [], emails: []};
                        $scope.solicitante = true;
                        $scope.loadTodosPaises();
                        $scope.loadTodosTipo();
                        $scope.pedido.solicitante.cpf = cpf;
                    }
                }).error(function (data, status) {
                    //  $scope.pedido.solicitante = {id: "", enderecos: [], telefones: [], emails: []};
                    $scope.solicitante = false;
                    $scope.pedido.solicitante.cpf = cpf;
                    $scope.loadTodosPaises();
                    $scope.loadTodosTipo();
                });
                $scope.pedido.solicitante.cpf = cpf;
            } else {
                $scope.solicitante = false;
            }
            ;

        };
        $scope.consultaAluno = function (cpf) {
            if (validaCPF(cpf) == true) {
                $http({
                    method: "GET",
                    url: "./aluno/carregar/cpf/" + cpf,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function (data, status) {
                    if (data != "") {
                        $scope.aluno = undefined;
                        $scope.pedido.aluno = data;
                        $scope.solicitante = false;
                    } else {
                        $http({
                            method: "GET",
                            url: "./pessoafisica/carregar/cpf/" + cpf,
                            headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                        }).success(function (data, status) {
                            if (data != null) {
                                $scope.pedido.aluno = data;
                                $scope.pedido.aluno.cpf = cpf;
                                $scope.solicitante = false;
                            } else {
                                $scope.pedido.aluno = {id: "", enderecos: [], telefones: [], emails: []};
                                $scope.solicitante = true;
                                $scope.pedido.aluno.cpf = cpf;
                                $scope.loadTodosPaises();
                                $scope.loadTodosTipo();
                            }
                        }).error(function (data, status) {
                            $scope.pedido.aluno = {id: "", enderecos: [], telefones: [], emails: []};
                            $scope.solicitante = false;
                            $scope.pedido.aluno.cpf = cpf;
                            $scope.loadTodosPaises();
                            $scope.loadTodosTipo();
                        });
                    }
                }).error(function (data, status) {
                    alert("Erro ao consultar CPF!");
                });
            } else {
                $scope.aluno = false;
            }
        };

        $scope.consultaProfessor = function (cpf) {
            pedidoService
                    .findProfessorByCpf(cpf)
                    .success(function (data) {
                        if (data != "") {

                            $scope.pedido.professor = data;
                        }
                        else {
                            $scope.pedido.professor = {id: "", enderecos: [], telefones: [], emails: []};
                            $scope.professor = cpf;
                            $scope.pedido.professor.cpf = cpf;
                            $scope.recuperarTodasEscolas();
                            $scope.loadTodosPaises();
                            $scope.loadTodosTipo();
                        }
                    });
        };

        $scope.consultaEscola = function (cnpj) {
            pedidoService
                    .findEscolaByCpnj(cnpj)
                    .success(function (data) {
                        if (data != "") {
                            $scope.pedido.escola = data;
                        }
                        else {
                            $scope.pedido.escola = {id: "", enderecos: [], telefones: [], emails: []};
                            $scope.escola = cnpj;
                            $scope.pedido.escola.cnpj = cnpj;
                            $scope.loadTodosPaises();
                            $scope.loadTodosTipo();
                        }
                    });
        };


        $scope.pedidoDadoId = function (id) {
            pedidoService
                    .getPedidoId(id)
                    .success(function (data) {
                        $scope.pedido = data;
                        $scope.calcularQtdMaterial();
                    });

        };
        $scope.addArquivo = function () {
            $scope.pedido.material.arquivos.push({origem: $scope.arq.origem, tipomaterial: $scope.arq.tipomaterial, observacao: $scope.arq.observacao});
            console.log($scope.arq);
            console.log($scope.pedido.material);
        };

        $scope.trocarCampos = function (tipo) {
            if (tipo == 'livro') {
                $scope.materialAtual = {autor: "", edicao: "", type: "livro", serie: "", ano: "", pnld: "", codigoPrateleira: "", disciplina: [], arquivos: [], nome: "", observacao: "", materiaprima: []};
            } else {
                $scope.materialAtual = {arquivos: [], nome: "", observacao: "", materiaprima: [], type: "outros"};
            }
            $scope.tipoMaterial = tipo;
        };
        $scope.calcularQtdMaterial = function () {

            $scope.qtdMaterial = $scope.pedido.pedidoMaterial.length;
            //console.log($scope.pedido.pedidoMaterial.length);
            //console.log($scope.pedido.pedidoMaterial);

        };
        $scope.selecionarMaterial = function (index) {
            $scope.pedido.pedidoMaterial.push({material: $scope.listaMaterial[index], volumes: [], paginaInicial: "", paginaFinal: "", tipoMaterial: [], idPedido: $scope.pedido.id});
            $scope.calcularQtdMaterial();
            $scope.material = false;
        };
        $scope.editarMaterial = function (index) {
            $scope.novoMaterial();
            $scope.sitMaterial = 'edit';
            $scope.tipoMaterial = $scope.pedido.pedidoMaterial[index].material.type;
            $scope.materialAtual = $scope.pedido.pedidoMaterial[index].material;
            $scope.material = true;
        };
        $scope.delMaterial = function (index) {
            $scope.pedido.pedidoMaterial.splice(index, 1);
            $scope.calcularQtdMaterial();
        };

        $scope.salvarMaterialLivro = function () {
            pedidoService
                    .saveMaterialLivro($scope.materialAtual)
                    .success(function (data) {
                        $scope.materialAtual = data;
                        $timeout(function () {
                            console.log("material:" + $scope.materialAtual);
                            console.log("data:" + data);
                            $scope.pedido.pedidoMaterial.push({material: data, volumes: [], paginaInicial: "", paginaFinal: "", tipoMaterial: [], idPedido: $scope.pedido.id});
                        }, 500);
                    });

        };
        $scope.salvarMaterialOutros = function () {
            pedidoService
                    .saveMaterialOutros($scope.materialAtual)
                    .success(function (data) {
                        $scope.materialAtual = data;
                        $timeout(function () {
                            console.log("material:" + $scope.materialAtual);
                            console.log("data:" + data);
                            console.log("id:" + data.id);
                            $scope.pedido.pedidoMaterial.push({material: data, volumes: [], paginaInicial: "", paginaFinal: "", tipoMaterial: [], idPedido: $scope.pedido.id});
                        }, 500);
                    });

        };
        $scope.addMaterial = function (index) {
            if ($scope.sitMaterial == 'novo') {
                if ($scope.materialAtual.type == 'outros') {
                    $scope.salvarMaterialOutros($scope.materialAtual);
                } else {
                    $scope.salvarMaterialLivro($scope.materialAtual);
                }
            }


            if ($scope.sitMaterial == 'edit') {
                if ($scope.materialAtual.type == 'outros') {
                    pedidoService
                            .saveMaterialOutros($scope.materialAtual)
                            .success(function (data) {
                                $scope.materialAtual = data;

                            });
                } else {
                    pedidoService
                            .saveMaterialLivro($scope.materialAtual)
                            .success(function (data) {
                                $scope.materialAtual = data;

                            });
                }

            }



            $scope.novoMaterial();
            $scope.material = false;
            $scope.calcularQtdMaterial();
        };


        $scope.novoMaterial = function () {
            $scope.material = true;
            $scope.sitMaterial = 'novo';
            $scope.loadTodosDisciplina();
            $scope.tipoMaterial = "";
            $scope.materialAtual = [];
            $scope.consultaMaterial = "";
            $scope.listaMaterial = "";
            $scope.consultaMaterial = "";
            //$scope.calcularQtdMaterial();
        };
        $scope.material = '';
        $scope.loadListaMaterial = function (valor) {
            $http({
                method: "GET",
                url: "./material/localizarPorNomePndl/" + valor,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaMaterial = data;


            }).error(function (data, status) {
                console.log(JSON.stringify(data));
                $scope.listaMaterial = "";

            });
            //$scope.calcularQtdMaterial();
        };

        $scope.loadListaEscola = function (valor) {
            $http({
                method: "GET",
                url: "./escola/procurarPorNome/" + valor,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaEscolas = data;
            }).error(function (data, status) {
                console.log(JSON.stringify(data));
                $scope.listaEscolas = "";
            });
        };
        //-------
        $scope.novoEndereco = function () {
            $scope.enderecos.logradouro = [];
            $scope.enderecos.bairro = [];
            $scope.enderecos.numero = "";
            $scope.enderecos.complemento = "";
            $scope.enderecos.cep = "";
            $scope.enderecos.cidade = [];
            $scope.enderecos.uf = [];
            $scope.enderecos.pais = [];
            $scope.loadTodosPaises();
        };

        $scope.validaEndereco = function () {
            return  ($scope.enderecos.logradouro !== undefined ? $scope.enderecos.logradouro.id : "") &&
                    $scope.enderecos.bairro.id &&
                    $scope.enderecos.numero &&
                    $scope.enderecos.cep &&
                    $scope.enderecos.cidade.id &&
                    $scope.enderecos.uf.id &&
                    $scope.enderecos.pais.id;
        };

        $scope.removerEnderecoSolicitante = function (index) {
            $scope.pedido.solicitante.enderecos.splice(index, 1);
        };

        $scope.editarEnderecoSolicitante = function (index) {
            $scope.enderecos = $scope.pedido.solicitante.enderecos[index];
            $scope.procurarUfPorIdPais($scope.enderecos.pais.id);
            $scope.procurarCidadePorIdUf($scope.enderecos.uf.id);
            $scope.procurarBairroPorIdCidade($scope.enderecos.cidade.id);
            $scope.procurarLogradouroPorIdBairro($scope.enderecos.bairro.id);
            $('#modalEndereco').modal('show');
            $scope.pedido.solicitante.enderecos.splice(index, 1);
        };

        $scope.adicionarEnderecoSolicitante = function () {
            $scope.pedido.solicitante.enderecos.push(
                    {
                        logradouro: $scope.enderecos.logradouro,
                        bairro: $scope.enderecos.bairro,
                        numero: $scope.enderecos.numero,
                        complemento: $scope.enderecos.complemento,
                        cep: $scope.enderecos.cep,
                        cidade: $scope.enderecos.cidade,
                        uf: $scope.enderecos.uf,
                        pais: $scope.enderecos.pais
                    }
            );
            $scope.novoEndereco();
            $scope.loadTodosPaises();
        };

        $scope.procurarBairroPorIdCidade = function (id) {
            $http({
                method: "GET",
                url: "./bairro/procurarBairroPorIdCidade/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaBairro = data;
            }).error(function (data, status) {
                alert("Erro ao buscar Bairros!");
            });
        };

        $scope.procurarCidadePorIdUf = function (id) {
            $http({
                method: "GET",
                url: "./cidade/procurarCidadePorIdUf/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaCidade = data;
            }).error(function (data, status) {
                console.log(JSON.stringify(status));
                alert("Erro ao buscar Cidades!");
            });
        };

        $scope.procurarLogradouroPorIdBairro = function (id) {
            $http({
                method: "GET",
                url: "./logradouro/procurarLogradouroPorIdBairro/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaLogradouro = data;
            }).error(function (data, status) {
                alert("Erro ao buscar Logradouros!");
            });
        };

        $scope.procurarUfPorIdPais = function (id) {
            $http({
                method: "GET",
                url: "./unidadefederativa/findbyCodigoPais/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                console.log(JSON.stringify(status));
                $scope.listaEstados = data;
            }).error(function (data, status) {
                console.log(JSON.stringify(status));
                alert("Erro ao buscar Estados");
            });
        };

        $scope.loadTodosPaises = function () {
            $http({
                method: "GET",
                url: "./pais/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaPaises = data;
            }).error(function (data, status) {
                alert("Erro ao buscar Paises!");
            });

        };

        $scope.novoTelefone = function () {

            $scope.telefones.numero = "";
            $scope.telefones.tipotelefone = [];
        };

        $scope.validaTelefone = function () {
            return $scope.telefones.numero && $scope.telefones.tipotelefone.id;
        };
        $scope.editarTelefoneSolicitante = function (index) {
            $scope.telefones = $scope.pedido.solicitante.telefones[index];
            $('#modalTelefone').modal('show');
            $scope.pedido.solicitante.telefones.splice(index, 1);
        };
        $scope.delTelefoneSolicitante = function (index) {
            $scope.pedido.solicitante.telefones.splice(index, 1);
        };
        $scope.addTelefoneSolicitante = function () {
            if (!$scope.verificarSeExisteTelefoneRepetido($scope.pedido.solicitante.telefones)) {
                $scope.pedido.solicitante.telefones.push({numero: $scope.telefones.numero, tipotelefone: $scope.telefones.tipotelefone});
            }
            $scope.novoTelefone();
        };
        $scope.loadTodosTipo = function () {
            $http({
                method: "GET",
                url: "./tipotelefone/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listatipos = data;
            }).error(function (data, status) {
                alert("Erro ao buscar os tipos");
            });
        };
        $scope.loadTodosDisciplina = function () {

            $http({
                method: "GET",
                url: "./disciplina/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaDisciplina = data;
            }).error(function (data, status) {
                alert("Erro ao buscar as disciplinas");
            });
        };
        $scope.editarEmailSolicitante = function (index) {
            $scope.emails = $scope.pedido.solicitante.emails[index];
            $('#modalEmail').modal('show');
            $scope.pedido.solicitante.emails.splice(index, 1);
        };
        $scope.delEmailSolicitante = function (index) {
            $scope.pedido.solicitante.emails.splice(index, 1);
        };

        $scope.verificarSeExisteEmailRepetido = function (emails) {
            this.emailRepetido = false;
            this.i = 0;
            for (this.i = 0; this.i < emails.length; this.i++) {
                if (emails[this.i].email === $scope.emails.email) {
                    growl.info("Email ja existente !");
                    this.emailRepetido = true;
                }
            }
            return this.emailRepetido;
        };

        $scope.verificarSeExisteTelefoneRepetido = function (telefones) {
            this.telefoneRepetido = false;
            this.i = 0;
            for (this.i = 0; this.i < telefones.length; this.i++) {
                if (telefones[this.i].numero === $scope.telefones.numero) {
                    growl.info("Telefone ja existente !");
                    this.telefoneRepetido = true;
                }
            }
            return this.telefoneRepetido;
        };

        $scope.addEmailSolicitante = function () {
            if (!$scope.verificarSeExisteEmailRepetido($scope.pedido.solicitante.emails)) {
                $scope.pedido.solicitante.emails.push({email: $scope.emails.email});
            }
            $scope.novoEmail();
        };
        $scope.novoEmail = function () {
            $scope.emails.email = "";
        };

        //Escola
        $scope.removerEnderecoEscola = function (index) {
            $scope.pedido.escola.enderecos.splice(index, 1);
        };

        $scope.editarEnderecoEscola = function (index) {
            $scope.enderecos = $scope.pedido.escola.enderecos[index];
            $scope.procurarUfPorIdPais($scope.enderecos.pais.id);
            $scope.procurarCidadePorIdUf($scope.enderecos.uf.id);
            $scope.procurarBairroPorIdCidade($scope.enderecos.cidade.id);
            $scope.procurarLogradouroPorIdBairro($scope.enderecos.bairro.id);
            $('#modalEndereco1').modal('show');
            $scope.pedido.escola.enderecos.splice(index, 1);
        };

        $scope.adicionarEnderecoEscola = function () {
            $scope.pedido.escola.enderecos.push(
                    {
                        logradouro: $scope.enderecos.logradouro,
                        bairro: $scope.enderecos.bairro,
                        numero: $scope.enderecos.numero,
                        complemento: $scope.enderecos.complemento,
                        cep: $scope.enderecos.cep,
                        cidade: $scope.enderecos.cidade,
                        uf: $scope.enderecos.uf,
                        pais: $scope.enderecos.pais
                    }
            );
            $scope.novoEndereco();
            $scope.loadTodosPaises();
        };

        $scope.editarEmailEscola = function (index) {
            $scope.emails = $scope.pedido.escola.emails[index];
            $('#modalEmail1').modal('show');
            $scope.pedido.escola.emails.splice(index, 1);
        };
        $scope.delEmailEscola = function (index) {
            $scope.pedido.escola.emails.splice(index, 1);
        };
        $scope.addEmailEscola = function () {
            if (!$scope.verificarSeExisteEmailRepetido($scope.pedido.escola.emails)) {
                $scope.pedido.escola.emails.push({email: $scope.emails.email});
            }
            $scope.novoEmail();
        };

        $scope.editarTelefoneEscola = function (index) {
            $scope.telefones = $scope.pedido.escola.telefones[index];
            $('#modalTelefone1').modal('show');
            $scope.pedido.escola.telefones.splice(index, 1);
        };
        $scope.delTelefoneEscola = function (index) {
            $scope.pedido.escola.telefones.splice(index, 1);
        };
        $scope.addTelefoneEscola = function () {
            if (!$scope.verificarSeExisteTelefoneRepetido($scope.pedido.escola.telefones)) {
                $scope.pedido.solicitante.escola.push({numero: $scope.telefones.numero, tipotelefone: $scope.telefones.tipotelefone});
            }
            $scope.novoTelefone();
        };


        //Professor
        $scope.removerEnderecoProfessor = function (index) {
            $scope.pedido.professor.enderecos.splice(index, 1);
        };

        $scope.editarEnderecoProfessor = function (index) {
            $scope.enderecos = $scope.pedido.professor.enderecos[index];
            $scope.procurarUfPorIdPais($scope.enderecos.pais.id);
            $scope.procurarCidadePorIdUf($scope.enderecos.uf.id);
            $scope.procurarBairroPorIdCidade($scope.enderecos.cidade.id);
            $scope.procurarLogradouroPorIdBairro($scope.enderecos.bairro.id);
            $('#modalEndereco3').modal('show');
            $scope.pedido.professor.enderecos.splice(index, 1);
        };

        $scope.adicionarEnderecoProfessor = function () {
            $scope.pedido.professor.enderecos.push(
                    {
                        logradouro: $scope.enderecos.logradouro,
                        bairro: $scope.enderecos.bairro,
                        numero: $scope.enderecos.numero,
                        complemento: $scope.enderecos.complemento,
                        cep: $scope.enderecos.cep,
                        cidade: $scope.enderecos.cidade,
                        uf: $scope.enderecos.uf,
                        pais: $scope.enderecos.pais
                    }
            );
            $scope.novoEndereco();
            $scope.loadTodosPaises();
        };

        $scope.editarEmailProfessor = function (index) {
            $scope.emails = $scope.pedido.professor.emails[index];
            $('#modalEmail3').modal('show');
            $scope.pedido.professor.emails.splice(index, 1);
        };
        $scope.delEmailProfessor = function (index) {
            $scope.pedido.professor.emails.splice(index, 1);
        };
        $scope.addEmailProfessor = function () {
            if (!$scope.verificarSeExisteEmailRepetido($scope.pedido.profesor.emails)) {
                $scope.pedido.professor.emails.push({email: $scope.emails.email});
            }
            $scope.novoEmail();
        };

        $scope.editarTelefoneProfessor = function (index) {
            $scope.telefones = $scope.pedido.professor.telefones[index];
            $('#modalTelefone3').modal('show');
            $scope.pedido.professor.telefones.splice(index, 1);
        };
        $scope.delTelefoneProfessor = function (index) {
            $scope.pedido.professor.telefones.splice(index, 1);
        };
        $scope.addTelefoneProfessor = function () {
            if (!$scope.verificarSeExisteTelefoneRepetido($scope.pedido.professor.telefones)) {
                $scope.pedido.professor.telefones.push({numero: $scope.telefones.numero, tipotelefone: $scope.telefones.tipotelefone});
            }
            $scope.novoTelefone();
        };

        //Aluno
        $scope.removerEnderecoAluno = function (index) {
            $scope.pedido.aluno.enderecos.splice(index, 1);
        };

        $scope.editarEnderecoAluno = function (index) {
            $scope.enderecos = $scope.pedido.aluno.enderecos[index];
            $scope.procurarUfPorIdPais($scope.enderecos.pais.id);
            $scope.procurarCidadePorIdUf($scope.enderecos.uf.id);
            $scope.procurarBairroPorIdCidade($scope.enderecos.cidade.id);
            $scope.procurarLogradouroPorIdBairro($scope.enderecos.bairro.id);
            $('#modalEndereco2').modal('show');
            $scope.pedido.aluno.enderecos.splice(index, 1);
        };

        $scope.adicionarEnderecoAluno = function () {
            $scope.pedido.aluno.enderecos.push(
                    {
                        logradouro: $scope.enderecos.logradouro,
                        bairro: $scope.enderecos.bairro,
                        numero: $scope.enderecos.numero,
                        complemento: $scope.enderecos.complemento,
                        cep: $scope.enderecos.cep,
                        cidade: $scope.enderecos.cidade,
                        uf: $scope.enderecos.uf,
                        pais: $scope.enderecos.pais
                    }
            );
            $scope.novoEndereco();
            $scope.loadTodosPaises();
        };

        $scope.editarEmailAluno = function (index) {
            $scope.emails = $scope.pedido.aluno.emails[index];
            $('#modalEmail2').modal('show');
            $scope.pedido.aluno.emails.splice(index, 1);
        };
        $scope.delEmailAluno = function (index) {
            $scope.pedido.aluno.emails.splice(index, 1);
        };
        $scope.addEmailAluno = function () {
            if (!$scope.verificarSeExisteEmailRepetido($scope.pedido.aluno.emails)) {
                $scope.pedido.aluno.emails.push({email: $scope.emails.email});
            }
            $scope.novoEmail();
        };

        $scope.editarTelefoneAluno = function (index) {
            $scope.telefones = $scope.pedido.aluno.telefones[index];
            $('#modalTelefone2').modal('show');
            $scope.pedido.aluno.telefones.splice(index, 1);
        };
        $scope.delTelefoneAluno = function (index) {
            $scope.pedido.aluno.telefones.splice(index, 1);
        };
        $scope.addTelefoneAluno = function () {
            if (!$scope.verificarSeExisteTelefoneRepetido($scope.pedido.aluno.telefones)) {
                $scope.pedido.aluno.telefones.push({numero: $scope.telefones.numero, tipotelefone: $scope.telefones.tipotelefone});
            }
            $scope.novoTelefone();
        };

        $scope.setFocusTelefone = function (modal, campo) {
            $scope.loadTodosTipo();
            pedidoService.setFocus(modal, campo);
        };

        $scope.setFocusPais = function (modal, campo) {
            $scope.loadTodosPaises();
            pedidoService.setFocus(modal, campo);
        };

        $scope.setFocusEmail = function (modal, campo) {
            pedidoService.setFocus(modal, campo);
        };


        $scope.loadTodosTipoDeMaterial = function () {
            $http({
                method: "GET",
                url: "./tipomaterial/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaTipoMaterial = data;
            }).error(function (data, status) {
                growl.error("Não foi possível listar os Tipos de Material!");
            });
        };
        $scope.consultaProfessor = function (value) {
            pedidoService
                    .findProfessorByNomeCpf(value)
                    .success(function (data) {
                        if (data != "") {

                            $scope.listaProfessor = data;
                        }
                        else {
                            $scope.listaProfessor = [];
                        }
                    });
        };
        $scope.consultaAluno = function (value) {
            pedidoService
                    .findAlunoByNomeCpf(value)
                    .success(function (data) {
                        if (data != "") {

                            $scope.listaAlunos = data;
                        }
                        else {
                            $scope.listaAlunos = [];
                        }
                    });
        };

        $scope.consultaSolicitante = function (value) {
            pedidoService
                    .findSolicitanteByNomeCpf(value)
                    .success(function (data) {
                        if (data != "") {
                            $scope.listaSolicitante = '';
                            $scope.listaSolicitante = data;
                            $scope.solicitante = false;


                        }
                        else {
                            $scope.listaSolicitante = '';

                        }
                    });
        };
        $scope.loadListaPessoa = function (value) {
            $http({
                method: "GET",
                url: "./pessoafisica/consulta/nomecpf/" + value,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaSolicitante = data;
                $scope.solicitante = false;
                console.log(data);
            }).error(function (data, status) {
                $scope.listaSolicitante = '';
                $scope.solicitante = false;
            });
        };
        $scope.selecionarProfessor = function (index) {
            console.log($scope.listaProfessor[index]);
            $scope.pedido.professor = $scope.listaProfessor[index];
            $scope.listaProfessor = [];
            $scope.professor = false;
            $scope.consultaProfessorNome = "";
        };
        $scope.selecionarAluno = function (index) {
            $scope.pedido.aluno = $scope.listaAlunos[index];
            $scope.listaAlunos = [];
            $scope.professor = false;
            $scope.consultaAlunoNome = "";
        };
        $scope.selecionarEscola = function (index) {
            $scope.pedido.escola = $scope.listaEscolas[index];
            $scope.listaEscolas = [];
            $scope.consultaEscolaNome = "";
        };
        $scope.selecionarSolicitante = function (index) {
            $scope.consultaSolicitanteNome = "";
            $scope.pedido.solicitante = $scope.listaSolicitante[index];
            $scope.listaSolicitante = [];
        };
        $scope.adicionarSolicitante = function () {
            $scope.solicitante = true;
            $scope.pedido.solicitante = {id: "", enderecos: [], telefones: [], emails: []};

        };
        $scope.adicionarProfessor = function () {
            $scope.professor = true;

        };
        $scope.adicionarAluno = function () {
            $scope.aluno = true;

        };
        $scope.encontraEnderecoPorCEP = function (value) {
            $scope.enderecos;
            pedidoService.getLogradouroCep(value)
                    .success(function (data) {
                        $scope.loadTodosPaises();
                        $scope.enderecos.pais = data.cidade.unidadefederativa.pais;
                        $scope.procurarUfPorIdPais(data.cidade.unidadefederativa.pais.id);
                        $scope.enderecos.uf = data.cidade.unidadefederativa;
                        $scope.procurarCidadePorIdUf(data.cidade.unidadefederativa.id);
                        $scope.enderecos.cidade = data.cidade;
                        $scope.procurarBairroPorIdCidade(data.cidade.id);
                        $scope.enderecos.bairro = data.bairro;
                        $scope.procurarLogradouroPorIdBairro(data.bairro.id);
                        $scope.enderecos.tipologradouro = data.tipologradouro;
                        $scope.enderecos.logradouro = data;

                        console.log($scope.enderecos);
                    });
        };
        $scope.adicionarEscola = function () {
            $scope.escola = true;
            //$scope.pedido.escola = [];
            $scope.pedido.escola.telefones = [];
            $scope.pedido.escola.enderecos = [];
            $scope.pedido.escola.emails = [];
        };

    }
]);
