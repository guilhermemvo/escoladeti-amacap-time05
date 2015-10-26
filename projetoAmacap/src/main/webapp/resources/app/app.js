    var myApp = angular.module('escoladeti', ['ngRoute', 'escoladeti.cidade', 'escoladeti.pais', 'escoladeti.estado',
    'escoladeti.distrito', 'escoladeti.usuario', 'escoladeti.cliente', 'escoladeti.unidadeProducao',
    'escoladeti.bairro', 'escoladeti.perfilAcesso', 'ui.mask', 'angular-loading-bar', 'escoladeti.logradouro',
    'escoladeti.funcao', 'escoladeti.tipologradouro']);

myApp.controller('MainController', function($scope) {
    $scope.visualizacaoAtiva = false;
    $scope.confirmarSenha = "";
});

myApp.config(['$routeProvider', '$locationProvider',
    function($routeProvider, $locationProvider) {
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
                    templateUrl: './View/Localizacao/Estado/listarEstado.html',
                    controller: 'EstadoController'
                })
                .when('/Localizacao/Estado/:objeto', {
                    templateUrl: './View/Localizacao/Estado/editarEstado.html',
                    controller: 'EstadoController'
                })
                .when('/Localizacao/Distrito', {
                    templateUrl: './View/Localizacao/Distrito/listarDistrito.html',
                    controller: 'DistritoController'
                })
                .when('/Localizacao/Distrito/:objeto', {
                    templateUrl: './View/Localizacao/Distrito/editarDistrito.html',
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
                    templateUrl: './View/Localizacao/Logradouro/listarLogradouro.html',
                    controller: 'LogradouroController'
                })
                .when('/Localizacao/Logradouro/:objeto', {
                    templateUrl: './View/Localizacao/Logradouro/editarLogradouro.html',
                    controller: 'LogradouroController'
                })
                .when('/Localizacao/TipoLogradouro', {
                    templateUrl: './View/Localizacao/TipoLogradouro/listarTipoLogradouro.html',
                    controller: 'TipoLogradouroController'
                })
                .when('/Localizacao/TipoLogradouro/:objeto', {
                    templateUrl: './View/Localizacao/TipoLogradouro/editarTipoLogradouro.html',
                    controller: 'TipoLogradouroController'
                })

                //Configuração
                .when('/Configuracao/PerfilAcesso', {
                    templateUrl: './View/Configuracao/PerfilAcesso/listarPerfilAcesso.html'
                })
                .when('/Configuracao/PerfilAcesso/:objeto', {
                    templateUrl: './View/Configuracao/PerfilAcesso/editarPerfilAcesso.html'
                })
                .when('/Configuracao/Funcao', {
                    templateUrl: './View/Configuracao/Funcao/listarFuncao.html'
                })
                .when('/Configuracao/Funcao/:objeto', {
                    templateUrl: './View/Configuracao/Funcao/editarFuncao.html'
                })

                //Pessoa
                .when('/Pessoa/Cliente', {
                    templateUrl: './View/Pessoa/Cliente/listarCliente.html',
                    controller: 'ClienteController'
                })
                .when('/Pessoa/Cliente/:objeto', {
                    templateUrl: './View/Pessoa/Cliente/editarCliente.html',
                    controller: 'ClienteController'
                })
                .when('/Pessoa/Usuario', {
                    templateUrl: './View/Pessoa/Usuario/listarUsuario.html',
                    controller: 'UsuarioController'
                })
                .when('/Pessoa/Usuario/:objeto', {
                    templateUrl: './View/Pessoa/Usuario/editarUsuario.html',
                    controller: 'UsuarioController'
                })
                .when('/Operacao/UnidadeProducao', {
                    templateUrl: './View/Operacao/UnidadeProducao/listarUnidadeProducao.html',
                    controller: 'UnidadeProducaoController'
                })
                .when('/Operacao/UnidadeProducao/:objeto', {
                    templateUrl: './View/Operacao/UnidadeProducao/editarUnidadeProducao.html',
                    controller: 'UnidadeProducaoController'
                })
                .otherwise({redirectTo: '/'});

    }]);