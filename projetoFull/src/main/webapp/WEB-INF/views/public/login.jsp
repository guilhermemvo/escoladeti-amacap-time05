<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./resources/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="./resources/fonts/css/font-awesome.css">
        <link rel="stylesheet" type="text/css" href="./resources/css/angular-growl.css">

        <link rel="stylesheet" type="text/css" href="./resources/componentes/jquery.magnific-popup/dist/magnific-popup.css" />
        <link rel="stylesheet" type="text/css" href="./resources/css/stylesistema.css"/>
        <title>AMACAP</title>
    </head>
    <body onload='document.loginForm.j_username.focus();' class="texture">
        <div id="cl-wrapper" class="sign-up-container">
            <div class="middle-sign-up">
                <div class="block-flat">
                    <div class="header">							
                        <h3 class="text-center"><img class="logo-img" src="./resources/imagens/logo2.png" alt="logo"/></h3>
                    </div>
                    <div>
                        <form style="margin-bottom: 0px !important;" class="form-horizontal" name='loginForm' modelAttribute="loginError" action="j_spring_security_check" method='POST'>
                            <div class="content">
                                <h5 class="title text-center"><strong>Acesse o Sistema de Gerenciamento</strong></h5>
                                <hr/>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                            <input type="text" placeholder="Login" class="form-control" name="j_username">
                                        </div>
                                        <div id="nick-error"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12 col-xs-12">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                            <input type="password" placeholder="Senha" class="form-control" name="j_password">
                                        </div>
                                        <div>${loginError}</div>
                                    </div>
                                </div>

                                <button class="btn btn-block btn-primary btn-rad btn-lg" type="submit" value="Entrar">Login</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="text-center out-links"><a href="#">Desenvolvido por: Projeto Integrador Escola de T.I. 2014</a></div>
            </div> 
















           <!-- <div class="container">
                <div class="row painel">
                    <div class="col-sm-4 col-sm-offset-4">
                        <form name='loginForm' modelAttribute="loginError" action="j_spring_security_check" method='POST'>
                            <fieldset>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <a href="#" class="thumbnail">
                                                    <img src="./resources/imagens/amacap.png" class="img-responsive logo">
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-sm-12">
                                        <label> Usuário</label>
                                        <input type="text" class="form-control" name="j_username" >
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-sm-12">
                                        <label> Senha</label>
                                        <input type="password" class="form-control" name="j_password" type="senha" value="">    
                                        ${loginError}
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-sm-12">
                                        <input class="btn btn-primary btn-block" type="submit" value="Entrar">
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div> 
                </div>
            </div>-->
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


</html>