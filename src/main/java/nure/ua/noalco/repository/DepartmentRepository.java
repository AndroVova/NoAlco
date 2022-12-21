package nure.ua.noalco.repository;

import nure.ua.noalco.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
