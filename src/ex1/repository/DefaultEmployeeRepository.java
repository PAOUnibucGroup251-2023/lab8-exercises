package ex1.repository;

import ex1.exceptions.EmployeeNotFoundException;
import ex1.exceptions.NoEmployeesPresentException;
import ex1.model.*;
import ex1.util.EmployeeFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DefaultEmployeeRepository implements EmployeeRepository {

    private Set<Employee> employees;

    public DefaultEmployeeRepository() throws IOException {
        readEmployees("employees.txt");
    }

    @Override
    public void addEmployee(Employee employee) {
        if (employees == null){
            employees = new HashSet<>();
        }
        employees.add(employee);
    }

    @Override
    public void updateEmployee(Employee employee) throws NoEmployeesPresentException, EmployeeNotFoundException {
        verifyEmptyEmployeeList();

        for (Employee emp : employees) {
            if (emp.idNo().equals(employee.idNo())) {
                employees.remove(emp);
                employees.add(employee);
            } else {
                throw new EmployeeNotFoundException();
            }
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        for (Employee emp : employees) {
            if (emp.idNo().equals(id)) {
                employees.remove(emp);
            }
        }
    }

    @Override
    public Employee getEmployeeById(Long id) throws NoEmployeesPresentException, EmployeeNotFoundException {
        verifyEmptyEmployeeList();

        for (Employee emp : employees) {
            if (emp.idNo().equals(id)) {
                return emp;
            }
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee getByName(String firstName, String lastName) throws NoEmployeesPresentException, EmployeeNotFoundException {
        verifyEmptyEmployeeList();
        for (Employee emp : employees) {
            if (emp.firstName().equals(firstName) && emp.lastName().equals(lastName)) {
                return emp;
            }
        }

        throw new EmployeeNotFoundException();
    }

    @Override
    public Set<Employee> getAll() {
        Set<Employee> returnedEmployees = new HashSet<>();
        returnedEmployees.addAll(employees);
        return returnedEmployees;
    }

    private void verifyEmptyEmployeeList() throws NoEmployeesPresentException {
        if (employees == null || employees.isEmpty()){
            throw new NoEmployeesPresentException();
        }
    }

    private void readEmployees(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        do {
            line = bufferedReader.readLine();
            String[] fields = "end".equals(line) ? null : line.split(",");
            Employee employee =
                    fields != null ?
                            EmployeeFactory.createEmployee(Role.valueOf(fields[fields.length - 1].replace(' ', '_').toUpperCase()),
                                    fields[0],
                                    fields[1], Long.parseLong(fields[2])):null;
            if(employee != null) {
               addEmployee(employee);
            }
        } while (!line.equals("end"));
        bufferedReader.close();
        fileReader.close();
    }
}
