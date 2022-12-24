package nure.ua.noalco.service;

import jakarta.persistence.EntityManager;
import nure.ua.noalco.entity.AlcoTesting;
import org.hibernate.Session;

import java.util.List;

public interface AlcoTestingService {
    AlcoTesting getAlcoTesting(Long id);
    AlcoTesting saveAlcoTesting(AlcoTesting test, String sensor_id, Long employeeId);
    void deleteAlcoTesting(Long id);
    List<AlcoTesting> getAlcoTestings();
    List<AlcoTesting> getAlcoTestingsByDepartment(Long id);
    List<AlcoTesting> getAlcoTestingsByEmployee(Long id);

    Long countAlcoTestingsWithValueGreaterThanMaxValue(EntityManager entityManager/*, String id*/);
    List<AlcoTesting> findAlcoTestingsWithValueGreaterThanMaxValue(EntityManager entityManager);
}
