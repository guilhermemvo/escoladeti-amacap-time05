<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="bg-black">
    <head>
        <meta charset="UTF-8">
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

        <script type="text/javascript" src="./resources/js/jquery.min.js"></script>
        <script type="text/javascript" src="./resources/js/bootstrap.min.js"></script>    

        <link rel="stylesheet" href="./resources/css/bootstrap.min.css" >
        <link href="./resources/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="./resources/css/AdminLTE.css" rel="stylesheet" type="text/css" />
        <link href="./resources/css/ionicons.min.css" rel="stylesheet" type="text/css" />

        <title>Equipe 5</title>
    </head>
    <body class="bg-black">
        <div class="form-box" id="login-box">
            <div class="header">Login Equipe 5</div>
            <form name='f' action="j_spring_security_check" method='POST'>
                <div class="body bg-gray">
                    <div class="form-group">
                        <input type="text" name="j_username" class="form-control" placeholder="Usuário"/>
                    </div>

                    <div class="form-group">
                        <input type="password" name="j_password" class="form-control" placeholder="Senha"/>
                    </div>     

                </div>
                <div class="footer">     
                    <button type="submit" class="btn bg-olive btn-block">Entrar</button>  
                    <c:if test="${loginError}">
                        <div class="alert alert-danger alert-dismissable">
                            <i class="fa fa-ban"></i>
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <b>Atenção!</b> Falha na autenticação.
                        </div>
                    </c:if>
                </div>
            </form>
        </div>
    </body>
</html>
