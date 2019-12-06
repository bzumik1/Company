package com.siemens.company.controller;

import com.siemens.company.model.Employee;
import com.siemens.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(Pageable pageable){
        return new ResponseEntity<>(employeeService.getAllEmployees(pageable),HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id).map(employee -> new ResponseEntity<>(employee,HttpStatus.FOUND)).orElseGet(()-> new ResponseEntity<>(null,HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable Integer id){
        return employeeService.deleteEmployeeById(id).map(employee -> new ResponseEntity<>(employee,HttpStatus.OK)).orElseGet(()->new ResponseEntity<>(null,HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Integer id,@RequestBody Employee employee){
        return employeeService.updateById(id,employee).map(e-> new ResponseEntity<>(e,HttpStatus.OK)).orElseGet(()-> new ResponseEntity<>(null,HttpStatus.NOT_FOUND));
    }

}
