<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html xmlns:ng="http://angularjs.org" lang="pt">
    <head>
        <meta charset="UTF-8">
        <link rel="shortcut icon" href="./resources/imagens/favicon.ico"> 
        <link rel="stylesheet" href="./resources/css/bootstrap.min.css" >
        <link rel="stylesheet" href="./resources/css/style.css" >
        <link rel="stylesheet" href="./resources/css/loading-bar.css" >
        
        <script type="text/javascript" src="./resources/js/angular.min.js"></script>
        <script type="text/javascript">
            //this is here to make plunkr work with AngularJS routing
            angular.element(document.getElementsByTagName('head')).append(angular.element('<base href="' + window.location.pathname + '" />'));
        </script>

        <title>Escola de TI - 2014 - Time 5</title>
    </head>
    <body ng-app="escoladeti" ng-controller="MainController">
        <!-- Inicio nav-bar -->
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">AMACAP</a>
                </div>

                <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
                    <!-- Dropdown de Pessoa -->
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle" data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span> Pessoa <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#/Pessoa/Usuario">Usuário</a></li>
                                <!--<li><a href="#/Pessoa/Cliente">Cliente</a></li>-->
                            </ul>
                        </li>
                    </ul>
                    <!-- Dropdown de Localização -->
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle" data-toggle="dropdown">
                                <span class="glyphicon glyphicon-map-marker"></span> Localização
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#/Localizacao/Pais">País</a></li>
                                <li><a href="#/Localizacao/Estado">Estado</a></li>
                                <li><a href="#/Localizacao/Cidade">Cidade</a></li>
                                <li><a href="#/Localizacao/Distrito">Distrito</a></li>
                                <li><a href="#/Localizacao/Bairro">Bairro</a></li>
                                <!--<li><a href="#/Localizacao/Logradouro">Logradouro</a></li>-->
                                <li><a href="#/Localizacao/TipoLogradouro">Tipo Logradouro</a></li>
                            </ul>
                        </li>
                    </ul>
                    <!-- Dropdown de Operações -->
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle" data-toggle="dropdown"> <span class="glyphicon glyphicon-wrench"></span> Operações <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#Operacao/UnidadeProducao">Unidade de Produção</a></li>
                            </ul>
                        </li>
                    </ul>
                    <!-- Dropdown de Configuração -->
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-cog"></span> Configuração <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                               <!-- <li><a href="#Configuracao/PerfilAcesso">Perfil de Acesso</a></li>-->
                                <li><a href="#Configuracao/Funcao">Função</a></li>
                            </ul>
                        </li>
                    </ul>
                    <!-- Dropdown de Login -->
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a  class="dropdown-toggle" data-toggle="dropdown"> Seja bem vindo, Usuário! <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="./logout"><label class="glyphicon glyphicon-off"></label> Sair</a></li>
                            </ul>
                        </li>
                    </ul>

                </div><!-- Fim navbar-collapse -->
            </div><!-- Fim container-fluid -->
        </nav> <!-- Fim nav -->

        <!-- Página: Icones de Acesso -->
        <div class="container">
            <div class="row">
                <div class="col-sm-10 col-sm-offset-1 col-md-6 col-md-offset-3" >
                    <div class="row">
                    </div> <!-- Fim row -->
                </div> <!-- Fim col -->
            </div> <!-- Fim row -->
        </div> <!-- Fim container -->

        <div class="container">
            <div ng-view></div>
        </div>

    </body>
    
    <script type="text/javascript" src="./resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="./resources/js/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="./resources/js/bootstrap.min.js"></script>    
    <script type="text/javascript" src="./resources/js/angular-route.min.js"></script>
    <script type="text/javascript" src="./resources/js/loading-bar.js"></script>
    <script type="text/javascript" src="./resources/app/app.js"></script>
    <script type="text/javascript" src="./resources/app/validacao.js"></script>
    <script type="text/javascript" src="./resources/app/element.js"></script>
    <script type="text/javascript" src="./resources/js/mask.js"></script>
    <script type="text/javascript" src="./Controller/CidadeController.js"></script>
    <script type="text/javascript" src="./Controller/UsuarioController.js"></script>
    <script type="text/javascript" src="./Controller/ClienteController.js"></script>
    <script type="text/javascript" src="./Controller/DistritoController.js"></script>
    <script type="text/javascript" src="./Controller/PaisController.js"></script>
    <script type="text/javascript" src="./Controller/PerfilAcessoController.js"></script>
    <script type="text/javascript" src="./Controller/EstadoController.js"></script>
    <script type="text/javascript" src="./Controller/UnidadeProducaoController.js"></script>
    <script type="text/javascript" src="./Controller/BairroController.js"></script>
    <script type="text/javascript" src="./Controller/LogradouroController.js"></script>
    <script type="text/javascript" src="./Controller/TipoLogradouroController.js"></script>
    <script type="text/javascript" src="./Controller/FuncaoController.js"></script>
    <script type="text/javascript" src="./resources/js/paginacao.js"></script>

</html>
