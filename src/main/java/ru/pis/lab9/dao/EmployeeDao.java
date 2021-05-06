package ru.pis.lab9.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.pis.lab9.model.Employee;
import ru.pis.lab9.repo.EmployeeRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static java.util.Objects.isNull;

@Repository
@Transactional
@RequiredArgsConstructor
public class EmployeeDao {
    private final EmployeeRepo employeeRepo;
    @PersistenceContext
    private final EntityManager entityManager;

    public List<Employee> getAllEmployees() {
        return employeeRepo.getAllEmployees();
    }

    public Employee getById(Long id) {
        if (isNull(id)) {
            throw new IllegalArgumentException("Empty id");
        }

        return employeeRepo.getById(id);
    }

    public void updateEmployee(Employee employee) {
        if (isNull(employee)) {
            throw new IllegalArgumentException("Empty employee");
        }

        entityManager.persist(employee);
    }
}
