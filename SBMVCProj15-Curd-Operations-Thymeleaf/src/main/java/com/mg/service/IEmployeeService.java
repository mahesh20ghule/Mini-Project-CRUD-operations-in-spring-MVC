package com.mg.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mg.entity.Emp;

public interface IEmployeeService {
	
	public List<Emp> getAllEmployee();
	public String registerNewEmployee(Emp emp);
	
	public Emp getEmployeeById(Integer id);
	public String editEmployeeDetails(Emp emp);
	public String deleteEmployeeById(Integer id);
	
	public Page<Emp> showEmpReportWithDynamicSearch(Emp emp,List<Integer> deptnos,Pageable pageable);
	
	public Page<Emp> getEmpRecordsByPage(Pageable pageable);
}
