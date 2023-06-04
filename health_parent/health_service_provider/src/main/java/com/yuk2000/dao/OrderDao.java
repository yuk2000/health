package com.yuk2000.dao;

import com.github.pagehelper.Page;
import com.yuk2000.pojo.CheckGroup;
import com.yuk2000.pojo.Order;
import com.yuk2000.pojo.OrderInfo;

import java.util.List;
import java.util.Map;

public interface OrderDao {


    public void add(Order order);
    public List<Order> findByCondition(Order order);
    public Map findById4Detail(Integer id);
    public Integer findOrderCountByDate(String date);
    public Integer findOrderCountAfterDate(String date);
    public Integer findVisitsCountByDate(String date);
    public Integer findVisitsCountAfterDate(String date);
    public List<Map> findHotSetmeal();

    public Page<OrderInfo> selectByCondition(String queryString);
}