package nure.ua.noalco.service;

import nure.ua.noalco.entity.Department;
import nure.ua.noalco.entity.Employee;

import java.util.List;
import java.util.Set;

public interface DepartmentService {
    Department getDepartment(Long id);
    Department saveDepartment(Department course);
    void deleteDepartment(Long id);
    List<Department> getDepartments();
    Set<Employee> getEmployeesInDepartment(Long id);
}
