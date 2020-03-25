package com.test.repair_system.dao.impl;

import com.test.repair_system.dao.IEmployeeDao;
import com.test.repair_system.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class EmployeeDaoImpl implements IEmployeeDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String[] register(Employee employee) {

        String[] info = {"0", ""};

        if (employee.getEmployeeId().length() < 4) {
            info[1] = "注册失败！工号不能小于4位或为空！";
        } else if (employee.getEmployeeName().equals("")) {
            info[1] = "注册失败！姓名不能为空";
        } else if (!employee.getPassword().equals(employee.getPassword1())) {
            info[1] = "注册失败！密码与确认密码不一致！";
        } else if (employee.getPassword().length() < 6) {
            info[1] = "注册失败！密码不能小于6位为空！";
        } else {
            Query query = new Query(Criteria.where("identity").is(employee.getIdentity()).and("employeeId").is(employee.getEmployeeId()));
            Employee one = mongoTemplate.findOne(query, Employee.class);
            if (one == null) {
                mongoTemplate.save(employee);
                info[0] = "1";
                info[1] = "注册成功！";
            } else {
                info[1] = "注册失败！工号已存在！";
            }
        }

        return info;
    }

    @Override
    public String[] login(Employee employee) {
        String[] info = {"0", "", employee.getEmployeeId()};

        if ("".equals(employee.getEmployeeId())) {
            info[1] = "登录失败！工号不能为空！";
        } else if ("".equals(employee.getPassword())) {
            info[1] = "登录失败！密码不能为空！";
        } else {
            Query query = new Query(Criteria.where("identity").is(employee.getIdentity()).and("employeeId").is(employee.getEmployeeId()));
            Employee one = mongoTemplate.findOne(query, Employee.class);
            if (one == null) {
                info[1] = "登录失败！工号不存在！";
            } else if (one.getPassword().equals(employee.getPassword())) {
                info[0] = "1";
                info[1] = "登录成功！";
            } else {
                info[1] = "登录失败！密码错误！";
            }
        }

        if (info[0] == "1") {

        }

        return info;
    }

    @Override
    public String[] ResetPwd(Employee employee) {
        String[] info = {"0", ""};

        Query query = new Query(Criteria.where("identity").is(employee.getIdentity()).and("employeeId").is(employee.getEmployeeId()));
        Employee one = mongoTemplate.findOne(query, Employee.class);

        if ("".equals(employee.getEmployeeId())) {
            info[1] = "工号不能为空！";
        } else if ("".equals(employee.getPassword())) {
            info[1] = "初始密码不能为空！";
        } else if ("".equals(employee.getPassword1())) {
            info[1] = "重置密码不能为空！";
        } else if ("".equals(employee.getPassword2())) {
            info[1] = "确认密码不能为空！";
        } else if (one == null) {
            info[1] = "工号不存在！";
        } else if (!one.getPassword().equals(employee.getPassword())) {
            info[1] = "初始密码错误！";
        } else if (!employee.getPassword1().equals(employee.getPassword2())) {
            info[1] = "重置密码与确认密码不一致！";
        } else if (employee.getPassword1().length() < 6) {
            info[1] = "重置密码不能小于6位！";
        } else {
            //更新密码！！！
            Update update = new Update();
            update.set("password", employee.getPassword1());
            mongoTemplate.updateFirst(query, update, Employee.class);

            info[0] = "1";
            info[1] = "修改密码成功！";
        }
        return info;
    }

    @Override
    public String findName(Employee employee) {
        Query query = new Query(Criteria.where("employeeId").is(employee.getEmployeeId()));
        Employee one = mongoTemplate.findOne(query, Employee.class);
        return one.getEmployeeName();
    }

}
