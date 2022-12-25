package nure.ua.noalco.repository;

import nure.ua.noalco.entity.AlcoTesting;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlcoTestingRepository extends CrudRepository<AlcoTesting, Long> {

    List<AlcoTesting> findAllByEmployeeId(Long id);
}
