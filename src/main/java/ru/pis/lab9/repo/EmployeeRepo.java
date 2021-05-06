package ru.pis.lab9.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.pis.lab9.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    @Query("SELECT emp FROM Employee emp")
    List<Employee> getAllEmployees();

    Employee getById(Long id);
}
