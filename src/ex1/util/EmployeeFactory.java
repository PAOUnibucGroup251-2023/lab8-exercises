package ex1.util;

import ex1.model.*;

public class EmployeeFactory {
    public static Employee createEmployee(Role role, String firstName, String lastName, long employeeID) {
        return switch (role) {
            case SECRETARY -> new Secretary(firstName, lastName, employeeID, role);
            case CHIEF_SECRETARY -> new ChiefSecretary(firstName, lastName, employeeID, role);
            case GENERAL_MANAGER -> new GeneralManager(firstName, lastName, employeeID, role);
            case HEAD_OF_DEVELOPMENT -> new ProgrammingManagerImpl(firstName, lastName, employeeID, role);
        };
    }
}
