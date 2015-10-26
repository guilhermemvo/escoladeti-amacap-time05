
var siteApp = angular.module('site', ['ngRoute', 'ui.mask', 'angular-loading-bar', 'ngAnimate','angular-growl']);
siteApp.config(['$routeProvider', '$locationProvider','growlProvider',
    function($routeProvider, $locationProvider,growlProvider) {
	 	growlProvider.globalTimeToLive({success: 5000, error: 5000, warning: 5000, info: 5000});

        $locationProvider.html5Mode(false);
        $routeProvider
                .when('/', {
                    templateUrl: '../resources/site/index.html',
                    controller: 'SiteEventoController'
                })
                .when('/contato', {
                    templateUrl: '../resources/site/contato.html',
                    controller: 'EmailController'
                })
                .when('/evento', {
                    templateUrl: '../resources/site/evento.html',
                    controller: 'SiteEventoController'
                })
                .when('/evento/:objeto', {
                    templateUrl: '../resources/site/evento.html',
                    controller: 'SiteEventoController'
                })
                .when('/material', {
                    templateUrl: '../resources/site/material.html',
                    controller: 'SiteMaterialController'
                })
                .when('/sobre', {
                    templateUrl: '../resources/site/sobre.html'
                })
                .when('/associados', {
                    templateUrl: '../resources/site/associados.html'
                })
                .when('/inscricao', {
                    templateUrl: '../resources/site/inscricao.html',
                    controller: 'SiteEventoController'
                })
                .when('/inscricao/:objeto', {
                    templateUrl: '../resources/site/inscricao.html',
                    controller: 'SiteEventoController'
                })
                .when('/pedido', {
                    templateUrl: '../resources/site/pedido.html',
                    controller: 'SitePedidoController'
                })
                .when('/pedido/:objeto', {
                    templateUrl: '../resources/site/pedido.html',
                    controller: 'SitePedidoController'
                })

                .otherwise({redirectTo: '/'});

    }]);



