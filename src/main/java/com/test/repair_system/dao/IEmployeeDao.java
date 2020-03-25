package com.test.repair_system.dao;

import com.test.repair_system.pojo.Employee;

import javax.servlet.http.HttpSession;

public interface IEmployeeDao {

    String[] register(Employee employee);

    String[] login(Employee employee);

    String[] ResetPwd(Employee employee);

    String findName(Employee employee);

}
