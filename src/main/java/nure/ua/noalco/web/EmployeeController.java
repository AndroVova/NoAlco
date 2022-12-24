package nure.ua.noalco.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nure.ua.noalco.entity.Employee;
import nure.ua.noalco.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getEmployees(){
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @PutMapping("/{employeeId}/department/{departmentId}")
    public ResponseEntity<Employee> updateDepartmentToEmployee(@PathVariable Long departmentId, @PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeService.addEmployeeToNewDepartment(employeeId, departmentId), HttpStatus.OK);
    }
}
