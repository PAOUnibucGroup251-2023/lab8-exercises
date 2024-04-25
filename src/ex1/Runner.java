package ex1;

import ex1.exceptions.EmployeeNotFoundException;
import ex1.exceptions.NoEmployeesPresentException;
import ex1.model.*;
import ex1.repository.DefaultEmployeeRepository;
import ex1.repository.EmployeeRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.random.RandomGenerator;

import static ex1.util.EmployeeFactory.createEmployee;

public class Runner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        EmployeeRepository employeeRepository = null;

        try {
            employeeRepository = new DefaultEmployeeRepository();
        } catch (IOException e) {
            System.out.printf("Failed to load employee data: %s%n", e.getMessage());
            System.exit(1);
        }

        handleMenu(sc, employeeRepository);
    }

    private static void handleMenu(Scanner sc, EmployeeRepository employeeRepository) {
        while (true) {
            System.out.println("""
                    Welcome to the employee directory!
                    Please select the operation you want to perform:
                    
                    1. Show employees
                    2. Add new employee
                    3. Update an employee
                    4. Delete an employee
                    5. Show employee details
                    6. Exit
                    """);

            int operation = Integer.parseInt(sc.nextLine());

            try {
                handleMenuOperation(sc, employeeRepository, operation);
            } catch (Exception e) {
                System.out.printf("Operation error: %s%n", e.getMessage());
            }
        }
    }

    private static void handleMenuOperation(Scanner sc, EmployeeRepository employeeRepository, int operation) throws NoEmployeesPresentException, EmployeeNotFoundException {
        switch (operation) {
            case 1 -> displayEmployees(employeeRepository);
            case 2 -> employeeRepository.addEmployee(readEmployee(sc, employeeRepository.getAll().size()));
            case 3 -> {
                System.out.println("Enter employee ID: ");
                Long employeeID = Long.parseLong(sc.nextLine());

                employeeRepository.updateEmployee(readEmployee(sc, employeeRepository.getAll().size(), employeeID));
            }
            case 4 -> {
                System.out.println("Enter employee id: ");
                Long employeeId = Long.parseLong(sc.nextLine());
                employeeRepository.deleteEmployee(employeeId);
            }
            case 5 -> searchEmployee(sc, employeeRepository);
            case 6 -> System.exit(0);
            default -> System.out.println("Invalid operation");
        }
    }

    private static void searchEmployee(Scanner sc, EmployeeRepository employeeRepository) throws NoEmployeesPresentException, EmployeeNotFoundException {
        System.out.println("Enter the name of the employee to search: ");
        System.out.println("First Name: ");
        String firstName = sc.nextLine();
        System.out.println("Last Name: ");
        String lastName = sc.nextLine();

        Employee employee = employeeRepository.getByName(firstName, lastName);
        System.out.println("Employee details: " + employee);
    }

    private static Employee readEmployee(Scanner sc, int numberOfEmployees) {
        System.out.println("Enter employee first name: ");
        String firstName = sc.nextLine();
        System.out.println("Enter employee last name: ");
        String lastName = sc.nextLine();
        Random random = Random.from(RandomGenerator.getDefault());
        long employeeID = random.nextInt(numberOfEmployees * 1000);
        System.out.println("Enter role: ");
        Role role = null;

        try {
            role = Role.valueOf(sc.nextLine().replace(' ', '_').toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role");
            System.exit(1);
        }

        Employee employee = createEmployee(role, firstName, lastName, employeeID);
        return employee;
    }

    private static Employee readEmployee(Scanner sc, int numberOfEmployees, long employeeID) {
        System.out.println("Enter employee first name: ");
        String firstName = sc.nextLine();
        System.out.println("Enter employee last name: ");
        String lastName = sc.nextLine();
        System.out.println("Enter role: ");
        Role role = null;

        try {
            role = Role.valueOf(sc.nextLine().replace(' ', '_').toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role");
            System.exit(1);
        }

        return createEmployee(role, firstName, lastName, employeeID);
    }

    private static void displayEmployees(EmployeeRepository employeeRepository) {
        Set<Employee> employees = employeeRepository.getAll();

        for (Employee employee : employees) {
            System.out.printf("%s%n", employee.tabular());
        }
    }
}
