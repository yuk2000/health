package com.yuk2000.dao;

/**
 * @Author yuk2000
 * @Date 2023/2/27 19:14
 * @Version 1.0
 */

import com.github.pagehelper.Page;
import com.yuk2000.pojo.CheckItem;

import java.util.List;

/**
 * 持久层Dao接口
 */
public interface CheckItemDao {

    public void add(CheckItem checkItem);

    public Page<CheckItem> selectByCondition(String queryString);

    public void deleteById(Integer id);

    public long findCountByCheckItemId(Integer checkItemId);

    public void edit(CheckItem checkItem);

    public CheckItem findById(Integer id);

    public List<CheckItem> findAll();
}
