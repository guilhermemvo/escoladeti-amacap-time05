var modulePedido = angular.module('escoladeti.pedidoPendente', ['ngRoute', 'escoladeti.pedidoService', 'escoladeti.validacaoService']);
modulePedido.controller('PedidoPendenteController', ['$scope', '$http', '$rootScope', '$routeParams', 'pedidoService', '$timeout', 'validacaoService', 'growl', function ($scope, $http, $rootScope, $routeParams, pedidoService, $timeout, validacaoService, growl) {

        $scope.message = 'Controller do Pedido Pendente!';
        $scope.page = {pageNumber: 0};
        $scope.pedido = undefined;
        $scope.search = "";
        $scope.nome = "Pedido";
        $scope.form = "PedidoPendente";
        $scope.listapedido = [];
        $scope.consultaMaterial = "";
        $scope.listaprofessor = [];
        $scope.listaaluno = [];
        $scope.caminholistar = "./View/Pedido/Pendente/listarPedido.html";
        $scope.caminhoeditar = "./View/Pedido/Pedido/editarPedido.html";
        $scope.caminhoinicial = "#/Pedido/Pendente/";
        $scope.listaEscola = [];
        $scope.stringCancelamento = undefined;
        $scope.listatipos = [];
        $scope.telefones = [];
        $scope.listaBairro = [];
        $scope.emails = [];
        $scope.listaLogradouro = [];
        $scope.enderecos = [];
        $scope.listaCidade = [];
        $scope.listaUnidadeProducao = [];
        $scope.listaEstados = [];
        $scope.listaPaises = [];
        $scope.listaTipoMaterial = [];
        $scope.pedidosPendentes = "";
        $scope.nota = {data_nota: "", observacao: "", pedido: ""};
        $scope.pedidoAtual = undefined;
        $scope.oficio = {recebido: false, observacao: "", pedido: ""};
        $scope.oficioModal = "";
        $scope.materialModal = "";
        $scope.notasModal = "";
        $scope.incluir = function () {
            window.location.href = "#Pedido/Pedido/Novo/Editar";
        };
        $scope.init = function () {
            $scope.load(0);
            $scope.loadTodosPaises();
            $scope.loadTodosTipoDeMaterial();
            $scope.loadTodasUnidadesProducao();
        };
        $scope.initEditar = function () {
            if ($routeParams.objeto === 'Novo') {
                $scope.pedido = {
                    id: ""
                };
            } else {
                $scope.visualizacaoAtiva = checkvisualizar($routeParams.objeto2);
                $scope.pedidoDadoId($routeParams.objeto);
            }
            $scope.loadTodosPedido();
            $scope.loadTodosTipoDeMaterial();
        };
        $scope.modalOficio = function (index) {
            $scope.oficioModal = true;
            $scope.pedidoAtual = $scope.page.list[index];
            $scope.oficio = {recebido: false, observacao: "", pedido: ""};
            $('#modalOficio').modal('show');
        };
        $scope.modalUnidadeProducao = function (index) {
            $scope.unidadeProducaoModal = true;
            $scope.pedidoAtual = $scope.page.list[index];
            $('#modalUnidadeProducao').modal('show');
        };
        $scope.modalMaterial = function (index) {
            $scope.materialModal = true;
            $scope.pedidoAtual = $scope.page.list[index];
            $('#modalMaterial').modal('show');
        };
        $scope.modalNota = function (index) {
            $scope.notasModal = true;
            $scope.nota = {data_nota: "", observacao: "", pedido: ""};
            $scope.pedidoAtual = $scope.page.list[index];
            $('#modalNotas').modal('show');
        };
        $scope.adicionarMaterial = function () {
            $scope.count = 0;
            if ($scope.pedidoAtual.pedidoMaterial.length === 0) {
                growl.error("Não à materiais no pedido!");
            } else {
                for (i = 0; i < $scope.pedidoAtual.pedidoMaterial.length; i++) {
                    console.log($scope.pedidoAtual.pedidoMaterial[i].recebido);
                    $http({
                        method: "POST",
                        url: './pedidomaterial/salvarid/' + $scope.pedidoAtual.pedidoMaterial[i].id + "/" + $scope.pedidoAtual.pedidoMaterial[i].recebido,
                        headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                    });
                    if ($scope.pedidoAtual.pedidoMaterial[i].recebido === false) {
                        $scope.count = $scope.count + 1;
                    }
                    console.log($scope.pedidoAtual.pedidoMaterial[i].recebido);
                }
                growl.success("Materiais foram alterados com sucesso!");
                if ($scope.count === 0) {
                    $http({
                        method: "POST",
                        url: './pedido/mudarstatusproducao',
                        headers: {'Content-Type': 'application/json; charset=UTF-8'},
                        data: angular.toJson($scope.pedidoAtual.id)
                    });
                    growl.success("Pedido foi alterado para produção!");
                }
            }
            $scope.load(0);
        }
        $scope.adicionarOficio = function () {
            if ($scope.oficio.recebido === "true") {
                $scope.oficio.pedido = $scope.pedidoAtual.id;
                $http({
                    method: "POST",
                    url: './oficio/salvar',
                    headers: {'Content-Type': 'application/json; charset=UTF-8'},
                    data: angular.toJson($scope.oficio)
                }).success(function (status) {
                    growl.success("Oficio Cadastrada com sucesso!");
                }).error(function (data, status) {
                    growl.error("Houve um erro no cadastro do oficio!");
                });
            } else {
                growl.error("Oficio não foi recebido!");
            }
            $scope.load(0);
        }
        $scope.adicionarNota = function () {
            $scope.nota.pedido = $scope.pedidoAtual.id;
            $http({
                method: "POST",
                url: './nota/salvar',
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson($scope.nota)
            }).success(function (status) {
                growl.success("Nota Cadastrada com sucesso!");
            }).error(function (data, status) {
                growl.error("Houve um erro no cadastro!");
            });
            $scope.load(0);
        }
        $scope.adicionarUnidadeProducao = function () {
            for (i = 0; i < $scope.pedidoAtual.pedidoMaterial.length; i++) {
                if ($scope.pedidoAtual.pedidoMaterial[i].unidadeProducao.id !== undefined) {
                    console.log($scope.id);
                    $http({
                        method: "POST",
                        url: './pedidomaterial/salvarunidadeproducao/' + $scope.pedidoAtual.pedidoMaterial[i].id + "/" + $scope.pedidoAtual.pedidoMaterial[i].unidadeProducao,
                        headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                    }).error(function (data, status) {
                        growl.error("Houve um erro no cadastro!");
                    });
                }
            }
            growl.success("Unidade de Produção atualizada com sucesso!");
            $scope.load(0);
        }
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
            $rootScope.stringCancelamento = 'cancelar'; //atribui cancelar aqui
            $rootScope.confirmaExclusao(excluir, 'descricao'); //atribui excluir dentro do método
        };
        $scope.removerServidor = function (pedido) {
            pedidoService
                    .cancelarPedido(pedido)
                    .success(function (status) {
                        $rootScope.getCountPendentes();
                        window.location.href = $scope.caminhoinicial;
                    });
        };
        $scope.load = function (numeroPagina) {
            pedidoService
                    .getPagePedido(numeroPagina)
                    .success(function (data) {
                        console.log(data);
                        $scope.page = data;
                    });
        };
        $scope.salvarAluno = function () {
            //$scope.pedido.aluno.serie = "1";
            //$scope.pedido.aluno.turma = "1";
            pedidoService
                    .saveAluno($scope.pedido.aluno)
                    .success(function (data) {
                        $scope.pedido.aluno = data;
                        $timeout(function () {
                            console.log($scope.pedido.aluno);
                            $scope.consultaAluno($scope.pedido.aluno.cpf);
                        }, 500);
                    });
        };
        $scope.salvarSolicitante = function () {
            console.log($scope.pedido.solicitante);
            pedidoService
                    .saveSolicitante($scope.pedido.solicitante)
                    .success(function (data) {
                        $scope.pedido.solicitante = data;
                        $timeout(function () {
                            console.log($scope.pedido.solicitante);
                            $scope.consultaCPF($scope.pedido.solicitante.cpf);
                        }, 500);
                    });
        };
        $scope.salvarEscola = function () {
            pedidoService
                    .saveEscola($scope.pedido.escola)
                    .success(function (data) {
                        $scope.pedido.escola = data;
                        $timeout(function () {
                            console.log($scope.pedido.escola);
                            $scope.consultaEscola($scope.pedido.escola.cnpj);
                        }, 500);
                    });
        };
        $scope.salvarProfessor = function () {
            console.log($scope.pedido.professor);
            pedidoService
                    .saveProfessor($scope.pedido.professor)
                    .success(function (data) {
                        $scope.pedido.professor = data;
                        $timeout(function () {
                            console.log($scope.pedido.professor);
                            $scope.consultaProfessor($scope.pedido.professor.cpf);
                        }, 500);
                    });
        };
        $scope.salvarPedido = function () {
            console.log($scope.pedido);
            pedidoService.savePedido($scope.pedido);
        };
        $scope.recuperarTodasEscolas = function () {
            pedidoService
                    .getAllEscola()
                    .success(function (data) {
                        $scope.listaEscola = data;
                    });
        };
        $scope.consultaCPF = function (cpf) {

            $http({
                method: "GET",
                url: "./pessoafisica/carregar/cpf/" + cpf,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                console.log(status);
                if (data != "") {
                    $scope.solicitante = undefined;
                    $scope.pedido.solicitante = data;
                } else {
                    $scope.pedido.solicitante = {id: "", enderecos: [], telefones: [], emails: []};
                    $scope.solicitante = cpf;
                    $scope.pedido.solicitante.cpf = cpf;
                    $scope.loadTodosPaises();
                    $scope.loadTodosTipo();
                }
            }).error(function (data, status) {
                alert("Erro ao consultar CPF!");
            });
        };
        $scope.consultaAluno = function (cpf) {
            $http({
                method: "GET",
                url: "./aluno/carregar/cpf/" + cpf,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                if (data != "") {
                    console.log('aqui');
                    $scope.aluno = undefined;
                    $scope.pedido.aluno = data;
                } else {
                    $http({
                        method: "GET",
                        url: "./pessoafisica/carregar/cpf/" + cpf,
                        headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                    }).success(function (data, status) {

                        if (data != "") {
                            $scope.pedido.aluno = data;
                            $scope.aluno = cpf;
                            $scope.pedido.aluno.cpf = cpf;
                        } else {
                            $scope.pedido.aluno = {id: "", enderecos: [], telefones: [], emails: []};
                            $scope.aluno = cpf;
                            $scope.pedido.aluno.cpf = cpf;
                            $scope.loadTodosPaises();
                            $scope.loadTodosTipo();
                        }
                    }).error(function (data, status) {
                        alert("Erro ao consultar CPF!");
                    });
                }
            }).error(function (data, status) {
                alert("Erro ao consultar CPF!");
            });
        };
        $scope.consultaProfessor = function (cpf) {
            pedidoService
                    .findProfessorByCpf(cpf)
                    .success(function (data) {
                        if (data != "") {
                            $scope.pedido.professor = undefined;
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
                            $scope.pedido.escola = undefined;
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
                    });
        };
        $scope.selecionarMaterial = function (index) {
            $scope.pedido.material = $scope.listaMaterial[index];
        };
        $scope.novoMaterial = function () {
            $scope.pedido.material = "";
            $scope.listaMaterial = "";
            $scope.consultaMaterial = "";
        };
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
            if (!validacaoService.verificarSeExisteEnderecosIguais($scope.pedido.solicitante.enderecos, $scope.enderecos)) {
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
            }
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
        $scope.isSelected = function (listOfItems, item) {
            var resArr = listOfItems.split(",");
            if (resArr.indexOf(item.toString()) > -1) {
                return true;
            } else {
                return false;
            }
        };
        $scope.loadTodasUnidadesProducao = function () {

            $http({
                method: "GET",
                url: "./unidadeProducao/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaUnidadeProducao = data;
            }).error(function (data, status) {
                alert("Erro ao buscar as unidades de produção!");
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
            if (!validacaoService.verificarSeExisteTelefoneIguais($scope.pedido.solicitante.telefones, $scope.telefones)) {
                $scope.pedido.solicitante.telefones.push({numero: $scope.telefones.numero, tipotelefone: $scope.telefones.tipotelefone});
                $scope.novoTelefone();
            }
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
        $scope.editarEmailSolicitante = function (index) {
            $scope.emails = $scope.pedido.solicitante.emails[index];
            $('#modalEmail').modal('show');
            $scope.pedido.solicitante.emails.splice(index, 1);
        };
        $scope.delEmailSolicitante = function (index) {
            $scope.pedido.solicitante.emails.splice(index, 1);
        };
        $scope.addEmailSolicitante = function () {
            if (!validacaoService.verificarSeExisteEmailsIguais($scope.pedido.solicitante.emails, $scope.emails)) {
                $scope.pedido.solicitante.emails.push({email: $scope.emails.email});
                $scope.novoEmail();
            }
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
            if (!validacaoService.verificarSeExisteEnderecosIguais($scope.pedido.escola.enderecos, $scope.enderecos)) {
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
            }
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
            if (!validacaoService.verificarSeExisteEmailsIguais($scope.pedido.escola.emails, $scope.emails)) {
                $scope.pedido.escola.emails.push({email: $scope.emails.email});
                $scope.novoEmail();
            }
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
            if (!validacaoService.verificarSeExisteTelefoneIguais($scope.pedido.escola.telefones, $scope.telefones)) {
                $scope.pedido.escola.telefones.push({numero: $scope.telefones.numero, tipotelefone: $scope.telefones.tipotelefone});
                $scope.novoTelefone();
            }
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
            if (!validacaoService.verificarSeExisteEnderecosIguais($scope.pedido.professor.enderecos, $scope.enderecos)) {
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
            }
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
            if (!validacaoService.verificarSeExisteEmailsIguais($scope.pedido.professor.emails, $scope.emails)) {
                $scope.pedido.professor.emails.push({email: $scope.emails.email});
                $scope.novoEmail();
            }
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
            if (!validacaoService.verificarSeExisteTelefoneIguais($scope.pedido.professor.telefones, $scope.telefones)) {
                $scope.pedido.professor.telefones.push({numero: $scope.telefones.numero, tipotelefone: $scope.telefones.tipotelefone});
                $scope.novoTelefone();
            }
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
            if (!validacaoService.verificarSeExisteEnderecosIguais($scope.pedido.aluno.enderecos, $scope.enderecos)) {
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
            }
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
            if (!validacaoService.verificarSeExisteEmailsIguais($scope.pedido.aluno.emails, $scope.emails)) {
                $scope.pedido.aluno.emails.push({email: $scope.emails.email});
                $scope.novoEmail();
            }
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
            if (!validacaoService.verificarSeExisteTelefoneIguais($scope.pedido.aluno.telefones, $scope.telefones)) {
                $scope.pedido.aluno.telefones.push({numero: $scope.telefones.numero, tipotelefone: $scope.telefones.tipotelefone});
                $scope.novoTelefone();
            }
        };
        $scope.loadTodosTipoDeMaterial = function () {
            $http({
                method: "GET",
                url: "./tipomaterial/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaTipoMaterial = data;
            }).error(function (data, status) {
                growl.error("NÃ£o foi possÃ­vel listar os Tipos de Material!");
            });
        };
        $scope.procurarPorData = function (de, ate) {
            if (de == undefined) {
                de = '2000-01-01';
            }
            if (ate == undefined) {
                ate = '2020-01-01';
            }
            if (de !== '') {
                $http({
                    method: "GET",
                    url: "./pedido/procurar/data/" + de + "/" + ate,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function (data, status) {
                    console.log(data);
                    $scope.page.list = data;
                }).error(function (data, status) {
                    console.log("Erro ao procurar Pedido");
                    console.log(de);
                    console.log(ate);
                });
            } else {
                $scope.load(0);
            }
        };
//        $scope.getCountPendentes = function () {
//                pedidoService
//                    .getCountPendentes()
//                    .success(function (data) {
//                        alert(data);
//                        $scope.pedidosPendentes = data;
//                    }).error(function (data, status) {
//                        alert("Erro ao contas os pedidos pendentes");
//                        alert(status);    
//                        alert(data);
//                    });
//        };
    }
]);

