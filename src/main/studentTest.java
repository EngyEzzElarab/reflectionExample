package main;

import main.ObjectRelationalMapping;
import main.Student;
import org.junit.Test;

import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.Date;

public class studentTest {

    @Test
    public void testStudent() {
        Student s = new Student("engy", 21, new Date(), true);
        try {
            Field[] listOfPublicFields = s.getClass().getDeclaredFields();
            for (int i = 0; i < listOfPublicFields.length; i++) {
                Field field = listOfPublicFields[i];
                System.out.println("name= " + field.getName());
                System.out.println("type= " + field.getType().getTypeName());
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }

    @Test
    public void createStudentTable() throws SQLException, ClassNotFoundException {
        //given the app connected on emtpy db
        ObjectRelationalMapping.create(Student.class);
        //
        //
    }

    @Test
    public void insertIntoStudent() throws SQLException, ClassNotFoundException {
        main.ObjectRelationalMapping.insert(new main.Student("amany", 22, new Date(), false));

    }

    @Test
    public void selectAllFromStudent() throws SQLException, ClassNotFoundException {
        main.ObjectRelationalMapping.selectAll();

    }
}
