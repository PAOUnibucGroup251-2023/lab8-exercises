package ex1.exceptions;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException() {
        super("Employee not found");
    }
}
