package nure.ua.noalco.service;

import nure.ua.noalco.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployee(Long id);
    Employee saveEmployee(Employee student);
    public Employee addEmployeeToNewDepartment(Long employeeId, Long departmentId);
    void deleteEmployee(Long id);
    List<Employee> getEmployees();
}
