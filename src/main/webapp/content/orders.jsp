<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html lang="ru">
<head>
    <title>Добавление заказов</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css">
    <link rel="stylesheet" href="/content/css/style.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Сервис заказов</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="ordersPage">Добавление заказа <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="statisticsPage">Статистика</a>
            </li>
        </ul>
        <ul class="navbar-nav navbar-right">
            <li class="nav-item mr-0">
                <a class="nav-link" href="#"><s:property value="#session.user.firstName"></s:property></a>
            </li>
            <li class="nav-item mr-0">
                <a href="logout" class="btn btn-outline-success my-2 my-sm-0">Выход</a>
            </li>
        </ul>

    </div>
</nav>
<div class="col-md-6 offset-md-3">
    <form action="addOrder" class="center-block" method="POST">
        <h3>Добавление нового заказа</h3>
        <div class="form-group">
            <label for="employee">Сотрудник</label>
            <select class="form-control" id="employee" name="order.employee.login">
                <option selected></option>
                <s:iterator value="employees">
                    <option value="<s:property value="login"></s:property>">
                        <s:property value="firstName"></s:property>
                        <s:property value="lastName"></s:property>
                    </option>
                </s:iterator>
            </select>
        </div>
        <div class="errorBox">
            <s:fielderror fieldName="order.employee.login"></s:fielderror>
        </div>
        <div class="form-group">
            <label for="region">Регион</label>
            <select class="form-control" id="region" name="order.region.id">
                <option selected></option>
                <s:iterator value="regions">
                    <option value="<s:property value="id"></s:property>">
                        <s:property value="name"></s:property>
                    </option>
                </s:iterator>
            </select>
        </div>
        <div class="errorBox">
            <s:fielderror fieldName="order.region.id"></s:fielderror>
        </div>
        <div class="form-group">
            <label for="price">Сумма заказа</label>
            <input class="form-control" type="number" id="price" name="order.price">
        </div>
        <div class="errorBox">
            <s:fielderror fieldName="order.price"></s:fielderror>
        </div>
        <div class="form-group">
            <label for="order_date">Дата заказа</label>
            <input class="form-control datepicker" type="text" id="order_date" name="order.orderDate">
        </div>
        <div class="errorBox">
            <s:fielderror fieldName="order.order_date"></s:fielderror>
        </div>
        <button type="submit" class="btn btn-primary">Добавить</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/locales/bootstrap-datepicker.ru.min.js"></script>
<script type="text/javascript">
    $('.datepicker').datepicker({
        language: "ru",
        autoclose: true,
        orientation: "bottom right"
    });
    $('.datepicker').datepicker('setDate', new Date());
    $('.errorBox:has(ul)').css("margin-top","2px");
</script>
</body>
</html>