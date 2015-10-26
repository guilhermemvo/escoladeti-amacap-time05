var myApp = angular.module('escoladeti', ['ngRoute', 'angularFileUpload', 'ui.mask', 'angular-growl', 'angular-loading-bar', 'brasil.filters',
    'escoladeti.pais', 'escoladeti.estado', 'escoladeti.cidade', 'escoladeti.distrito', 'escoladeti.bairro', 'escoladeti.tipologradouro', 'escoladeti.logradouro',
    'escoladeti.unidadeProducao', 'escoladeti.funcao',
    'escoladeti.usuario', 'escoladeti.aluno', 'escoladeti.funcionario', 'escoladeti.instrutor', 'escoladeti.professor',
    'escoladeti.editora', 'escoladeti.escola', 'escoladeti.transportadora', 'escoladeti.disciplina', 'escoladeti.evento', 'escoladeti.livro', 'escoladeti.volume', 'escoladeti.materiaprima',
    'escoladeti.tipomaterial', 'escoladeti.perfilAcesso', 'escoladeti.outros', 'escoladeti.pedido', 'escoladeti.inscritos', 'escoladeti.associadopf', 'escoladeti.associadopj', 'escoladeti.solicitante',
    'escoladeti.pedidoPendente', 'escoladeti.pedidoProducao', 'escoladeti.movassociadopj', 'escoladeti.movassociadopf', 'escoladeti.galeriaEvento', 'ui.bootstrap',  'escoladeti.pedidoService' 

]);

myApp.controller('MainController', function ($scope, $rootScope, $http, pedidoService) {
    $scope.label = "";
    $scope.visualizacaoAtiva = false;
    $scope.confirmarSenha = "";
    $scope.msg = "";
    $scope.cancelarOuExcluir = "";
    $scope.editar = function () {
        document.getElementById("field_set").disabled = false;
        this.visualizacaoAtiva = false;
    };
    $rootScope.confirmaExclusao = function (index, label) {
    	var check = $rootScope.stringCancelamento;
    	if(check === 'cancelar'){
    		$scope.cancelarOuExcluir = "cancelar";
    	}else{
    		$scope.cancelarOuExcluir = "excluir";
    	}
        $scope.label = label;
        $scope.modalExcluir = index;
        $('#modalExcluir').modal('show');
        $rootScope.stringCancelamento = '';//atribui excluir aqui
    };
    
    $scope.initLoad = function () {
        $scope.getCountPendentes();
    };
    
    $rootScope.getCountPendentes = function () {
        pedidoService.getCountPendentes()
            .success(function (data) {
                $scope.pedidosPendentes = data;
            }).error(function (data, status) {
                alert("Erro ao contar os pedidos pendentes");
            });
    };
    
});

function excluirQuem() {
    if (modalExcluir.nome !== '') {
        return modalExcluir.nome;
    } else if (modalExcluir.descricao !== '') {
        return modalExcluir.descricao;
    } else if (modalExcluir.razao !== '') {
        return modalExcluir.razao;
    }
}

