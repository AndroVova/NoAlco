package nure.ua.noalco;

import lombok.AllArgsConstructor;
import nure.ua.noalco.entity.Employee;
import nure.ua.noalco.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@AllArgsConstructor
public class NoAlcoholApplication implements CommandLineRunner {
    EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(NoAlcoholApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
