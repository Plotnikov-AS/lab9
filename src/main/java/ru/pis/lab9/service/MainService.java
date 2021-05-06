package ru.pis.lab9.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pis.lab9.dao.AutomobileDao;
import ru.pis.lab9.dao.EmployeeDao;
import ru.pis.lab9.model.Automobile;
import ru.pis.lab9.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class MainService {
    private final EmployeeDao employeeDao;
    private final AutomobileDao automobileDao;

    public List<Employee> getAllDrivers() {
        return employeeDao.getAllEmployees();
    }

    public List<Automobile> getAllAutos() {
        return automobileDao.getAllAutomobiles();
    }

    public void saveRoadList(String driverId, String automobileId, String length, String fuelUsage) {
        Employee driver = employeeDao.getById(Long.parseLong(driverId));
        driver.setLength(Integer.parseInt(length));
        employeeDao.updateEmployee(driver);

        Automobile automobile = automobileDao.getAutomobileById(Long.parseLong(automobileId));
        automobile.setTotalConsumed(Integer.parseInt(fuelUsage));
        automobileDao.updateAutomobile(automobile);
    }

    public List<Integer> getLengthOfAll() {
        List<Employee> allEmployees = employeeDao.getAllEmployees();
        List<Integer> lengthOfAll = new ArrayList<>();
        allEmployees.stream().filter(Objects::nonNull).forEach(employee -> {
            if (nonNull(employee.getLength())) {
                lengthOfAll.add(employee.getLength());
            }
        });

        return lengthOfAll;
    }

    public Integer getMiddleLength() {
        List<Employee> allEmployees = employeeDao.getAllEmployees();
        List<Integer> lengthOfAll = new ArrayList<>();
        allEmployees.forEach(employee -> lengthOfAll.add(employee.getLength()));
        int middle = 0;
        for (Integer length : lengthOfAll) {
            if (isNull(length)) continue;
            middle += length;
        }

        return middle / lengthOfAll.size();
    }

    public List<Integer> getConsumedOfAll() {
        List<Automobile> allAutomobiles = automobileDao.getAllAutomobiles();
        List<Integer> consumeOfAll = new ArrayList<>();
        allAutomobiles.stream().filter(Objects::nonNull).forEach(automobile -> {
            if (nonNull(automobile.getTotalConsumed())) {
                consumeOfAll.add(automobile.getTotalConsumed());
            }
        });

        return consumeOfAll;
    }

    public Integer getMiddleConsume() {
        List<Automobile> allAutomobiles = automobileDao.getAllAutomobiles();
        List<Integer> consumeOfAll = new ArrayList<>();
        allAutomobiles.forEach(automobile -> consumeOfAll.add(automobile.getTotalConsumed()));
        int middle = 0;
        for (Integer consume : consumeOfAll) {
            if (isNull(consume)) continue;
            middle += consume;
        }

        return middle / consumeOfAll.size();
    }
}
