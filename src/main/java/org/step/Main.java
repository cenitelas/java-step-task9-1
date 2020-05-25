package org.step;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, SQLException {
        Properties prop = new Properties();
        InputStream input = new FileInputStream("src/main/resources/db.properties");
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserDAO userDAO = new UserDAO(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"), prop.getProperty("db.driver"));
        for (User user : userDAO.getAll("step_test")){
            System.out.println(user.getName()+" | "+user.getAge());
        }
//        System.out.println("//////////////////");
//        User user1 = userDAO.getId("step_test",1);
//        System.out.println(user1.getName()+" | "+user1.getAge());

//        System.out.println("/////////////////////");
//        User user2 = new User("Test",33);
//        userDAO.addUser("step_test",user2);
//        for (User user : userDAO.getAll("step_test")){
//            System.out.println(user.getName()+" | "+user.getAge());
//        }

//        System.out.println("///////////////////");
//        user1.setName("UpdateName");
//        user1.setAge(512);
//        userDAO.updateUser("step_test",user1);
//        for (User user : userDAO.getAll("step_test")){
//            System.out.println(user.getName()+" | "+user.getAge());
//        }

//        System.out.println("///////////////////");
//        userDAO.deleteUser("step_test",1);
//        for (User user : userDAO.getAll("step_test")){
//            System.out.println(user.getName()+" | "+user.getAge());
//        }

//        System.out.println("///////////////////");
//        userDAO.deleteTable("step_test");
//        for (User user : userDAO.getAll("step_test")){
//            System.out.println(user.getName()+" | "+user.getAge());
//        }
    }
}
