<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="ru">
<head>
    <title>Статистика</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Сервис заказов</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/ordersPage">Добавление заказа <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/statisticsPage">Статистика</a>
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
<div class="container">
    <div class="row justify-content-md-center">
        <h2>Общее количество заказов для всех сотрудников</h2>
    </div>
    <div class="row justify-content-md-center">
        <div class="col-md-2">
            <label for="begin_date">Дата с</label>
            <input class="form-control datepicker" type="text" id="begin_date"
                   value="<s:date name="minDate" format="dd.MM.yyyy"/>">
        </div>
        <div class="col-md-2 offset-md-4">
            <label for="end_date">Дата по</label>
            <input class="form-control datepicker" type="text" id="end_date"
                   value="<s:date name="maxDate" format="dd.MM.yyyy"/>">
        </div>
    </div>

    <div class="row justify-content-md-center" style="margin-top: 20px">
        <div class="ct-chart ct-golden-section">
        </div>
    </div>

    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>Сотрудник</th>
            <s:subset source="sumPrices" count="1">
                <s:iterator>
                    <s:iterator value="value">
                        <th>
                            <s:property value="key"></s:property>
                        </th>
                    </s:iterator>
                </s:iterator>
            </s:subset>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="sumPrices">
            <tr>
                <td>
                    <s:property value="key"></s:property>
                </td>
                <s:iterator value="value">
                    <td>
                        <s:property value="value"></s:property>
                    </td>
                </s:iterator>
            </tr>
        </s:iterator>
        </tbody>
    </table>

    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/locales/bootstrap-datepicker.ru.min.js"></script>
<script src="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        buildChart();
    });
    $('.datepicker').datepicker({
        language: "ru",
        autoclose: true,
        orientation: "bottom right"
    });
    $('.datepicker').datepicker().on('changeDate',function (e) {
        buildChart();
    });
    function buildChart() {
        $.getJSON('sendOrdersCount', {
            minDate: $('#begin_date').val(),
            maxDate: $('#end_date').val()
        }, function(jsonResponse){
            console.log(jsonResponse.ordersCountMap);
            var xdata = Object.keys(jsonResponse.ordersCountMap);
            var ydata = Object.values(jsonResponse.ordersCountMap);
            var data = {
                labels: xdata,
                series: [ydata]
            };
            var options = {
                height: 500
            };
            console.log('dsfsdfs');
            new Chartist.Line('.ct-chart', data, options);
        });
    }
</script>
</body>
</html>
