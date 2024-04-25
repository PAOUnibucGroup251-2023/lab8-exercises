package ex1.exceptions;

public class NoEmployeesPresentException extends Exception {
    public NoEmployeesPresentException() {
        super("No employees.txt present");
    }
}
