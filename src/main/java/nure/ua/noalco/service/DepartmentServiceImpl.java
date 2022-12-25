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

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private DepartmentRepository departmentRepository;

    private EmployeeRepository employeeRepository;

    @Override
    public Department getDepartment(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return unwrapDepartment(department, id);
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }


    @Override
    public List<Department> getDepartments() {
        return (List<Department>)departmentRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeesInDepartment(Long id) {
        return employeeRepository.findAllByDepartmentId(id);
    }

    static Department unwrapDepartment(Optional<Department> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Department.class);
    }
}
