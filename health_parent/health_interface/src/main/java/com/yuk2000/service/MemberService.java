package com.yuk2000.service;

import com.yuk2000.entity.PageResult;
import com.yuk2000.pojo.Member;

import java.util.List;

public interface MemberService {
    public void add(Member member);
    public Member findByTelephone(String telephone);

    public List<Integer> findMemberCountByMonth(List<String> month);

    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
}
