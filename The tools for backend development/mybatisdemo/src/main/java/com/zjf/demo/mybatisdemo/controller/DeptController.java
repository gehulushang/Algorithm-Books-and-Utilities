package com.zjf.demo.mybatisdemo.controller;


import com.zjf.demo.mybatisdemo.bean.Department;

import com.zjf.demo.mybatisdemo.bean.Employee;
import com.zjf.demo.mybatisdemo.mapper.DepartmentMapper;
import com.zjf.demo.mybatisdemo.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentMapper.getDeptById(id);
    }

    @GetMapping("/dept")
    public Department insertDepartment(Department department){
         departmentMapper.insertDept(department);
        return department;

    }

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        return employeeMapper.getEmpById(id);
    }

    @GetMapping("/emp")
    public Employee insertEmployee(Employee employee){
        employeeMapper.insertEmp(employee);
        return employee;

    }


}















