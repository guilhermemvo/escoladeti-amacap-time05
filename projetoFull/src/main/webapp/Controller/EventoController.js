var moduleEvento = angular.module('escoladeti.evento', ['ngRoute', 'escoladeti.pessoaService']);

moduleEvento.controller('EventoController', ['$scope', '$http', '$rootScope', '$routeParams', 'growl', 'pessoaService', function ($scope, $http, $rootScope, $routeParams, growl, pessoaService) {

        $scope.message = 'Controller dos Eventos!';
        $scope.page = {pageNumber: 0};
        $scope.eventoAtual = undefined;
        $scope.pessoa = undefined;
        $scope.search = "";
        $scope.listainstrutores = [];
        $scope.nome = "Evento";
        $scope.form = "evento";
        $scope.caminholistar = "./View/Operacao/Evento/listarEvento.html";
        $scope.caminhoeditar = "./View/Operacao/Evento/editarEvento.html";
        $scope.caminhoinicial = "#/Operacao/Evento/";
        $scope.caminhorelatorio = "evento/relatorio/listaEvento";
        $scope.displayBtnRelatorio = "inline";
        $scope.evento = [];
        $scope.presencaDosParticipantes = [];
        $scope.emails = [];
        $scope.telefones = [];
        $scope.listatipos = [];
        $scope.labelDataPresenca = "";
        $scope.presencaVO = [];
        $scope.bloqueiaPresencas = false;
        $scope.presencaManha = false;
        $scope.presencaTarde = false;
        $scope.presencaNoite = false;

        /*Site*/
        $scope.listaeventos = [];

        /**/

        $scope.listaBairro = [];
        $scope.listaLogradouro = [];
        $scope.enderecos = [];
        $scope.listaCidade = [];
        $scope.listaEstados = [];
        $scope.listaPaises = [];
        $scope.flagEndereco = false;

        $scope.incluir = function () {
            window.location.href = "#Operacao/Evento/Novo/Editar";
        };
        $scope.init = function () {
            $scope.load(0);
            $scope.loadTodosInstrutores();
            $scope.novoEmail();
            $scope.novoEndereco();
            $scope.novoTelefone();
        };
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
            $scope.loadTodosInstrutores();
            $scope.loadTodosPaises();
        };
   
        $scope.remover = function (index) {
            if (index === '') {
                var excluir = $scope.eventoAtual;
            } else {
                var excluir = $scope.page.list[index];
                $scope.evento = $scope.page.list[index];
            }
            $scope.verificaParticipantesCadastrados($scope.evento.id);
            if (!$scope.bloqueiaPresencas) {
                growl.error("Existe participantes cadastrados no evento. Por favor verifique!");
                return;
            }
            $rootScope.confirmaExclusao(excluir, 'nome');
            $scope.flagEndereco = false;
        };
        $scope.loadTodosInstrutores = function () {
            $http({
                method: "GET",
                url: "./instrutor/todas",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listainstrutores = data;
            }).error(function (data, status) {
                growl.error("Não foi possível listar os Instrutores!");
            });
        };
        $scope.removerServidor = function (evento) {
            $http({
                method: "DELETE",
                url: "./evento/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(evento))
            }).success(function (data, status) {
                growl.success("Cadastro excluído com sucesso!");
                $scope.load(0);
            }).error(function (data, status) {
                if (status == 500) {
                    growl.error("Não foi possível excluir o Evento. O cadastro possui outras referências no sistema!");
                } else {
                    growl.error("Não foi possível excluir o Evento!");

                }
            });
        };

        $scope.load = function (numeroPagina) {
            $http({
                method: "GET",
                url: "./evento/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.page = formatPageList(data);
            }).error(function (data, status) {
                growl.error("Não foi possível listar os Eventos!");
            });
        };

        $scope.encontraEnderecoPorCEP = function (value) {
            $scope.enderecos;
            pessoaService.getLogradouroCep(value)
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
                    });
        };



        /*instrutor*/

        $scope.pessoa = {
            id: '',
            nome: '',
            cpf: '',
            rg: '',
            sexo: '',
            dataNascimento: '',
            telefones: [],
            emails: [],
            enderecos: []
        };

        $scope.abrirInstrutor = function () {
            $scope.instrutor = true;
        };
        $scope.fecharInstrutor = function () {
            $scope.instrutor = '';
        };

        $scope.salvarInstrutor = function () {
            $http({
                method: "POST",
                url: "./instrutor/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.pessoa)
            }).success(function (data, status) {
                console.log(JSON.stringify(status));
                $scope.loadTodosInstrutores();
                $scope.fecharInstrutor();
                growl.success("Cadastro efetuado com sucesso!");
            }).error(function (data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
                $scope.fecharInstrutor();
            });
            console.log($scope.pessoa);

        };



        $scope.salvar = function () {
            $scope.validaPeriodoDeDatas();
            $http({
                method: "POST",
                url: "./evento/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson($scope.eventoAtual)
            }).success(function (data, status) {
                window.location.href = "#Operacao/Evento/";
                growl.success("Cadastro efetuado com sucesso!");
            }).error(function (data, status) {
                //console.log($scope.eventoAtual);
                //growl.error("Não foi possível efetuar o cadastro!");
                window.location.href = "#Operacao/Evento/";
            });
        };

        $scope.eventoDadoId = function (id) {
            $http({
                method: "GET",
                url: "./evento/carregar/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.eventoAtual = data;
                $scope.validaBotaoPresencas();
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

        $scope.validaDataInicial = function (dataInicio, dataFinal) {
            if (dataFinal !== "" && dataInicio > dataFinal) {
                dataInicio = dataInicio.split("-");
                dataFinal = dataFinal.split("-");

                dataInicio = dataInicio[2] + "/" + dataInicio[1] + "/" + dataInicio[0];
                dataFinal = dataFinal[2] + "/" + dataFinal[1] + "/" + dataFinal[0];

                $scope.msg = "A data inicial " + dataInicio + " é maior que data final " + dataFinal;

                $('#ModalMensagem').modal('show');
                $("#dataInicial").val("");
            }
        };

        $scope.validaDataFinal = function (dataInicio, dataFinal) {
            if (dataInicio !== "" && dataFinal < dataInicio) {
                dataInicio = dataInicio.split("-");
                dataFinal = dataFinal.split("-");

                dataInicio = dataInicio[2] + "/" + dataInicio[1] + "/" + dataInicio[0];
                dataFinal = dataFinal[2] + "/" + dataFinal[1] + "/" + dataFinal[0];

                $scope.msg = $scope.msg = "A data final " + dataFinal + " é menor que data inicial " + dataInicio;
                $('#ModalMensagem').modal('show');
                dataFinal = "";
                $("#dataFinal").val("");
            }
        };

        $scope.validaVagas = function (v1) {
            if (v1 < 1 || v1 > 100) {
                $scope.msg = "Quantidade de Vagas entre 1 a 100 !";
                $('#ModalMensagem').modal('show');
                $("#Vagasfinal").val("");
            }
        };

        $scope.validaCargaH = function (v1) {
            if (v1 < 1 || v1 > 100) {
                $scope.msg = "Carga Horária entre 1 a 100 !";
                $('#ModalMensagem').modal('show');
                $("#Cargafinal").val("");
            }
        };
        $scope.validaValorEvento = function (v1) {
            if (v1 < 1 || v1 > 1000) {
                $scope.msg = "Valor do Evento entre R$ 1,00 a R$ 1000,00 !";
                $('#ModalMensagem').modal('show');
                $("#Valorfinal").val("");
            }
        };

        $scope.procurarPorNome = function (nome) {
            if (nome !== '') {
                $http({
                    method: "GET",
                    url: "./evento/procurarPorNome/" + nome,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).success(function (data, status) {
                    $scope.page = data;
                }).error(function (data, status) {
                    growl.info("Não foi possível localizar os Eventos!");
                });
            } else {
                $scope.load(0);
            }
        };

        $scope.addAgenda = function () {
            if ($scope.validaPeriodoDeDatas()) {

                $scope.eventoAtual.agendaEvento.push({
                    id:"",  
                    data: "",
                    manha: false,
                    tarde: false,
                    noite: false});

                $scope.validaBotaoPresencas();
            }
        };

        $scope.validaPeriodoDeDatas = function () {
            for (var i = 0; i < $scope.eventoAtual.agendaEvento.length; i++) {
                if (!$scope.eventoAtual.agendaEvento[i].manha && !$scope.eventoAtual.agendaEvento[i].tarde && !$scope.eventoAtual.agendaEvento[i].noite) {
                    $scope.msg = $scope.msg = "Existe data do evento sem período preenchido. Por favor verifique!";
                    $('#ModalMensagem').modal('show');
                    return false;
                }
            }

            return true;
        };

        $scope.removerDeAgenda = function (index) {
            $scope.eventoAtual.agendaEvento.splice(index, 1);
        };

        $scope.validaDataPeriodoEvento = function (dataEdicao, dataInicio, dataFim, index) {
            var dataInvalida = false;
            if (dataEdicao !== "" && dataEdicao < dataInicio) {
                dataInvalida = true;
            } else if (dataEdicao !== "" && dataEdicao > dataFim) {
                dataInvalida = true;
            }
            if (dataInvalida) {
                $scope.msg = $scope.msg = "Data informada está fora do período do evento!";
                $('#ModalMensagem').modal('show');
                $scope.eventoAtual.agendaEvento[index].data = "";
                $("#dataEdicao_" + index).val("");
            }
            $scope.validaDatasIguais(dataEdicao, index);
        };

        $scope.validaDatasIguais = function (dataBusca, index) {
            if (dataBusca === "") {
                return;
            }

            for (var i = 0; i < $scope.eventoAtual.agendaEvento.length; i++) {
                if (i !== index) {
                    if ($scope.eventoAtual.agendaEvento[i].data === dataBusca) {
                        $scope.msg = $scope.msg = "Data já informada.Por favor verifique!";
                        $('#ModalMensagem').modal('show');
                        $scope.eventoAtual.agendaEvento[index].data = "";
                        $("#dataEdicao_" + index).val("");
                    }
                }
            }
        };
        
        $scope.carregaAgendas = function (index) {
            $http({
                method: "GET",
                url: "./evento/carregaAgenda/" + $scope.page.list[index].id,
                headers: {'Content-Type': 'application/json; charset=UTF-8'}
            }).success(function (data, status) {

                if (data.length === 0){
                    growl.error("Não existe agenda nesse evento. Por favor verifique!");
                    return;
                }
                
                $scope.agendaEvento = data;
                $scope.agendaAtualizar = data[0];
                $scope.carregaParticipantes(data[0]);
                                
                $http({
                    method: "POST",
                    url: "./inscricao/participantes",
                    headers: {'Content-Type': 'application/json; charset=UTF-8'},
                    data: angular.toJson($scope.page.list[index].id)
                }).success(function (data, status) {
                    $scope.participantes = data;
                    $scope.bloqueiaPresencas = !(data.length > 0);

                    if ($scope.participantes.length === 0){
                        growl.error("Não existe participantes cadastrados a esse evento!");
                        return; 
                    };
                    $('#modalPresencas').modal('show');
                }).error(function (data, status) {
                });
            }).error(function (data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
            });  
        };
        
        $scope.pegaData = function(id){
            $scope.carregaParticipantes($scope.agendaEvento.filter(function (lista) { return lista.id === id;})[0]);
        };

        $scope.carregaParticipantes = function (agenda) {
            $http({
                method: "GET",
                url: "./evento/carregaPresencas/" + agenda.id,
                headers: {'Content-Type': 'application/json; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.presencaDosParticipantes = data;

                $scope.presencaManha = agenda.manha;
                $scope.presencaTarde = agenda.tarde;
                $scope.presencaNoite = agenda.noite;
                
            }).error(function (data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
            });
        };

        $scope.salvarPresencas = function () {
            var presencaVO = [];

            for (var i = 0; i < $scope.presencaDosParticipantes.length; i++) {
                presencaVO.push({id: $scope.presencaDosParticipantes[i].id,
                    manha: $scope.presencaDosParticipantes[i].manha,
                    tarde: $scope.presencaDosParticipantes[i].tarde,
                    noite: $scope.presencaDosParticipantes[i].noite
                });
            }

            $http({
                method: "POST",
                url: "./evento/atualizarPresenca",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson(presencaVO)
            }).success(function (data, status) {
                window.location.href = "#Operacao/Evento/";
                growl.success("Presença efetuado com sucesso!");
            }).error(function (data, status) {
                console.log(JSON.stringify($scope.eventoAtual));
                growl.error("Não foi possível atualizar a presença do participante!");
            });
        };

        $scope.verificaParticipantesCadastrados = function (id) {
            $http({
                method: "POST",
                url: "./inscricao/participantes",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson(id)
            }).success(function (data, status) {
                $scope.participantes = data;
                $scope.bloqueiaPresencas = !(data.length > 0);
            }).error(function (data, status) {
                $scope.bloqueiaPresencas = false;
            });
        };

        $scope.validaBotaoPresencas = function () {
            if ($scope.eventoAtual.id === "") {
                $scope.bloqueiaPresencas = true;
            } else {
                $scope.verificaParticipantesCadastrados($scope.eventoAtual.id);
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
                $scope.listaEstados = data;
            }).error(function (data, status) {
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






        /*EMAIL*/

        $scope.novoEmail = function () {
            $scope.emails.email = "";
        };

        $scope.editarEmailInstrutor = function (index) {
            $scope.emails = $scope.pessoa.emails[index];
            $('#modalEmail').modal('show');
            $scope.pessoa.emails.splice(index, 1);
        };

        $scope.delEmailInstrutor = function (index) {
            $scope.pessoa.emails.splice(index, 1);
        };

        $scope.addEmailInstrutor = function () {
            $scope.pessoa.emails.push({email: $scope.emails.email});
            $scope.novoEmail();
        };



        /*TELEFONES*/
        $scope.novoTelefone = function () {
            $scope.telefones.numero = "";
            $scope.telefones.tipotelefone = [];
        };

        $scope.editarTelefoneInstrutor = function (index) {
            $scope.telefones = $scope.pessoa.telefones[index];
            $('#modalTelefone').modal('show');
            $scope.pessoa.telefones.splice(index, 1);
        };

        $scope.addTelefoneInstrutor = function () {
            $scope.pessoa.telefones.push({numero: $scope.telefones.numero, tipotelefone: $scope.telefones.tipotelefone});
            $scope.novoTelefone();
        };

        $scope.delTelefoneInstrutor = function (index) {
            $scope.pessoa.telefones.splice(index, 1);
        };


        $scope.loadTodosTipo = function () {
            $http({
                method: "GET",
                url: "./tipotelefone/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listatipos = data;
                $scope.telefones.tipotelefone = $scope.listatipos[0];
            }).error(function (data, status) {
                growl.info("Não foi possível localizar os Tipos de Telefone!");
            });
        };





        /*ENDEREÇOS*/

        $scope.editarEndereco = function (index) {
            $scope.enderecos = $scope.eventoAtual.enderecos[index];
            $scope.procurarUfPorIdPais($scope.enderecos.pais.id);
            $scope.procurarCidadePorIdUf($scope.enderecos.uf.id);
            $scope.procurarBairroPorIdCidade($scope.enderecos.cidade.id);
            $scope.procurarLogradouroPorIdBairro($scope.enderecos.bairro.id);
            $('#modalEndereco').modal('show');
            $scope.eventoAtual.enderecos.splice(index, 1);
        };

        $scope.editarEnderecoInstrutor = function (index) {
            $scope.enderecos = $scope.Pessoa.enderecos[index];
            $scope.procurarUfPorIdPais($scope.enderecos.pais.id);
            $scope.procurarCidadePorIdUf($scope.enderecos.uf.id);
            $scope.procurarBairroPorIdCidade($scope.enderecos.cidade.id);
            $scope.procurarLogradouroPorIdBairro($scope.enderecos.bairro.id);
            $('#modalEndereco').modal('show');
            $scope.Pessoa.enderecos.splice(index, 1);
        };

        $scope.delEnderecoInstrutor = function (index) {
            $scope.pessoa.enderecos.splice(index, 1);
        };


        $scope.adicionarEndereco = function () {
            $scope.eventoAtual.enderecos.push(
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
            $scope.flagEndereco = true;
        };

        $scope.adicionarEnderecoInstrutor = function () {
            $scope.pessoa.enderecos.push(
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
            $scope.flagEndereco = true;

        };

        $scope.removerEndereco = function (index) {
            $scope.eventoAtual.enderecos.splice(index, 1);
        };


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

    }
]);
