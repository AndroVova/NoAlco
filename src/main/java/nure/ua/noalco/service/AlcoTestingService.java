package nure.ua.noalco.service;

import jakarta.persistence.EntityManager;
import nure.ua.noalco.entity.AlcoTesting;

import javax.mail.MessagingException;
import java.util.List;

public interface AlcoTestingService {
    AlcoTesting getAlcoTesting(Long id);
    AlcoTesting saveAlcoTesting(AlcoTesting test, String sensor_id, Long employeeId) throws MessagingException;
    void deleteAlcoTesting(Long id);
    List<AlcoTesting> getAlcoTestings();
    List<AlcoTesting> getAlcoTestingsByDepartment(Long id);
    List<AlcoTesting> getAlcoTestingsByEmployee(Long id);

    Long countAlcoTestingsWithValueGreaterThanMaxValue(EntityManager entityManager/*, String id*/);
    List<AlcoTesting> findAlcoTestingsWithValueGreaterThanMaxValue(EntityManager entityManager);
}
