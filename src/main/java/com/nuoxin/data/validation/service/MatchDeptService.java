package com.nuoxin.data.validation.service;

import bean.Departments;
import bean.Dept;
import control.MatchDept;
import org.springframework.stereotype.Service;

/**
 * Created by fenggang on 1/8/18.
 *
 * @author fenggang
 * @date 1/8/18
 */
@Service
public class MatchDeptService {

    /**
     * 获取规则清洗后的科室
     * @param id
     * @param depart
     * @return
     */
    public Departments find(Integer id, String depart) {
        MatchDept matchDept = new MatchDept();
        Dept dept = new Dept();
        dept.setId(id);
        dept.setValue(depart);
        Departments departments = matchDept.matchOne(dept);
        return departments;
    }
}
