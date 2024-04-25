package ex1.repository;

import ex1.exceptions.EmployeeNotFoundException;
import ex1.exceptions.NoEmployeesPresentException;
import ex1.model.Employee;

import java.util.Set;

public interface EmployeeRepository {
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee) throws NoEmployeesPresentException, EmployeeNotFoundException;
    void deleteEmployee(Long id);
    Employee getEmployeeById(Long id) throws NoEmployeesPresentException, EmployeeNotFoundException;
    Employee getByName(String firstName, String lastName) throws NoEmployeesPresentException, EmployeeNotFoundException;

    Set<Employee> getAll();
}
