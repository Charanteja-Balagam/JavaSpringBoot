package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>
{

	


	@Query("from Employee e where e.emp_name= :ename")
	Employee findByName(@Param("ename")String ename);

	
	
}
