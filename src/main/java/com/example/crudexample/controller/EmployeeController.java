package com.example.crudexample.controller;

import com.example.crudexample.model.Employee;
import com.example.crudexample.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Получение по ID
     *
     * @param id
     * @return
     */
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employee != null
                ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    /**
     * Получение списка сотрудников
     *
     * @param employee
     */
    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployee(@RequestBody Employee employee) {
        List<Employee> emp = employeeService.getAllEmployee(employee);
        return emp != null
                ? new ResponseEntity<>(emp, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //return ResponseEntity.ok(employeeService.getAllEmployee(employee));
    }

    /**
     * Поиск по имени
     *
     * @param name
     * @return
     */
    @GetMapping("/employee/name")
    public List<Employee> getNameEmployee(@RequestParam String name) {
        return (List<Employee>) employeeService.getEmployeeByName(name);
    }

    /**
     * Добавление
     *
     * @param employee
     * @return
     */
    @PostMapping("/employee")
    public ResponseEntity<?> creatEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
        //return ResponseEntity.ok(employeeService.createEmployee(employee, HttpStatus.CREATED));
    }

    /**
     * Удаление по ID
     *
     * @param id
     * @return
     */
    @DeleteMapping("/employee")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        employeeService.getEmployeeById(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping("/employee")
    public ResponseEntity<?> update(@RequestParam Long id,
                                    @RequestParam String name,
                                    @RequestParam String company,
                                    @RequestParam String phoneNumber) {
        Employee employee = employeeService.getEmployeeById(id);
        employee.setName(name);
        employee.setCompany(company);
        employee.setPhoneNumber(phoneNumber);

        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
