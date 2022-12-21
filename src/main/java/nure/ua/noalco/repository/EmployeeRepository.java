package nure.ua.noalco.repository;

import nure.ua.noalco.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
