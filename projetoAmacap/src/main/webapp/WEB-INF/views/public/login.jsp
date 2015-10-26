<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="shortcut icon" href="./resources/imagens/favicon.ico"> 
        <link rel="stylesheet" href="./resources/css/bootstrap.min.css" >
        <title>Escola de TI - 2014 - Time 5</title>

        <style type="text/css">
            body{
                background-color: #DCDCDC;
            }
            .painel{
                margin-top: 50px;
            }
            img{
                width: 150px;
            }
        </style>

    </head>
    <body onload='document.loginForm.j_username.focus();'>
        <div class="container">
            <div class="row painel">
                <div class="col-sm-4 col-sm-offset-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Acesso ao Painel de Controle</h3>
                        </div>
                        <div class="panel-body">

                            <form name='loginForm' modelAttribute="loginError" action="j_spring_security_check" method='POST'>
                                <fieldset>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <a href="#" class="thumbnail">
                                                        <img src="./resources/imagens/amacap.jpg">
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
                        </div> <!-- panel-body -->
                    </div> <!-- panel-default -->
                </div> <!-- col-md -->
            </div><!-- row-->
        </div> <!-- container -->
    </body>
    <script type="text/javascript" src="./resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="./resources/js/bootstrap.min.js"></script>    
</html>