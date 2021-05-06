<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lab 5</title>
</head>
<body>
<script>
    const automobiles = [
        <#list drivers as driver>
            <#list driver.getAutomobiles() as automobile>{
            driverId: '${driver.getId()}',
            automobileId: '${automobile.getId()}',
            automobileMark: '${automobile.getMark()}',
            automobileGosNumber: '${automobile.getGosNumber()}',
            fuelOnKm: '${automobile.getConsumption()}',
            selected: false
        }<#if automobile?has_next>, </#if></#list><#if driver?has_next>, </#if>
        </#list>
    ];

    function getAutomobiles(selectedDriver) {
        let selectBox = document.getElementById("automobile");
        let optionsList = selectBox.options;
        for (let i = optionsList.length; i >= 0; i--) {
            optionsList[i] = null;
        }
        let options = [];
        automobiles.forEach(automobile => {
            if (automobile.driverId === selectedDriver) {
                options.push(automobile);
            }
        });
        options.forEach(function (option, index) {
            if (index === options.length - 1) {
                option.selected = true;
                document.getElementById("fuelOnKm").value = option.fuelOnKm;
            }
            optionsList.add(new Option(option.automobileMark, option.automobileId, option.selected));
        });
        calc();
    }

    function setFuelOnKm(automobileId) {
        automobiles.forEach(automobile => {
            if (automobile.automobileId === automobileId) {
                document.getElementById("fuelOnKm").value = automobile.fuelOnKm;
            }
        });
        calc();
    }

    function calc() {
        let startKm = document.getElementById("startKm");
        let endKm = document.getElementById("endKm");
        let length = document.getElementById("length");
        let fuelUsage = document.getElementById("fuelUsage");
        let fuelOnKm = Number.parseInt(document.getElementById("fuelOnKm").value);
        length.value = endKm.value - startKm.value;
        fuelUsage.value = Number.parseInt(length.value) * fuelOnKm;
    }

    function getDriver() {
        document.getElementById("driverId").value = document.getElementById("driver").options[document.getElementById("driver").selectedIndex].value;
    }

    function getAuto() {
        document.getElementById("autoId").value = document.getElementById("automobile").options[document.getElementById("automobile").selectedIndex].value;
    }

    function getSubmitInfo() {
        document.getElementById("driverIdSubmit").value = document.getElementById("driver").options[document.getElementById("driver").selectedIndex].value;
        document.getElementById("autoIdSubmit").value = document.getElementById("automobile").options[document.getElementById("automobile").selectedIndex].value;
        document.getElementById("lengthSubmit").value = document.getElementById("length").value;
        document.getElementById("fuelUsageSubmit").value = document.getElementById("fuelUsage").value;
    }
</script>
<h1>Путевой лист</h1>
<div id="bodyDiv">
    <table>
        <tr>
            <td>
                Водитель:
            </td>
            <td>
                <select id="driver" name="driver" onchange="getAutomobiles(this.options[this.selectedIndex].value)">
                    <option selected value="0">Водитель</option>
                    <#foreach driver in drivers>
                        <option value="${driver.getId()}">${driver.getLastName()} ${driver.getFirstName()} ${driver.getMiddleName()}</option>
                    </#foreach></select>
            </td>
            <td>
                <form method="get" action="/showDriverStat">
                    <input type="hidden" id="driverId" name="driverId">
                    <input type="submit" value="Показать статистику" onsubmit="getDriver()">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                Автомобиль:
            </td>
            <td>
                <input type="hidden" id="fuelOnKm" value="0">
                <select name="automobile" id="automobile"
                        onchange="setFuelOnKm(this.options[this.selectedIndex].value)">
                    <option selected value="0">Автомобиль</option>
                </select>
            </td>
            <td>
                <form method="get" action="/showAutoStat">
                    <input type="hidden" id="autoId" name="autoId">
                    <input type="submit" value="Показать статистику" onsubmit="getAuto()">
                </form>
            </td>
        </tr>
        <tr>
            <td>
                Время выезда:
            </td>
            <td>
                <input id="startTime" name="startTime" type="text" placeholder="Время выезда" value="00:00">
            </td>
        </tr>
        <tr>
            <td>
                Время заезда:
            </td>
            <td>
                <input id="endTime" name="endTime" type="text" placeholder="Время заезда" value="00:00">
            </td>
        </tr>
        <tr>
            <td>
                Начальный километраж:
            </td>
            <td>
                <input id="startKm" name="startKm" type="text" value="0" onchange="calc()"> км
            </td>
        </tr>
        <tr>
            <td>
                Конечный километраж:
            </td>
            <td>
                <input id="endKm" name="endKm" type="text" value="0" onchange="calc()"> км
            </td>
        </tr>
        <tr>
            <td>
                Пробег:
            </td>
            <td>
                <input id="length" name="length" type="text" value="0" onchange="calc()"> км
            </td>
        </tr>
        <tr>
            <td>
                Расход топлива:
            </td>
            <td>
                <input id="fuelUsage" name="fuelUsage" type="text" value="0" onchange="calc()"> л
            </td>
        </tr>
    </table>
    <form method="post" action="/submit">
        <input type="hidden" id="driverIdSubmit" name="driverId">
        <input type="hidden" id="autoIdSubmit" name="autoId">
        <input type="hidden" id="lengthSubmit" name="length">
        <input type="hidden" id="fuelUsageSubmit" name="fuelUsage">
        <input type="submit" value="Записать" onclick="getSubmitInfo()">
    </form>
</div>
</body>
</html>