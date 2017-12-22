package com.ej.example.dao;

import com.ej.example.dao.board.OldBoardDAO;
import com.ej.example.domain.BoardDTO;
import com.ej.example.domain.DTO;
import com.ej.example.domain.MemberDTO;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class JavaTest {


    @Test
    public void stringLengthTest() {
        String str = "string";
        Assert.assertEquals(str.length(), 6);
    }

    public <T extends DTO> T testClass(String className, Class<T> type) {

        Class<BoardDTO> t = BoardDTO.class;
        return type.cast(MemberDTO.class);
    }

    @Test
    public void reflectionTest() {
        String str = "string";
        try {
//            Object length = String.class.getMethod("length").invoke(str);
            Object length = Class.forName("java.lang.String").getMethod("length").invoke(str);

            for (Field field : String.class.getDeclaredFields()) {
                System.out.println("field name : " + field.getName());
                System.out.println("field type : " + field.getType());
                System.out.println("================================");
            }
            Assert.assertEquals(Integer.parseInt(length.toString()), 6);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void accessTest() {
        try {
            OldBoardDAO manager2 = OldBoardDAO.class.newInstance();
            for (Field field : OldBoardDAO.class.getDeclaredFields()) {
                System.out.println("field name : " + field.getName());
                System.out.println("field type : " + field.getType());
                System.out.println("================================");
            }

            System.out.println(manager2.query);

            Class c = OldBoardDAO.class;
            OldBoardDAO d = OldBoardDAO.class.newInstance();

            Class<?> anon = Class.forName("com.ej.example.domain.MemberDTO");


            Class<? extends DTO> dto = Class.forName("com.ej.example.domain.BoardDTO").asSubclass(DTO.class);

//            testClass("", anon.asSubclass(DTO.class));


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
