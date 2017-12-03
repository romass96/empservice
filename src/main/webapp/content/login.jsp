<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Авторизация</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/content/css/style.css">

</head>
<body>
<div class="container">
    <div class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info" >
            <div class="panel-heading">
                <div class="panel-title">Авторизация</div>
            </div>
            <div class="panel-body" >
                <s:actionerror></s:actionerror>
                <form class="form-horizontal" role="form" action="login" method="POST">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input id="login-username" type="text" class="form-control" name="login" value="" placeholder="Логин">
                    </div>
                    <div class="errorBox">
                        <s:fielderror fieldName="login"></s:fielderror>
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="login-password" type="password" class="form-control" name="password" placeholder="Пароль">
                    </div>
                    <div class="errorBox">
                        <s:fielderror fieldName="password"></s:fielderror>
                    </div>

                    <div class="form-group" style="margin-top: 10px">
                        <div class="col-sm-12 controls">
                            <button id="btn-login" class="btn btn-success" type="submit">Войти</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $('.errorBox:has(ul)').css("margin-top","2px");
</script>
</body>
</html>