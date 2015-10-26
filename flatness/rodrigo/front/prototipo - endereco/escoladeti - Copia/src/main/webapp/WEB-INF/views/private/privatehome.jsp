<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html xmlns:ng="http://angularjs.org" lang="pt">
    <head>
        <meta charset="UTF-8">
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

        <link href="./resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="./resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="./resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <link href="./resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="./resources/css/bootstrap.min.css" >

        <script type="text/javascript" src="./resources/js/angular.min.js"></script>
        <script type="text/javascript" src="./resources/js/jquery.min.js"></script>
        <script type="text/javascript" src="./resources/js/jquery-ui.custom.min.js"></script>
        <script type="text/javascript" src="./resources/js/bootstrap.min.js"></script>    
        <script type="text/javascript" src="./resources/js/angular-route.min.js"></script>
        <script type="text/javascript" src="./resources/app/app.js"></script>
        <script type="text/javascript" src="./resources/app/endereco/endereco.js"></script>
        
        <script type="text/javascript">
            //this is here to make plunkr work with AngularJS routing
            angular.element(document.getElementsByTagName('head')).append(angular.element('<base href="' + window.location.pathname + '" />'));
        </script>

        <title>Equipe 5</title>
    </head>
    <body ng-app="escoladeti" ng-controller="MainController" class="skin-black">
        <header class="header">
            <a href="#" class="logo">
                Equipe 5
            </a>
            <nav class="navbar navbar-static-top" role="navigation">
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">

                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-user"></i>
                                <span>Admin <i class="caret"></i></span>
                            </a>
                            <ul class="dropdown-menu">

                                <li class="user-header bg-light-blue">
                                    <img src="./resources/imagens/sem_imagem.png" class="img-circle" alt="Imagem usuário" />
                                    <p>
                                        Admin
                                        <small>Texto teste</small>
                                    </p>
                                </li>

                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="#" class="btn btn-default btn-flat">Perfil</a>
                                    </div>
                                    <div class="pull-right">
                                        <a href="#" class="btn btn-default btn-flat">Sair</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">                
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="./resources/imagens/sem_imagem.png" class="img-circle" alt="Imagem do usuário" />
                        </div>
                        <div class="pull-left info">
                            <p>Hello, Admin</p>

                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>

                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input type="text" name="q" class="form-control" placeholder="Busca..."/>
                            <span class="input-group-btn">
                                <button type='submit' name='seach' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>

                    <ul class="sidebar-menu">
                        <li class="active">
                            <a href="#">
                                <i class="fa fa-dashboard"></i> <span>Home</span>
                            </a>
                        </li>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-bar-chart-o"></i>
                                <span>Cadastros</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="#/cadastros/endereco/listar"><i class="fa fa-angle-double-right"></i> Endereço</a></li>
                            </ul>
                        </li>
                    </ul>
                </section>
            </aside>
        </div>

        <div class="container">
            <div ng-view></div>
        </div>

    </body>
</html>
