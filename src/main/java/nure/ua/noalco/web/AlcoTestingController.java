package nure.ua.noalco.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nure.ua.noalco.entity.AlcoTesting;
import nure.ua.noalco.service.AlcoTestingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/alcoTesting")
public class AlcoTestingController {

    AlcoTestingService alcoTestingService;

    @GetMapping("/{id}")
    public ResponseEntity<AlcoTesting> getAlcoTesting(@PathVariable Long id) {
        return new ResponseEntity<>(alcoTestingService.getAlcoTesting(id), HttpStatus.OK);
    }

    @PostMapping("/sensor/{sensor_id}/employee/{employee_id}")
    public ResponseEntity<AlcoTesting> saveAlcoTesting(@Valid @RequestBody AlcoTesting alcoTesting,@PathVariable String sensor_id ,@PathVariable Long employee_id) {
        alcoTestingService.saveAlcoTesting(alcoTesting, sensor_id, employee_id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AlcoTesting> deleteAlcoTesting(@PathVariable Long id) {
        alcoTestingService.deleteAlcoTesting(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AlcoTesting>> getAlcoTestings() {
        return new ResponseEntity<>(alcoTestingService.getAlcoTestings(), HttpStatus.OK);
    }

    @GetMapping("/all/department/{id}")
    public ResponseEntity<List<AlcoTesting>> getAlcoTestingsByDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(alcoTestingService.getAlcoTestingsByDepartment(id), HttpStatus.OK);
    }

    @GetMapping("/all/employee/{id}")
    public ResponseEntity<List<AlcoTesting>> getAlcoTestingsByEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(alcoTestingService.getAlcoTestingsByEmployee(id), HttpStatus.OK);
    }
}
