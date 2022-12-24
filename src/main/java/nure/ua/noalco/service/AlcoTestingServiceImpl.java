package nure.ua.noalco.service;

import lombok.AllArgsConstructor;
import nure.ua.noalco.entity.AlcoTesting;
import nure.ua.noalco.entity.Employee;
import nure.ua.noalco.entity.Sensor;
import nure.ua.noalco.exception.EntityNotFoundException;
import nure.ua.noalco.repository.AlcoTestingRepository;
import nure.ua.noalco.repository.EmployeeRepository;
import nure.ua.noalco.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlcoTestingServiceImpl implements AlcoTestingService{
    
    AlcoTestingRepository alcoTestingRepository;
    EmployeeRepository employeeRepository;

    SensorRepository sensorRepository;
    @Override
    public AlcoTesting getAlcoTesting(Long id) {
        Optional<AlcoTesting> alcoTesting = alcoTestingRepository.findById(id);
        return unwrapAlcoTesting(alcoTesting, id);
    }
    
    @Override
    public AlcoTesting saveAlcoTesting(AlcoTesting test, String sensorId, Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Employee unwrapedEmployee = EmployeeServiceImpl.unwrapEmployee(employee, employeeId);
        Optional<Sensor> sensor = sensorRepository.findById(sensorId);
        Sensor unwrapedSensor = SensorServiceImpl.unwrapSensor(sensor, sensorId);
        test.setEmployee(unwrapedEmployee);
        test.setSensor(unwrapedSensor);
        return alcoTestingRepository.save(test);
    }

    @Override
    public void deleteAlcoTesting(Long id) {
        alcoTestingRepository.deleteById(id);
    }

    @Override
    public List<AlcoTesting> getAlcoTestings() {
        return (List<AlcoTesting>)alcoTestingRepository.findAll() ;
    }

    @Override
    public List<AlcoTesting> getAlcoTestingsByDepartment(Long id) {
        List<Employee> employeeList = employeeRepository.findAllByDepartmentId(id);
        List<AlcoTesting> alcoTestings = new ArrayList<>();
        for (Employee emp : employeeList ) {
            alcoTestings.addAll(alcoTestingRepository.findAllByEmployeeId(emp.getId()));
        }
        return alcoTestings;
    }

    @Override
    public List<AlcoTesting> getAlcoTestingsByEmployee(Long id) {
        return alcoTestingRepository.findAllByEmployeeId(id);
    }

    static AlcoTesting unwrapAlcoTesting(Optional<AlcoTesting> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, AlcoTesting.class);
    }
}
