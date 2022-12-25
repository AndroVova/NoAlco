package nure.ua.noalco.exception;

import nure.ua.noalco.entity.Employee;

public class AlcoTestingFailureException extends RuntimeException{
    public AlcoTestingFailureException(Long id, Employee entity) {
        System.out.println("The employee " + entity.getName() + " with id '" + id + "' exceeded the limit of alcohol intoxication");
    }
}
