package com.siemens.company.service;

import com.siemens.company.model.Employee;
import com.siemens.company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    final private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(Pageable pageable){
        var temp = new ArrayList<Employee>();
        employeeRepository.findAll(pageable).stream().forEach(temp::add);
        return temp;
    }

    public Optional<Employee> getEmployeeById(Integer id){
        return employeeRepository.findById(id);
    }

    public Optional<Employee> deleteEmployeeById(Integer id){
        var temp = employeeRepository.findById(id);
        temp.ifPresent(employeeRepository::delete);
        return temp;
    }

    public Optional<Employee> updateById(Integer id,Employee employee){
        return employeeRepository.findById(id).map(e -> employeeRepository.save(new Employee(e.getId(), employee.getName())));
    }
}
