package com.test.repair_system.controller;

import com.test.repair_system.dao.IRepairDao;
import com.test.repair_system.pojo.Employee;
import com.test.repair_system.pojo.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RepairController {

    @Autowired
    private IRepairDao iRepairDao;

    @PostMapping("/newRepair")
    public String newRepair(Repair repair) {
        return iRepairDao.newRepair(repair);
    }

    @PostMapping("/findRepair")
    public List<Repair> findRepair() {
        return iRepairDao.findRepair();
    }
}
