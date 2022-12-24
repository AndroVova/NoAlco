package nure.ua.noalco;

public class SQLQuerry {
    static public final String countFalseAlcoTestings = "SELECT COUNT(*) FROM AlcoTesting at WHERE at.value > at.sensor.maxValue";
    static public final String getFalseAlcoTestings = "FROM AlcoTesting at WHERE at.value > at.sensor.maxValue";
}
