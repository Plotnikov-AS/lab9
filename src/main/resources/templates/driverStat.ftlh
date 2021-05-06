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
                ['Driver', 'Расстояние', {role: 'style'}],
                <#list allDrivers as driver>
                    ['${driver.getLastName()} ${driver.getFirstName()?substring(0, 1)}. ${driver.getMiddleName()?substring(0, 1)}.',
                    ${driver.getLength()}, 'blue'],
                </#list>
                ['Среднее по всем', '${middleLength}', 'red']
            ]);

            let opts = {
                title: 'Общее потребление топлива',
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