package nure.ua.noalco.service;

import nure.ua.noalco.entity.AlcoTesting;

import java.util.List;

public interface AlcoTestingService {
    AlcoTesting getAlcoTesting(Long id);
    AlcoTesting saveAlcoTesting(AlcoTesting test, String sensor_id, Long employeeId);
    void deleteAlcoTesting(Long id);
    List<AlcoTesting> getAlcoTestings();
    List<AlcoTesting> getAlcoTestingsByDepartment(Long id);
    List<AlcoTesting> getAlcoTestingsByEmployee(Long id);
}
