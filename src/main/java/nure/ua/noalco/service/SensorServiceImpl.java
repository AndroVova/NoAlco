package nure.ua.noalco.service;

import lombok.AllArgsConstructor;
import nure.ua.noalco.entity.Sensor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SensorServiceImpl implements SensorService{
    @Override
    public Sensor getSensor(String id) {
        return null;
    }

    @Override
    public Sensor saveSensor(Sensor user) {
        return null;
    }

    @Override
    public void deleteSensor(String id) {

    }

    @Override
    public List<Sensor> getSensors() {
        return null;
    }
}