siteApp.controller('SiteEventoController', ['$scope', '$http', '$rootScope', '$routeParams', 'growl','$timeout', function($scope, $http, $rootScope, $routeParams,growl,$timeout) {
        $scope.root = "/imagens/";
        $scope.photos = undefined;
        $scope.evento = undefined;
        $scope.listatipos = [];
        $scope.emails = [];
        $scope.telefones = [];
        $scope.listaLogradouro = [];
        $scope.enderecos = [];
        $scope.listaCidade = [];
        $scope.listaEstados = [];
        $scope.listaPaises = [];

        $scope.fotos = function (lista) {
            $scope.photos = lista;
            $scope._Index = 0;
            $scope.isActive = function (index) {
                return $scope._Index === index;
            };
            $scope.showPrev = function () {
                $scope._Index = ($scope._Index > 0) ? --$scope._Index : $scope.photos.length - 1;
            };
            $scope.showNext = function () {
                $scope._Index = ($scope._Index < $scope.photos.length - 1) ? ++$scope._Index : 0;
            };
            $scope.showPhoto = function (index) {
                $scope._Index = index;
            };
        };
        
        
        $scope.eventoSite = {
                id: "",
                nome: "",
                quantidade_vagas: "",
                descricao: "",
                local_evento: "",
                valor: "",
                data_inicio: "",
                data_fim: "",
                instrutor: "",
                cargaHoraria: "",
                agendaEvento: [],
                enderecos: [],
                fotos: []
            };
        


        $scope.init = function () {

            $scope.loadTodosEventos();
            $scope.loadTodosTipo();            
            $scope.loadTodosPaises();
            $scope.novoEmail();
            $scope.novoEndereco();
            $scope.novoTelefone();
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
            
        };

        $scope.temp = {
            id: '',
            nome: '',
            cpf: '',
            rg: '',
            sexo: '',
            dataNascimento: ''
        };

        $scope.daoInscritoSite = {
            id: '',
            idPessoa: '',
            idEvento: ''
        };


        $scope.getId = function (index) {
            $scope.loadTodosTipo();
            $scope.loadTodosPaises();
            $scope.novoEmail();
            $scope.novoEndereco();
            $scope.novoTelefone();
            $scope.indice = index;
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
        };


        $scope.buscarPessoa = function(cpf) {
            $http({
                method: "GET",
                url: "./carregar/inscrito/" + cpf,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}

            }).success(function(data, status) {
            	/*recuperar dados da pessoa*/
            	if(data !== "")

                $scope.pessoa = data;
            	
                $scope.telefones.numero = data.telefones[0].numero;
                $scope.telefones.tipotelefone = data.telefones[0].tipotelefone;
                $scope.emails.email = data.emails[0].email;
                
                /*setando país*/
                $scope.enderecos.pais = data.enderecos[0].pais;
                /*buscando UF*/
                $scope.procurarUfPorIdPais($scope.enderecos.pais.id);
                /*Setando UF*/
                $scope.enderecos.uf = data.enderecos[0].uf;
                /*buscando cidade*/
                $scope.procurarCidadePorIdUf($scope.enderecos.uf.id);
                /*Setando cidade*/
                $scope.enderecos.cidade = data.enderecos[0].cidade;
                /*buscando bairro*/
                $scope.procurarBairroPorIdCidade($scope.enderecos.cidade.id);
                /*setando bairro*/
                $scope.enderecos.bairro = data.enderecos[0].bairro;
                /*buscando rua*/
                $scope.procurarLogradouroPorIdBairro($scope.enderecos.bairro.id);
                /*setando rua*/
                $scope.enderecos.logradouro = data.enderecos[0].logradouro;
                
                $scope.enderecos.cep = data.enderecos[0].cep;
                $scope.enderecos.complemento = data.enderecos[0].complemento;
                $scope.enderecos.numero = data.enderecos[0].numero;

            }).error(function (data, status) {
                console.log(data);
            });
        };

        $scope.consultaCPFInscrito = function (cpf) {
            $http({
                method: "GET",
                url: "./carregar/inscrito/" + cpf,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                if (data !== "") {
                    $scope.temp = data;
                    $scope.salvarInscrito();
                } else {
                    $scope.pessoa.emails.push({email: $scope.emails.email});
                    $scope.pessoa.telefones.push({numero: $scope.telefones.numero, tipotelefone: $scope.telefones.tipotelefone});
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

                    $scope.salvarPessoa();

                }
            }).error(function (data, status) {
                console.log(data);
            });
        };

        $scope.validaCpf = function (cpf) {

            if (!validaCPF(cpf)) {
            	growl.error("CPF inválido!");
                $scope.pessoa.cpf = '';
            }

            $scope.buscarPessoa(cpf);
            
        };


        $scope.salvar = function () {

            $scope.consultaCPFInscrito($scope.pessoa.cpf);
        };

        $scope.salvarPessoa = function () {
            $http({
                method: "POST",
                url: "pessoa/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.pessoa)
            }).success(function (data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                $scope.consultaCPFInscrito($scope.pessoa.cpf);
            }).error(function (data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                growl.error("Não foi possível efetuar o cadastro!");
            });
        };


        $scope.salvarInscrito = function () {
            $scope.daoInscritoSite.id = undefined;
            $scope.daoInscritoSite.idPessoa = $scope.temp.id;
            $scope.daoInscritoSite.idEvento = $routeParams.objeto;
            $http({
                method: "POST",
                url: "evento/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson($scope.daoInscritoSite)

            }).success(function(data, status) {
            	growl.success("Obrigado, contamos com a sua presença " + $scope.pessoa.nome);
                $scope.init();
            }).error(function (data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                if (status == 500) {
                	growl.info($scope.pessoa.nome +", você já está inscrito neste evento!");
                } else {
                	growl.error($scope.pessoa.nome + ", houve um problema em nosso servidor e não foi possível efetuar sua inscrição ");
                }
                $scope.init();
            });
        };


        $scope.loadTodosEventos = function () {
            $http({
                method: "GET",
                url: "evento/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaeventos = data;
            }).error(function (data, status) {
                alert("Não foi possível listar os Eventos!");
                console.log(data);
            });
        };

        $scope.loadTodosTipo = function () {
            $http({
                method: "GET",
                url: "tipoTelefone/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listatipos = data;
                // $scope.telefones.tipotelefone = $scope.listatipos[0];
            }).error(function (data, status) {
                //growl.info("Não foi possível localizar os Tipos de Telefone!");
            });
        };

        $scope.procurarBairroPorIdCidade = function (id) {
            $http({
                method: "GET",
                url: "bairro/procurarBairroPorIdCidade/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaBairro = data;
            }).error(function (data, status) {
                //growl.info("Não foi possível localizar os Bairros!");
            });
        };

        $scope.procurarCidadePorIdUf = function (id) {
            $http({
                method: "GET",
                url: "cidade/procurarCidadePorIdUf/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaCidade = data;
            }).error(function (data, status) {
                console.log(JSON.stringify(status));
                // growl.info("Não foi possível localizar as Cidades!");
            });
        };

        $scope.procurarLogradouroPorIdBairro = function (id) {
            $http({
                method: "GET",
                url: "logradouro/procurarLogradouroPorIdBairro/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaLogradouro = data;
            }).error(function (data, status) {
                // growl.info("Não foi possível localizar os Logradouros!");
            });
        };

        $scope.procurarUfPorIdPais = function (id) {
            $http({
                method: "GET",
                url: "uf/findbyCodigoPais/" + id,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                console.log(JSON.stringify(status));
                $scope.listaEstados = data;
            }).error(function (data, status) {
                console.log(JSON.stringify(status));
                //growl.info("Não foi possível localizar os Estados!");
            });
        };

        $scope.loadTodosPaises = function () {
            $http({
                method: "GET",
                url: "pais/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaPaises = data;
            }).error(function (data, status) {
                //growl.info("Não foi possível localizar os Países!");
            });
        };

        $scope.novoEmail = function () {
            $scope.emails.email = "";
        };

        $scope.novoTelefone = function () {
            $scope.telefones.numero = "";
            $scope.telefones.tipotelefone = [];
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

    }
]);