myApp.config(['$routeProvider', '$locationProvider', 'growlProvider',
    function ($routeProvider, $locationProvider, growlProvider) {
        growlProvider.globalTimeToLive({success: 5000, error: 5000, warning: 5000, info: 5000});
        $locationProvider.html5Mode(false);
        $routeProvider
                //Localização
                .when('/Localizacao/Pais', {
                    templateUrl: './View/listar.html',
                    controller: 'PaisController'
                })
                .when('/Localizacao/Pais/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'PaisController'
                })
                .when('/Localizacao/Cidade', {
                    templateUrl: './View/listar.html',
                    controller: 'CidadeController'
                })
                .when('/Localizacao/Cidade/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'CidadeController'
                })
                .when('/Localizacao/Estado', {
                    templateUrl: './View/listar.html',
                    controller: 'EstadoController'
                })
                .when('/Localizacao/Estado/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'EstadoController'
                })
                .when('/Localizacao/Distrito', {
                    templateUrl: './View/listar.html',
                    controller: 'DistritoController'
                })
                .when('/Localizacao/Distrito/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'DistritoController'
                })
                .when('/Localizacao/Bairro', {
                    templateUrl: './View/listar.html',
                    controller: 'BairroController'
                })
                .when('/Localizacao/Bairro/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'BairroController'
                })
                .when('/Localizacao/Logradouro', {
                    templateUrl: './View/listar.html',
                    controller: 'LogradouroController'
                })
                .when('/Localizacao/Logradouro/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'LogradouroController'
                })

                //Configuração
                .when('/Configuracao/PerfilAcesso/', {
                    templateUrl: './View/listar.html',
                    controller: 'PerfilAcessoController'
                })
                .when('/Configuracao/PerfilAcesso/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'PerfilAcessoController'
                })
                .when('/Configuracao/Funcao', {
                    templateUrl: './View/listar.html',
                    controller: 'FuncaoController'
                })
                .when('/Configuracao/Funcao/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'FuncaoController'
                })
                .when('/Configuracao/Disciplina', {
                    templateUrl: './View/listar.html',
                    controller: 'DisciplinaController'
                })
                .when('/Configuracao/Disciplina/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'DisciplinaController'
                })
                .when('/Configuracao/TipoMaterial', {
                    templateUrl: './View/listar.html',
                    controller: 'TipoMaterialController'
                })
                .when('/Configuracao/TipoMaterial/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'TipoMaterialController'
                })
                .when('/Configuracao/TipoLogradouro', {
                    templateUrl: './View/listar.html',
                    controller: 'TipoLogradouroController'
                })
                .when('/Configuracao/TipoLogradouro/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'TipoLogradouroController'
                })

                //Operações
                .when('/Operacao/Evento', {
                    templateUrl: './View/listar.html',
                    controller: 'EventoController'
                })
                .when('/Operacao/Evento/:objeto/Galeria', {
                    templateUrl: './View/editar.html',
                    controller: 'EventoGaleriaController'
                })
                .when('/Operacao/Evento/:objeto/Inscritos', {
                    templateUrl: './View/editar.html',
                    controller: 'EventoInscritosController'
                })
                .when('/Operacao/Evento/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'EventoController'
                })
                .when('/Operacao/Evento/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'EventoController'
                })

                //Material
                .when('/Material/Livro', {
                    templateUrl: './View/listar.html',
                    controller: 'LivroController'
                })
                .when('/Material/Livro/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'LivroController'
                })
                .when('/Material/Outros', {
                    templateUrl: './View/listar.html',
                    controller: 'OutrosController'
                })
                .when('/Material/Outros/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'OutrosController'
                })
                .when('/Material/MateriaPrima', {
                    templateUrl: './View/listar.html',
                    controller: 'MateriaPrimaController'
                })
                .when('/Material/MateriaPrima/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'MateriaPrimaController'
                })


                //Pessoa
                .when('/Pessoa/Usuario', {
                    templateUrl: './View/listar.html',
                    controller: 'UsuarioController'
                })
                .when('/Pessoa/Usuario/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'UsuarioController'
                })
                .when('/Pessoa/Aluno', {
                    templateUrl: './View/listar.html',
                    controller: 'AlunoController'
                })
                .when('/Pessoa/Aluno/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'AlunoController'
                })
                .when('/Pessoa/Professor', {
                    templateUrl: './View/listar.html',
                    controller: 'ProfessorController'
                })
                .when('/Pessoa/Professor/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'ProfessorController'
                })

                .when('/Pessoa/AssociadoPF', {
                    templateUrl: './View/listar.html',
                    controller: 'AssociadoPFController'
                })
                .when('/Pessoa/AssociadoPF/:objeto/Pagamento', {
                    templateUrl: './View/editar.html',
                    controller: 'MovimentacaoAssociadoPFController'
                })
                .when('/Pessoa/AssociadoPF/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'AssociadoPFController'
                })
                .when('/Pessoa/AssociadoPJ', {
                    templateUrl: './View/listar.html',
                    controller: 'AssociadoPJController'
                })
                .when('/Pessoa/AssociadoPJ/:objeto/Pagamento', {
                    templateUrl: './View/editar.html',
                    controller: 'MovimentacaoAssociadoPJController'
                })
                .when('/Pessoa/AssociadoPJ/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'AssociadoPJController'
                })
                .when('/Pessoa/Instrutor', {
                    templateUrl: './View/listar.html',
                    controller: 'InstrutorController'
                })
                .when('/Pessoa/Instrutor/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'InstrutorController'
                })
                .when('/Pessoa/Funcionario', {
                    templateUrl: './View/listar.html',
                    controller: 'FuncionarioController'
                })
                .when('/Pessoa/Funcionario/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'FuncionarioController'
                })
                .when('/Pessoa/Escola', {
                    templateUrl: './View/listar.html',
                    controller: 'EscolaController'
                })
                .when('/Pessoa/Escola/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'EscolaController'
                })
                .when('/Pessoa/Transportadora', {
                    templateUrl: './View/listar.html',
                    controller: 'TransportadoraController'
                })
                .when('/Pessoa/Transportadora/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'TransportadoraController'
                })
                .when('/Pessoa/Editora', {
                    templateUrl: './View/listar.html',
                    controller: 'EditoraController'
                })
                .when('/Pessoa/Editora/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'EditoraController'
                })
                .when('/Pessoa/UnidadeProducao', {
                    templateUrl: './View/listar.html',
                    controller: 'UnidadeProducaoController'
                })
                .when('/Pessoa/UnidadeProducao/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'UnidadeProducaoController'
                })
                .when('/Pessoa/Solicitante', {
                    templateUrl: './View/listar.html',
                    controller: 'SolicitanteController'
                })
                .when('/Pessoa/Solicitante/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'SolicitanteController'
                })


                //Pedido
                .when('/Pedido/Pedido', {
                    templateUrl: './View/listar.html',
                    controller: 'PedidoController'
                })
                .when('/Pedido/Pedido/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'PedidoController'
                })
                .when('/Pedido/Pendente', {
                    templateUrl: './View/listar.html',
                    controller: 'PedidoPendenteController'
                })
                .when('/Pedido/Pendente/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'PedidoPendenteController'
                })
                .when('/Pedido/Producao', {
                    templateUrl: './View/listar.html',
                    controller: 'PedidoProducaoController'
                })
                .when('/Pedido/Producao/:objeto/:objeto2', {
                    templateUrl: './View/editar.html',
                    controller: 'PedidoProducaoController'
                })
                .otherwise({redirectTo: '/'});

    }]);
