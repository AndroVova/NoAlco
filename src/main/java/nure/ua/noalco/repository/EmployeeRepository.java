package nure.ua.noalco.repository;


import nure.ua.noalco.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findAllByDepartmentId(Long id);
}
