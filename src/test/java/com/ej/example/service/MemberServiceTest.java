package com.ej.example.service;

import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;
import com.ej.example.domain.Paging;

import java.sql.SQLException;

public class MemberServiceTest {


    MemberService service = new MemberService(new MemberDAO());

    public void testList() throws SQLException {

        Paging<MemberDTO> list = service.getList(1, 10);

        System.out.println(list);

    }


}