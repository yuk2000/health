package com.yuk2000.service;

/**
 * @Author yuk2000
 * @Date 2023/2/27 19:08
 * @Version 1.0
 */

import com.yuk2000.entity.PageResult;
import com.yuk2000.pojo.CheckItem;

import java.util.List;

/**
 * 检查项服务接口
 */
public interface CheckItemService {
    public void add(CheckItem checkItem);

    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    public void delete(Integer id);

    public void edit(CheckItem checkItem);

    public CheckItem findById(Integer id);

    public List<CheckItem> findAll();
}
