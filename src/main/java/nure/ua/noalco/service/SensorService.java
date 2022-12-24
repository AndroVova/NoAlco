package nure.ua.noalco.service;


import nure.ua.noalco.entity.Sensor;

import java.util.List;

public interface SensorService {
    Sensor getSensor(String id);
    Sensor saveSensor(Sensor user);
    void deleteSensor(String id);
    List<Sensor> getSensors();
}
