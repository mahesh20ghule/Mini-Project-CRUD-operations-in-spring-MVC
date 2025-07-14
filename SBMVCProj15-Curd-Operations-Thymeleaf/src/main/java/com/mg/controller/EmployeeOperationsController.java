package com.mg.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mg.entity.Emp;
import com.mg.service.IEmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/employee")
public class EmployeeOperationsController {

	@Autowired
	private IEmployeeService iEmp;
	
	
	
	@RequestMapping("/welcome")
	public String welcomeHelper() {
		return "greet";
	}
	@GetMapping("/report")
	public String report(@PageableDefault(page=0,size=5,sort="job",direction=Sort.Direction.ASC) Pageable pageable,
						Map<String,Object> map,@ModelAttribute Emp emp) {
	System.out.println("EmployeeOperationsController.report()");
		try {
			Page<Emp> page=iEmp.getEmpRecordsByPage(pageable);
			map.put("empList", page);
		return "show_all_employee";
		}catch(Exception e) {
			e.printStackTrace();
			return errorHandler();
		}
	}
	 
	/*@GetMapping("/report")
	public String report(Map<String,Object> map,@ModelAttribute Emp emp) {
		try {
		map.put("empList", iEmp.getAllEmployee());
	//		map.put("emp", emp);
	//		map.put("empList", new ArrayList<Emp>());
		return "show_all_employee";
		}catch(Exception e) {
			e.printStackTrace();
			return errorHandler();
		}
	}
	*/
	@PostMapping("/search")
	public String showReportWithDynamicSearch(@PageableDefault(page=0,size=5,sort="job",direction=Sort.Direction.ASC) Pageable pageable,
											@ModelAttribute Emp emp,Map<String,Object> map,@RequestParam(required=false) List<Integer> deptnos,
											HttpSession session) {
		System.out.println("EmployeeOperationsController.showReportWithDynamicSearch()");
		try {
		Page<Emp> list=iEmp.showEmpReportWithDynamicSearch(emp,deptnos,pageable);
		map.put("empList", list);
		 session.setAttribute("empSearch", emp);
		 session.setAttribute("deptnoSearch", deptnos);
		return "redirect:/employee/search?page=0";
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@GetMapping("/search")
	public String showReportWithPageSearch(@PageableDefault(page=0,size=5,sort="job",direction=Sort.Direction.ASC) Pageable pageable,
		Map<String,Object> map,HttpSession ses) {
		System.out.println("EmployeeOperationsController.showReportWithDynamicSearch()");
		try {
			Emp emp=(Emp) ses.getAttribute("empSearch");
			List<Integer> deptno=(List<Integer>)ses.getAttribute("deptnoSearch");
			if(emp==null) emp=new Emp();
			Page<Emp> list=iEmp.showEmpReportWithDynamicSearch(emp,deptno,pageable);
			map.put("empList", list);
			map.put("emp", emp);
			return "show_searched_emp";
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	
	@RequestMapping("/custom_error")
	public String errorHandler() {
		return "error";
	}
	@GetMapping("/emp_add")
	public String showRegisterPage(@ModelAttribute Emp emp) {
		return "register_emp";
	}
	
	/*@PostMapping("/emp_add")
	public String registerEmployee(@ModelAttribute Emp emp,Map<String,Object> map) {
		try {
			String msg=iEmp.registerNewEmployee(emp);
			map.put("msg", msg);
			return "redirect:report";
			
		}catch(Exception e) {
			e.printStackTrace();
			return errorHandler();
		}
	}*/
	/*@PostMapping("/emp_add")
	public String registerEmployee(@ModelAttribute Emp emp,RedirectAttributes attr) {
		try {
			String msg=iEmp.registerNewEmployee(emp);
			attr.addFlashAttribute("msg", msg);
			return "redirect:report";
			
		}catch(Exception e) {
			e.printStackTrace();
			return errorHandler();
		}
	}*/
	@PostMapping("/emp_add")
	public String registerEmployee(@ModelAttribute Emp emp, RedirectAttributes attr) {
	    try {
	        String msg = iEmp.registerNewEmployee(emp);
	        attr.addFlashAttribute("msg", msg); // adds msg for next request
	        return "redirect:report";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return errorHandler();
	    }
	}

	@GetMapping("/edit")
	public String showEditEmployeeFormPage(@RequestParam Integer id,@ModelAttribute Emp emp) {
		try {
		Emp emp2=iEmp.getEmployeeById(id);
		BeanUtils.copyProperties(emp2, emp);
		return "edit_emp";
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@PostMapping("/edit")
	public String editEmployeeDetails(@ModelAttribute Emp emp,RedirectAttributes attr) {
		try {
			String msg=iEmp.editEmployeeDetails(emp);
			attr.addFlashAttribute("msg",msg);
			return "redirect:report";
		}catch(Exception e) {
		e.printStackTrace();
			return "error";
		}
	}
	
	@GetMapping("/delete")
	public String deleteEmployeeById(@RequestParam Integer id,Map<String,Object> map) {
		try {
			String msg=iEmp.deleteEmployeeById(id);
			map.put("msg",msg);
			return "forward:report";
		}catch(Exception e) {
		e.printStackTrace();
			return "error";
		}
	}
	

	
}
