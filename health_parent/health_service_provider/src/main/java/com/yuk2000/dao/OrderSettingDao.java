package com.yuk2000.dao;

import com.yuk2000.pojo.OrderSetting;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    public void add(OrderSetting orderSetting);
    public void editNumberByOrderDate(OrderSetting orderSetting);

    //更新已预约人数
    public void editReservationsByOrderDate(OrderSetting orderSetting);
    public long findCountByOrderDate(Date orderDate);
    //根据日期范围查询预约设置信息
    public List<OrderSetting> getOrderSettingByMonth(Map date);
    //根据预约日期查询预约设置信息
    public OrderSetting findByOrderDate(Date orderDate);
}