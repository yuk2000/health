package com.yuk2000.service;

import com.yuk2000.entity.PageResult;
import com.yuk2000.entity.Result;

import java.util.Map;
/**
 * 体检预约服务接口
 */
public interface OrderService {
    //体检预约
    public Result order(Map map) throws Exception;

    //根据id查询预约信息，包括体检人信息、套餐信息
    public Map findById(Integer id) throws Exception;

    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
}