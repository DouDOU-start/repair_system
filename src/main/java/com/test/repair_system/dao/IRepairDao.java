package com.test.repair_system.dao;

import com.test.repair_system.pojo.Repair;

import java.util.List;

public interface IRepairDao {

    String newRepair(Repair repair);

    public List<Repair> findRepair();
}