siteApp.controller('SiteMaterialController', ['$scope', '$http', '$rootScope', '$routeParams', function ($scope, $http, $rootScope, $routeParams) {
        $scope.listarlivros = [];

        $scope.loadTodosLivros = function () {
            $http({
                method: "GET",
                url: "material/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listarlivros = data;
                console.log(data);
            }).error(function (data, status) {
                console.log(data);
                growl.error("Não foi possível listar os Livros!");
            });
        };

    }
]);

siteApp.controller('SitePedidoController', ['$scope', '$http', '$rootScope', '$routeParams', function ($scope, $http, $rootScope, $routeParams) {
        $scope.qtdMaterial = '';
        $scope.pedido = {
            id: ""
        };
       
        $scope.pedido.solicitante = {enderecos: [], telefones: [], emails: []};
        $scope.pedido.pedidoMaterial = [];
        $scope.listaMaterial = [];
        $scope.material = '';
        

        $scope.somenteNumero = function (campo) {
            var digits = "0123456789.";
            var campo_temp;
            for (var i = 0; i < campo.length; i++) {
                campo_temp = campo.substring(i, i + 1);
                if (digits.indexOf(campo_temp) == -1) {
                    campo = campo.substring(0, i);
                }
            }
            return campo;
        };

        $scope.calcularQtdMaterial = function () {
            $scope.qtdMaterial = $scope.pedido.pedidoMaterial.length;


        };
        $scope.salvarPedido = function () {
            console.log($scope.pedido);
            $http({
                method: "POST",
                url: "pedido/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: angular.toJson($scope.pedido)
            }).success(function (data, status) {
                console.log(data);
            }).error(function (data, status) {
                console.log(data);                
            });

        };
        $scope.selecionarMaterial = function (index) {
            $scope.pedido.pedidoMaterial.push({material: $scope.listaMaterial[index], volumes: []});
            $scope.calcularQtdMaterial();
        };
        $scope.delMaterial = function (index) {
            $scope.pedido.pedidoMaterial.splice(index, 1);
            $scope.calcularQtdMaterial();
        };

        $scope.loadListaMaterial = function (valor) {
            $http({
                method: "GET",
                url: "./material/busca/" + valor,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                $scope.listaMaterial = data;


            }).error(function (data, status) {
                console.log(JSON.stringify(data));
                $scope.listaMaterial = "";

            });
            $scope.calcularQtdMaterial();
        };

        $scope.buscaSolicitante = function (cpf) {
            $http({
                method: "GET",
                url: "./carregar/pessoa/" + cpf,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                console.log(data);
                if (data.cpf) {
                    $scope.pedido.solicitante = data;
                }
            });
        };
        $scope.buscaEscola = function (cnpj) {
            $http({
                method: "GET",
                url: "./carregar/escola/" + cnpj,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                if (data.cnpj) {
                    $scope.pedido.escola = data;
                }
                console.log(data);
            });

        };

        $scope.buscaProfessor = function (cpf) {

            $http({
                method: "GET",
                url: "./carregar/professor/" + cpf,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                if (data.cpf) {
                    $scope.pedido.professor = data;
                }
            });
        };

        $scope.buscaAluno = function (cpf) {

            $http({
                method: "GET",
                url: "./carregar/aluno/" + cpf,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function (data, status) {
                if (data.cpf) {
                    $scope.pedido.aluno = data;
                }
            });
        };
    }
]);
siteApp.controller('EmailController', ['$scope', '$http', '$rootScope', '$routeParams', function ($scope, $http, $rootScope, $routeParams) {

        $scope.caminhoinicial = "#/Site/Contato.html/";
        $scope.email = {nome: "", email: "", assunto: "", mensagem: ""};
        $scope.emailenviado = false;


        $scope.salvar = function () {
            $scope.emailenviado = true;
            $http({
                method: "POST",
                url: "./email/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.email)
            });
        };


    }
]);
