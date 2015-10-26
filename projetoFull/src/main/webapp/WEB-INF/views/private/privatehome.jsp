<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns:ng="http://angularjs.org" lang="pt">
    <head>
        <link rel="shortcut icon" href="./resources/imagens/favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="./resources/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="./resources/fonts/css/font-awesome.css">
        <link rel="stylesheet" type="text/css" href="./resources/css/angular-growl.css">

        <link rel="stylesheet" type="text/css" href="./resources/componentes/jquery.magnific-popup/dist/magnific-popup.css" />
        <link rel="stylesheet" type="text/css" href="./resources/css/stylesistema.css"/>

        <title>AMACAP</title>

    </head>
    <body class="animated" ng-app="escoladeti" ng-controller="MainController" ng-init="initLoad()">
        <div id="cl-wrapper">
            <div class="cl-sidebar">
                <div class="cl-toggle"><i class="fa fa-bars"></i></div>
                <div class="cl-navblock">
                    <div class="menu-space">
                        <div class="content">
                            <div class="sidebar-logo">
                                <a href="/#">
                                    <div class="logo">
                                    </div>
                                </a>
                            </div>
                            <ul class="cl-vnavigation">
                                <!--
                                <li>
                                    <a href="#"><i class="fa fa-fw fa-shopping-cart"></i><span> Pedido</span></a>
                                    <ul class="sub-menu">
                                        <li><a href="#/Pedido/Pedido/Novo/Editar"><span class="fa fa-caret-right"></span> Novo Pedido</a></li>
                                        <li><a href="#/Pedido/Producao"><span class="fa fa-caret-right"></span> Produção</a></li>
                                        <li><a href="#Pedido/Pendente"><span class="fa fa-caret-right"></span> Pedidos <span class="label label-primary pull-right"> {{pedidosPendentes}} </span></a></li>
                                    </ul>
                                </li>
                                -->
                                <li><a href="#/Pedido/Pendente"><i class="fa fa-fw fa-shopping-cart"></i>Pedidos <span class="label label-primary pull-right"> {{pedidosPendentes}}</span></a></li>
                                <li><a href="#/Pedido/Producao"><i class="fa fa-fw fa-gear"></i>Produção</a></li>
                                <li>
                                    <a href="#/Operacao/Evento" class="nav-header"><i class="fa fa-fw fa-users"></i><span> Eventos</span></a>
                                </li>
                                <li>
                                    <a href="#"><i class="fa fa-fw fa-file-text"></i><span> Material</span></a>
                                    <ul class="sub-menu">
                                        <li><a href="#/Material/Livro"><span class="fa fa-caret-right"></span> Livro</a></li>
                                        <li><a href="#/Material/Outros"><span class="fa fa-caret-right"></span> Outros</a></li>
                                        <!--<li><a href="#/Material/MateriaPrima"><span class="fa fa-caret-right"></span> Matéria Prima</a></li>-->
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><i class="fa fa-fw fa-tasks"></i><span> Cadastros Básicos</span></a>
                                    <ul class="sub-menu">
                                        <li><a href="#/Pessoa/Usuario"><span class="fa fa-caret-right"></span> Usuário</a></li>
                                        <li><a href="#/Pessoa/Aluno"><span class="fa fa-caret-right"></span> Aluno</a></li>
                                        <li><a href="#/Pessoa/Funcionario"><span class="fa fa-caret-right"></span> Funcionário</a></li>
                                        <li><a href="#/Pessoa/Instrutor"><span class="fa fa-caret-right"></span> Instrutor</a></li>
                                        <li><a href="#/Pessoa/Professor"><span class="fa fa-caret-right"></span> Professor</a></li>
                                        <li><a href="#/Pessoa/AssociadoPF"><span class="fa fa-caret-right"></span> Associado Físico</a></li>
                                        <li><a href="#/Pessoa/AssociadoPJ"><span class="fa fa-caret-right"></span> Associado Jurídico</a></li>              
                                        <li><a href="#/Pessoa/Editora"><span class="fa fa-caret-right"></span> Editora</a></li>
                                        <li><a href="#/Pessoa/Transportadora"><span class="fa fa-caret-right"></span> Transportadora</a></li>
                                        <li><a href="#/Pessoa/Escola"><span class="fa fa-caret-right"></span> Escola</a></li>
                                        <li><a href="#/Pessoa/UnidadeProducao"><span class="fa fa-caret-right"></span> Unidade de Operação</a></li>
                                        <li><a href="#/Pessoa/Solicitante"><span class="fa fa-caret-right"></span> Solicitante</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><i class="fa fa-fw fa-wrench"></i><span> Configuração</span></a>
                                    <ul class="sub-menu">
                                        <li><a href="#Configuracao/Funcao"><span class="fa fa-caret-right"></span> Função</a></li>
                                        <li><a href="#/Configuracao/Disciplina"><span class="fa fa-caret-right"></span> Disciplina</a></li>
                                        <li><a href="#/Configuracao/PerfilAcesso"><span class="fa fa-caret-right"></span> Perfil Acesso</a></li>
                                        <li><a href="#/Configuracao/TipoMaterial"><span class="fa fa-caret-right"></span> Tipo Material</a></li>                    
                                        <li><a href="#/Configuracao/TipoLogradouro"><span class="fa fa-caret-right"></span> Tipo Logradouro</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><i class="fa fa-fw fa-map-marker"></i><span> Localização</span></a>
                                    <ul class="sub-menu">
                                        <li><a href="#/Localizacao/Pais"><span class="fa fa-caret-right"></span> País</a></li>
                                        <li ><a href="#/Localizacao/Estado"><span class="fa fa-caret-right"></span> Estado</a></li>
                                        <li ><a href="#/Localizacao/Cidade"><span class="fa fa-caret-right"></span> Cidade</a></li>
                                        <li ><a href="#/Localizacao/Distrito"><span class="fa fa-caret-right"></span> Distrito</a></li>
                                        <li ><a href="#/Localizacao/Bairro"><span class="fa fa-caret-right"></span> Bairro</a></li>
                                        <li ><a href="#/Localizacao/Logradouro"><span class="fa fa-caret-right"></span> Logradouro</a></li>    
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="text-right collapse-button" style="padding:7px 9px;">
                        <button id="sidebar-collapse" class="btn btn-default" style="">
                            <i style="color:#fff;" class="fa fa-angle-left"></i>
                        </button>
                    </div>
                </div>
            </div>
            <div class="container-fluid" id="pcont">
                <!-- USUARIO -->
                <div id="head-nav" class="navbar navbar-default">
                    <div class="container-fluid">
                        <div class="navbar-collapse">
                            <ul class="nav navbar-nav navbar-right user-nav">
                                <li class="dropdown profile_menu">
                                    <a class="dropdown-toggle" data-toggle="dropdown"><span> ${username} </span> <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a tabindex="-1" href="./logout">Sair</a></li>
                                    </ul>
                                </li>
                            </ul>			
                        </div>
                    </div>
                </div>
                <div class="content">
                    <div growl></div>
                    <div ng-view></div>
                </div>
            </div>
        </div> 
    </body>
    <script type="text/javascript" src="./resources/js/angular.min.js"></script>
    <script type="text/javascript" src="./resources/js/jquery-1.11.1.js" ></script>
    <script type="text/javascript" src="./resources/js/jquery.knob.js" ></script>
    <script type="text/javascript" src="./resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="./resources/js/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="./resources/js/plugins.js" ></script>
    <script type="text/javascript" src="./resources/js/bootstrap.min.js"></script>    
    <script type="text/javascript" src="./resources/js/angular-route.min.js"></script>
    <script type="text/javascript" src="./resources/js/loading-bar.js"></script>
    <script type="text/javascript" src="./resources/app/app.js"></script>
    <script type="text/javascript" src="./resources/app/validacao.js"></script>
    <script type="text/javascript" src="./resources/app/forcaSenha.js"></script>
    <script type="text/javascript" src="./resources/app/element.js"></script>
    <script type="text/javascript" src="./resources/js/mask.js"></script>
    <script type="text/javascript" src="./resources/js/paginacao.js"></script>
    <script type="text/javascript" src="./resources/js/filters.js"></script>
    <script type="text/javascript" src="./resources/js/angular-file-upload.js"></script>
    <script type="text/javascript" src="./resources/js/angular-growl.js"></script>
    <script type="text/javascript" src="./resources/js/angular-locale_pt-br.js"></script>
    <script type="text/javascript" src="./resources/js/ui-bootstrap-tpls-0.12.0.min.js"></script>
    <script type="text/javascript" src="./resources/js/angular-growl.js"></script>

    <script type="text/javascript" src="./resources/componentes/jquery.cookie/jquery.cookie.js"></script>
    <script type="text/javascript" src="./resources/componentes/jquery.pushmenu/js/jPushMenu.js"></script>
    <script type="text/javascript" src="./resources/componentes/jquery.nanoscroller/jquery.nanoscroller.js"></script>
    <script type="text/javascript" src="./resources/componentes/behaviour/core.js"></script>
    <script type="text/javascript" src="./resources/componentes/masonry.js"></script>
    <script type="text/javascript" src="./resources/componentes/jquery.magnific-popup/dist/jquery.magnific-popup.min.js"></script>


    <!--Controllers-->
    <script type="text/javascript" src="./Controller/CidadeController.js"></script>
    <script type="text/javascript" src="./Controller/DistritoController.js"></script>
    <script type="text/javascript" src="./Controller/PaisController.js"></script>
    <script type="text/javascript" src="./Controller/PerfilAcessoController.js"></script>
    <script type="text/javascript" src="./Controller/EstadoController.js"></script>
    <script type="text/javascript" src="./Controller/UnidadeProducaoController.js"></script>
    <script type="text/javascript" src="./Controller/BairroController.js"></script>
    <script type="text/javascript" src="./Controller/LogradouroController.js"></script>
    <script type="text/javascript" src="./Controller/TipoLogradouroController.js"></script>
    <script type="text/javascript" src="./Controller/FuncaoController.js"></script>
    <script type="text/javascript" src="./Controller/AlunoController.js"></script>
    <script type="text/javascript" src="./Controller/FuncionarioController.js"></script>
    <script type="text/javascript" src="./Controller/ProfessorController.js"></script>
    <script type="text/javascript" src="./Controller/UsuarioController.js"></script>
    <script type="text/javascript" src="./Controller/InstrutorController.js"></script>
    <script type="text/javascript" src="./Controller/EditoraController.js"></script>
    <script type="text/javascript" src="./Controller/EscolaController.js"></script>
    <script type="text/javascript" src="./Controller/TransportadoraController.js"></script>
    <script type="text/javascript" src="./Controller/DisciplinaController.js"></script>
    <script type="text/javascript" src="./Controller/MateriaPrimaController.js"></script>
    <script type="text/javascript" src="./Controller/EventoController.js"></script>
    <script type="text/javascript" src="./Controller/EventoInscritosController.js"></script>
    <script type="text/javascript" src="./Controller/LivroController.js"></script>
    <script type="text/javascript" src="./Controller/VolumeController.js"></script>
    <script type="text/javascript" src="./Controller/TipoMaterialController.js"></script>
    <script type="text/javascript" src="./Controller/OutrosController.js"></script>
    <script type="text/javascript" src="./Controller/PedidoController.js"></script>
    <script type="text/javascript" src="./Controller/PedidoPendenteController.js"></script>
    <script type="text/javascript" src="./Controller/PedidoProducaoController.js"></script>
    <script type="text/javascript" src="./Controller/AssociadoPFController.js"></script>
    <script type="text/javascript" src="./Controller/AssociadoPJController.js"></script>
    <script type="text/javascript" src="./Controller/SolicitanteController.js"></script>
    <script type="text/javascript" src="./Controller/EventoGaleriaController.js"></script>
    <script type="text/javascript" src="./Service/RepositoryService.js"></script>
    <script type="text/javascript" src="./Service/SolicitanteService.js"></script>
    <script type="text/javascript" src="./Service/PedidoService.js"></script>
    <script type="text/javascript" src="./Service/PessoaService.js"></script>
    <script type="text/javascript" src="./Controller/MovimentacaoAssociadoPJController.js"></script>
    <script type="text/javascript" src="./Controller/MovimentacaoAssociadoPFController.js"></script>
    <script type="text/javascript" src="./Service/ValidacaoService.js"></script>

</html>

