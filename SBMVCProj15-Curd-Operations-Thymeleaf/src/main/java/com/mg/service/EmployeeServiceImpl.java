package com.mg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mg.entity.Emp;
import com.mg.repo.IEmployeeRepo;

@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired 
	IEmployeeRepo iEmpRepo;
	
	@Override
	public List<Emp> getAllEmployee() {
		List<Emp> it=iEmpRepo.findAll();
//		it.sort((emp1,emp2)->emp1.getEmpno().compareTo(emp2.getEmpno()));
		it.sort((emp1,emp2)->emp1.getEname().compareTo(emp2.getEname()));
		return it;
	}

	@Override
	public String registerNewEmployee(Emp emp) {
		emp.setEname(emp.getEname().toUpperCase());
		emp.setJob(emp.getJob().toUpperCase());
		return "Employee Data saved with the Employee Number:: "+iEmpRepo.save(emp).getEmpno();
	}

	
	@Override
	public Emp getEmployeeById(Integer id) {
		
		Emp emp=iEmpRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Given id not found"));
		return emp;
	}

	@Override
	public String editEmployeeDetails(Emp emp) {
		
		return "Employee details are updated with id value:: "+iEmpRepo.save(emp).getEmpno();
	}

	@Override
	public String deleteEmployeeById(Integer id) {
		iEmpRepo.deleteById(id);
		return "Employee details are removed with the id value:: "+id;
	}

	@Override
	public Page<Emp> showEmpReportWithDynamicSearch(Emp emp,List<Integer> deptnos,Pageable pageable) {
		String name=null,job=null;
		if(emp.getEname()==null|| emp.getEname().length()==0 || emp.getEname().equalsIgnoreCase("")) {
			emp.setEname(null);
		}else {
			name=emp.getEname().toUpperCase();
		}
		if(emp.getJob()==null || emp.getJob().length()==0 || emp.getJob().equalsIgnoreCase("")) {
			emp.setJob(null);
		}
		else {
			job=emp.getJob().toUpperCase();
		}
		Page<Emp> emps=iEmpRepo.findEmployeeByInitialChars(name, job,emp.getSal(),deptnos,pageable);
		return emps;
	}

	@Override
	public Page<Emp> getEmpRecordsByPage(Pageable pageable) {
	
		return iEmpRepo.findAll(pageable);
	}

	

}
