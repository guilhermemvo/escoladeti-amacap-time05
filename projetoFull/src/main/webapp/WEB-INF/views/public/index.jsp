<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html xmlns:ng="http://angularjs.org" lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>AMACAP</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="../resources/imagens/favicon.ico">
        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="../resources/fonts/font.css">
        <link rel="stylesheet" type="text/css" href="../resources/fonts/css/font-awesome.css">
        <link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.css" >
        <link rel="stylesheet" type="text/css" href="../resources/css/site/animate.css" >
        <link rel="stylesheet" type="text/css" href="../resources/css/angular-growl.css">
        <link rel="stylesheet" type="text/css" href="../resources/css/site/style.css" >
        <link rel="stylesheet" type="text/css" href="../resources/js/site/woothemes-FlexSlider-06b12f8/flexslider.css" media="screen">
        <link rel="stylesheet" type="text/css" href="../resources/js/site/prettyPhoto_3.1.5/prettyPhoto.css" media="screen">

        <!-- JS -->
        <script type="text/javascript" src="../resources/js/angular.min.js"></script>
        
        <script type="text/javascript" src="../resources/js/site/modernizr.custom.48287.js"></script>
        
        

        <script type="text/javascript">
            angular.element(document.getElementsByTagName('head')).append(angular.element('<base href="' + window.location.pathname + '" />'));
        </script>
    </head>

    <body ng-app="site">
        <header>
            <div class="container">
                <div class="navbar navbar-default" role="navigation">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#/"> 
                            <img src="../resources/imagens/amacap_logo.png" class="img-responsive" alt="Logomarca da AMACAP"> 
                        </a>
                        <a class="btn btn-navbar btn-default navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="nb_left pull-left"><span class="fa fa-reorder"></span></span> <span class="nb_right pull-right"menu</span> 
                        </a>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav pull-right navbar-nav">
                            <li><a href="#/" >Home</a></li>
                            <li class="dropdown"> <a data-toggle="dropdown" class="dropdown-toggle" href="">A AMACAP <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#/sobre">Sobre a AMACAP</a></li>
                                    <li><a href="#/associados">Associados AMACAP</a></li>
                                </ul>
                            </li>
                            <li><a href="#/material/">Acervo</a></li>
                            <li><a href="#/evento/">Eventos</a></li>
                            <li><a href="#/pedido/">Solicite seu Material</a></li>
                            <li><a href="#/contato/">Contato</a></li>
                        </ul>
                    </div>
                </div>
                <div id="social_media_wrapper"> 
                    <a href="/escoladetiTime5/login" data-toggle="tooltip" data-placement="bottom" title="Acesso restrito">
                        <i class="fa fa-lock"></i>
                    </a> 
                </div>
            </div>
        </header>
        <div class="content">
        	<div growl></div>
            <div ng-view> </div>
        </div>


        <footer>
            <section id="footer_teasers_wrapper">
                <div class="container">
                    <div class="row">
                        <div class="footer_teaser col-sm-4 col-md-4">
                            <h3>Sobre nós</h3>
                            <p>Associação Maringaense dos Amigos do Centro de Apoio Pedagógico para Atendimento às Pessoas com Deficiência Visual</p>
                            <p><i class="fa fa-map-marker"></i> R. Martin Afonso, 50 - Zona 02 </p>
                            <p><i class="fa fa-phone"></i> (44)3255-5498</p>
                            <p><i class="fa fa-envelope"></i> email@gmail.com</p>
                        </div>
                        <div class="footer_teaser col-sm-4 col-md-4">

                        </div>
                        <div class="footer_teaser col-sm-4 col-md-4" id="mapa-site">
                            <h3>Mapa do Site</h3>
                            <p><a href="#/sobre">Sobre a AMACAP</a></p>
                            <p><a href="#/associados">Associados AMACAP</a></p>
                            <p><a href="#/material">Acervo</a></p>
                            <p><a href="#/evento">Eventos</a></p>
                            <p><a href="#/pedido">Solicite seu Material</a></p>
                            <p><a href="#/contato">Contato</a></p>
                        </div>
                    </div>
                </div>
            </section>
            <section class="copyright">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-6 col-md-6"> Desenvolvido por: Projeto Integrador Escola de T.I. 2014</div>
                    </div>
                </div>
            </section>
        </footer>
    </body>

    <script type="text/javascript" src="../resources/js/site/jquery-latest.min.js"></script>
    <script>window.jQuery || document.write('<script src="../resources/js/site/jquery-1.9.0.min.js"><\/script>')</script>
    <script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../resources/js/site/woothemes-FlexSlider-06b12f8/jquery.flexslider-min.js"></script>
    <script type="text/javascript" src="../resources/js/site/prettyPhoto_3.1.5/jquery.prettyPhoto.js"></script>
    <script type="text/javascript" src="../resources/js/site/isotope/jquery.isotope.min.js"></script>
    <script type="text/javascript" src="../resources/js/angular-route.min.js"></script>
        <script type="text/javascript" src="../resources/js/angular-animate.min.js"></script>
        
    <script type="text/javascript" src="../resources/js/site/jquery.ui.totop.js"></script>
    <script type="text/javascript" src="../resources/js/site/easing.js"></script>
    <script type="text/javascript" src="../resources/js/site/wow.min.js"></script>
    <script type="text/javascript" src="../resources/js/site/snap.svg-min.js"></script>
    <script type="text/javascript" src="../resources/js/site/restart_theme.js"></script>
    <script type="text/javascript" src="../resources/js/angular-growl.js"></script>
    <script type="text/javascript" src="../resources/js/site/collapser.js"></script>

    <script type="text/javascript" src="../resources/app/validacao.js"></script>        
    <script type="text/javascript" src="../resources/js/loading-bar.js"></script>
    <script type="text/javascript" src="../resources/site/siteapp.js"></script>
    <script type="text/javascript" src="../resources/js/mask.js"></script>

</html>