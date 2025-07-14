package com.mg.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mg.entity.Emp;

public interface IEmployeeRepo extends JpaRepository<Emp, Integer> {
	
	@Query("FROM Emp e WHERE (e.ename LIKE %:chars% OR e.job LIKE %:job% OR e.sal >= :salRange OR e.deptno IN :deptno)")
	public Page<Emp> findEmployeeByInitialChars(String chars,String job,Double salRange,List<Integer> deptno,Pageable pageable);
}
