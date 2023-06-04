package com.yuk2000.dao;

import com.github.pagehelper.Page;
import com.yuk2000.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    public void add(Setmeal setmeal);
    public void setSetmealAndCheckGroup(Map<String, Integer> map);

    public Page<Setmeal> selectByCondition(String queryString);

    public List<Setmeal> findAll();

    public Setmeal findById(int id);
    public void deleteById(Integer id);

    public List<Map<String,Object>> findSetmealCount();

    void deleteAssociation(Integer id);
    public long findCountBySetmealId(Integer setmealId);
}