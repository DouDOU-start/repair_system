package com.test.repair_system.dao.impl;

import com.test.repair_system.dao.IEmployeeDao;
import com.test.repair_system.dao.IRepairDao;
import com.test.repair_system.pojo.Repair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class RepairImpl implements IRepairDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String newRepair(Repair repair) {
        mongoTemplate.save(repair);
        return "提交成功！";
    }

    @Override
    public List<Repair> findRepair() {
        return mongoTemplate.findAll(Repair.class);
    }
}
