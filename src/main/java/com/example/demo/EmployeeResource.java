package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeResource {
	
	@Autowired
	EmployeeRepository repo;

	@GetMapping("/employees")
	public List<Employee> getEmployee()
	{
		
		List<Employee> employees = (List<Employee>) repo.findAll();
		
		
		return employees;
	}
	
	
	@GetMapping("/employeeDetails")
	public Employee getEmployeeById(@RequestParam int id) {
	    Employee employee = repo.findById(id).orElse(null);

	    return employee;
	}
	
	@PutMapping("/updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@RequestParam Integer id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> existingEmployeeOptional = repo.findById(id);

        if (existingEmployeeOptional.isPresent()) {
            Employee existingEmployee = existingEmployeeOptional.get();

            existingEmployee.setEmp_id(updatedEmployee.getEmp_id());
            existingEmployee.setEmp_name(updatedEmployee.getEmp_name());
            existingEmployee.setEmp_dept(updatedEmployee.getEmp_dept());

         
            Employee savedEmployee = repo.save(existingEmployee);

            return ResponseEntity.ok(savedEmployee);
        } else {
      
            return ResponseEntity.notFound().build();
        }
	}
	
	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
	    Employee savedEmployee = repo.save(employee);
	    return ResponseEntity.ok(savedEmployee);
	}
    
	@DeleteMapping("/deleteEmployee")
    public ResponseEntity<String> deleteEmployee(@RequestParam Integer id) {
        Optional<Employee> employeeOptional = repo.findById(id);

        if (employeeOptional.isPresent()) {
            repo.deleteById(id);
            return ResponseEntity.ok("Employee with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	
	
}
