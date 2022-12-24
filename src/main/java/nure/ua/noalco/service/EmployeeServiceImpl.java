package nure.ua.noalco.service;

import lombok.AllArgsConstructor;
import nure.ua.noalco.entity.Department;
import nure.ua.noalco.entity.Employee;
import nure.ua.noalco.exception.EntityNotFoundException;
import nure.ua.noalco.repository.DepartmentRepository;
import nure.ua.noalco.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Override
    public Employee getEmployee(Long id) {
        Optional<Employee> student = employeeRepository.findById(id);
        return unwrapEmployee(student, id);
    }

    @Override
    public Employee saveEmployee(Employee student) {
        return employeeRepository.save(student);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployees() {
        return (List<Employee>)employeeRepository.findAll();
    }

    static Employee unwrapEmployee(Optional<Employee> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Employee.class);
    }

    @Override
    public Employee addEmployeeToNewDepartment(Long employeeId, Long departmentId) {
        Employee employee = getEmployee(employeeId);
        Optional<Department> department = departmentRepository.findById(departmentId);
        Department unwrappedDepartment = DepartmentServiceImpl.unwrapDepartment(department, departmentId);
        employee.setDepartment(unwrappedDepartment);
        return employeeRepository.save(employee);
    }
}
