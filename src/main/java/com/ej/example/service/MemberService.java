package com.ej.example.service;

import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;
import com.ej.example.domain.Paging;

import java.sql.SQLException;
import java.util.List;

public class MemberService implements IService<MemberDTO> {

    private MemberDAO dao;

    public MemberService() {
        this.dao = new MemberDAO();
    }

    public Paging<MemberDTO> getList(int page, int rowCount) throws SQLException {

//        int count = dao.selectCount();

        Paging<MemberDTO> paging = new Paging<MemberDTO>();
//        paging.calcPaging(count);

        List<MemberDTO> list = dao.selectList(paging.getSkipRowCount(), paging.getRowCount());

        paging.setBody(list);

        return paging;
    }

    public MemberDTO getOne(int seq) throws SQLException {
        return dao.selectOne(seq);
    }

    public int regist(MemberDTO dto) throws SQLException {
        return dao.insert(dto);
    }

    public int modify(MemberDTO dto) throws SQLException {
        return dao.update(dto);
    }

    public int remove(int seq) throws SQLException {
        return dao.delete(seq);
    }

}
