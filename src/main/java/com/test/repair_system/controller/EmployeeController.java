package com.test.repair_system.controller;

import com.test.repair_system.dao.IEmployeeDao;
import com.test.repair_system.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class EmployeeController {

    @Autowired
    private IEmployeeDao iEmployeeDao;

    @PostMapping("/register")
    public String[] register(Employee employee) {
        return iEmployeeDao.register(employee);
    }

    @PostMapping("/login")
    public String[] login(Employee employee) {
        return iEmployeeDao.login(employee);
    }

    @PostMapping("/resetPwd")
    public String[] ResetPwd(Employee employee) {
        return iEmployeeDao.ResetPwd(employee);
    }

    @PostMapping("/findName")
    public String findName(Employee employee) {
        return iEmployeeDao.findName(employee);
    }
}
