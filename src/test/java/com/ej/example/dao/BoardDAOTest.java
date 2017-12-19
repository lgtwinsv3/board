package com.ej.example.dao;

import com.ej.example.domain.BoardDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class BoardDAOTest {

    private BoardDAO2 dao;

    @Before
    public void setup() {
        dao = new BoardDAO2();
    }

    private int createData() throws SQLException {
        BoardDTO vo = new BoardDTO();
        vo.setSubject("제목");
        vo.setContent("내용");
        vo.setWriter("작성자");

        return dao.insert(vo);
    }

    @org.junit.Test
    public void insert() throws Exception {
        int result = createData();
        Assert.assertTrue(result > 0);
    }

    @org.junit.Test
    public void update() throws Exception {

        int createSeq = createData();

        BoardDTO vo = new BoardDTO();
        vo.setSeq(createSeq);
        vo.setSubject("제목");
        vo.setContent("내용1");
        vo.setWriter("작성자");

        int result = dao.update(vo);

        Assert.assertTrue(1 == result);

        BoardDTO searchVo = dao.selectOne(createSeq);

        Assert.assertEquals("내용1", searchVo.getContent());
    }


    @org.junit.Test
    public void delete() throws Exception {

        int createSeq = createData();

        int result = dao.delete(createSeq);

        Assert.assertTrue(result == 1);
    }

    @Test
    public void select() throws SQLException {
        int createSeq = createData();

        System.out.println(dao.selectOne(createSeq).getWriter());
    }


    @org.junit.Test
    public void updateReadCount() throws Exception {
        int seq = createData();
        int result = dao.updateReadCount(dao.selectOne(seq));
        Assert.assertTrue(result == 1);

        BoardDTO searchVo = dao.selectOne(seq);

        Assert.assertTrue(searchVo.getReadCount() == 1);
    }


    @Test
    public void list() throws SQLException {
        System.out.println(dao.selectList(1, 10).size());
    }
}