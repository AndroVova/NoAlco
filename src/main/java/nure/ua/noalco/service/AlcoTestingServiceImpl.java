package nure.ua.noalco.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import nure.ua.noalco.SQLQuerry;
import nure.ua.noalco.emailUtil.EmailUtil;
import nure.ua.noalco.entity.AlcoTesting;
import nure.ua.noalco.entity.Employee;
import nure.ua.noalco.entity.Sensor;
import nure.ua.noalco.exception.AlcoTestingFailureException;
import nure.ua.noalco.exception.EntityNotFoundException;
import nure.ua.noalco.repository.AlcoTestingRepository;
import nure.ua.noalco.repository.EmployeeRepository;
import nure.ua.noalco.repository.SensorRepository;
import org.springframework.stereotype.Service;

import javax.mail.*;
import java.util.*;

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
    public AlcoTesting saveAlcoTesting(AlcoTesting test, String sensorId, Long employeeId) throws MessagingException {
        Optional<Sensor> sensor = sensorRepository.findById(sensorId);
        Sensor unwrappedSensor = SensorServiceImpl.unwrapSensor(sensor, sensorId);
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Employee unwrappedEmployee = EmployeeServiceImpl.unwrapEmployee(employee, employeeId);
        test.setEmployee(unwrappedEmployee);
        test.setSensor(unwrappedSensor);
        try {
            if (test.getValue() > unwrappedSensor.getMaxValue()) {
                throw new AlcoTestingFailureException(employeeId, unwrappedEmployee);
            }
        } catch (AlcoTestingFailureException e) {
            EmailUtil.sendMessage(test);
        }
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

    @Override
    public Long countAlcoTestingsWithValueGreaterThanMaxValue(EntityManager entityManager) {
        String hql = SQLQuerry.countFalseAlcoTestings;
        TypedQuery<Long> query = entityManager.createQuery(hql, Long.class);
        return query.getSingleResult();
    }

    @Override
    public List<AlcoTesting> findAlcoTestingsWithValueGreaterThanMaxValue(EntityManager entityManager) {
        String hql = SQLQuerry.getFalseAlcoTestings;
        TypedQuery<AlcoTesting> query = entityManager.createQuery(hql, AlcoTesting.class);
        return query.getResultList();
    }

    static AlcoTesting unwrapAlcoTesting(Optional<AlcoTesting> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, AlcoTesting.class);
    }
}
