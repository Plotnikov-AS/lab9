<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <title>Lab 5</title>
</head>
<body>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<div id="bodyDiv">
    <script>
        google.charts.load('current', {packages: ['corechart', 'bar']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            let data = google.visualization.arrayToDataTable([
                ['Auto', 'Потребление', {role: 'style'}],
                <#list allAutos as auto>
                    ['${auto.getMark()} ${auto.getGosNumber()}', ${auto.getTotalConsumed()}, 'blue'],
                </#list>
                ['Среднее по всем', '${middleConsume}', 'red']
            ]);

            let opts = {
                title: 'Общее пройденное расстояния',
                legend: {position: 'bottom'}
            }

            let chart = new google.visualization.ColumnChart(document.getElementById("chart_div"));
            chart.draw(data, opts);
        }
    </script>

    <div class="table" id="chart">
        <div class="client_row">
            <div class="cell">
                <div id="chart_div"></div>
            </div>
        </div>
    </div>
    <a href="javascript:history.back()">Назад</a>
</div>
</body>
</html>