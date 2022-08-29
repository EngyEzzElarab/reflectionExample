package main;

import main.Student;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.HashMap;

public class ObjectRelationalMapping {
    public static void create(Class<?> someClass) throws SQLException, ClassNotFoundException {
        // explore class fields(string , int , date, boolean)
        // table name follows sql convention not Java
        // i.e StudentActivity => student_activity
        //create a table for the class
        // getting th
        //hashmap for the types
        HashMap<Type, String> map = new HashMap<>();
        map.put(java.util.Date.class, "DATE");
        map.put(java.lang.String.class, "VARCHAR(255)");
        map.put(boolean.class, "TINYINT");
        map.put(int.class, "INTEGER");

        Field[] listOfFields;
        listOfFields = someClass.getDeclaredFields();
        Connection conn = connectToDB();
        Statement stmt = conn.createStatement();
        String tableName = someClass.getSimpleName().toLowerCase();
        String sql = "CREATE TABLE " + tableName + " (";

        for (int i = 0; i < listOfFields.length; i++) {
            Field field = listOfFields[i];
            field.setAccessible(true);
            Type type = field.getType();
            sql += field.getName() + " " + map.get(type);
            if (i != listOfFields.length - 1)
                sql += ", \n ";
        }
        sql += ");";
        stmt.executeUpdate(sql);
        System.out.println("Table Created Successfully");
        conn.close();


    }

    public static void insert(Student s) throws SQLException, ClassNotFoundException {
        Connection conn = connectToDB();
        String query = "INSERT INTO student VALUES( ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, s.getName());
        preparedStatement.setInt(2, s.getAge());
        preparedStatement.setDate(3, new Date(622790105000L));
        preparedStatement.setBoolean(4, s.getIsEnrolled());
        //execution
        preparedStatement.executeUpdate();
        conn.close();
        System.out.println("The item has been inserted successfully");
    }

    public static void selectAll() throws SQLException, ClassNotFoundException {
        Connection conn = connectToDB();
        String sql = "SELECT * FROM student";
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(sql);
        while (result.next()) {
            System.out.println(result.getString(1) + " " + result.getString(2) + " " + result.getString(3) + " " + result.getString(4));
        }
        conn.close();
    }

    public static Connection connectToDB() throws ClassNotFoundException, SQLException {
        // load and register JDBC driver for MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "ya33");
        System.out.println("Connected With the database successfully");
        return connection;
    }
}
