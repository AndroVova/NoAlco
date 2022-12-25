package nure.ua.noalco.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nure.ua.noalco.entity.Department;
import nure.ua.noalco.entity.Employee;
import nure.ua.noalco.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/department")
public class DepartmentController {

    DepartmentService departmentService;

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.getDepartment(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department department) {
        return new ResponseEntity<>(departmentService.saveDepartment(department), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getDepartments() {
        return new ResponseEntity<>(departmentService.getDepartments(), HttpStatus.OK);
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<Employee>> getEnrolledEmployees(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.getEmployeesInDepartment(id), HttpStatus.OK);
    }

}
