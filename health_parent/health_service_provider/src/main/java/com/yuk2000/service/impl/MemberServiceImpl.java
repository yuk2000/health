package com.yuk2000.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yuk2000.dao.MemberDao;
import com.yuk2000.entity.PageResult;
import com.yuk2000.pojo.Member;
import com.yuk2000.pojo.OrderInfo;
import com.yuk2000.service.MemberService;
import com.yuk2000.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    //新增会员
    public void add(Member member) {
        if(member.getPassword() != null){
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberDao.add(member);
    }

    //根据月份统计会员数量
    public List<Integer> findMemberCountByMonth(List<String> month) {
        List<Integer> list = new ArrayList<>();

        for(String m : month){
            try {
                String date_str = m + "-10";
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM-dd");
                calendar.setTime(sdf.parse(date_str));
                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH, 0);
                // 获取当月最后一天
                String lastDayOfMonth = sdf.format(calendar.getTime());
                Integer count = memberDao.findMemberCountBeforeDate(lastDayOfMonth);
                list.add(count);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    //分页查询
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Member> page = memberDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }
}
