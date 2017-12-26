package com.ej.example.dao;

import com.ej.example.dao.member.MemberDAO;
import com.ej.example.domain.MemberDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Random;

public class MemberDAOTest {

    private MemberDAO dao;

    @Before
    public void setup() {
        dao = new MemberDAO();
    }

    private int createData() throws SQLException {

        Random random = new Random();
        int randomNum = random.nextInt(1000000);
        MemberDTO dto = new MemberDTO();
        dto.setUserId("eunjung115" + String.valueOf(randomNum));
        dto.setPassword("1234");
        dto.setName("은쟁");
        dto.setEmail("eunjung115@naver.com");
        return dao.insert(dto);
    }

    @Test
    public void insert() throws Exception {
        int result = createData();
        Assert.assertTrue(result > 0);
    }

    @Test
    public void update() throws Exception {

        int createSeq = createData();
        MemberDTO dto = new MemberDTO();
        dto.setUserId("ej" + createSeq);
        dto.setName("은쟁1");
        dto.setEmail("ejejejej@fdf.dfdf");
        dto.setPassword("4321");
        dto.setSeq(createSeq);

        int result = dao.update(dto);
        Assert.assertTrue(result > 0);

        MemberDTO newDto = dao.selectOne(createSeq);
        Assert.assertEquals(dto.getName(), newDto.getName());

    }


    @Test
    public void delete() throws Exception {

        int createSeq = createData();

        int result = dao.delete(createSeq);

        Assert.assertTrue(result == 1);
    }

    @Test
    public void select() throws SQLException {
        int createSeq = createData();

        System.out.println(dao.selectOne(37).getName());
    }

    @Test
    public void list() throws SQLException {
        System.out.println(dao.selectList(1, 10).size());
    }
}