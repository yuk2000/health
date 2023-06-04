package com.yuk2000.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yuk2000.dao.CheckGroupDao;
import com.yuk2000.entity.PageResult;
import com.yuk2000.pojo.CheckGroup;
import com.yuk2000.pojo.CheckItem;
import com.yuk2000.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检查组服务
 */
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    //添加检查组合，同时需要设置检查组合和检查项的关联关系
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);
        setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
    }
    //设置检查组合和检查项的关联关系
    public void setCheckGroupAndCheckItem(Integer checkGroupId,Integer[] checkitemIds){
        if(checkitemIds != null && checkitemIds.length > 0){
            for (Integer checkitemId : checkitemIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("checkgroup_id",checkGroupId);
                map.put("checkitem_id",checkitemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }

    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page = checkGroupDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    //编辑检查组，同时需要更新和检查项的关联关系
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        //根据检查组id删除中间表数据（清理原有关联关系）
        checkGroupDao.deleteAssociation(checkGroup.getId());
        //向中间表(t_checkgroup_checkitem)插入数据（建立检查组和检查项关联关系）
        setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
        //更新检查组基本信息
        checkGroupDao.edit(checkGroup);
    }

//    //向中间表(t_checkgroup_checkitem)插入数据（建立检查组和检查项关联关系）
//    public void setCheckGroupAndCheckItem(Integer checkGroupId,Integer[] checkitemIds){
//        if(checkitemIds != null && checkitemIds.length > 0){
//            for (Integer checkitemId : checkitemIds) {
//                Map<String,Integer> map = new HashMap<>();
//                map.put("checkgroup_id",checkGroupId);
//                map.put("checkitem_id",checkitemId);
//                checkGroupDao.setCheckGroupAndCheckItem(map);
//            }
//        }
//    }

    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

    //删除
    public void delete(Integer id) throws RuntimeException{

        //查询当前检查项是否和检查组关联
        long count = checkGroupDao.findCountByCheckGroupId(id);
        if(count > 0){
            //当前检查项被引用，不能删除
            throw new RuntimeException("当前检查组被引用，不能删除");
        }
        checkGroupDao.deleteAssociation(id);
        checkGroupDao.deleteById(id);
    }
}