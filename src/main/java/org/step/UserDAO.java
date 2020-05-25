package org.step;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private String dbDriver;

    public UserDAO(String dbUrl, String dbUser, String dbPassword, String dbDriver) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.dbDriver = dbDriver;
    }

    public List<User> getAll(String table) throws SQLException {
        Connection connection = null;
        List<User> users = new ArrayList<User>();
        try {
            Class.forName(this.dbDriver);
            connection = DriverManager
                    .getConnection(this.dbUrl, this.dbUser, this.dbPassword);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String query = String.format("SELECT * FROM %s",table);
            rs = stmt.executeQuery(query);
            while ( rs.next() ) {
                String name = rs.getString("name");
                Integer age = rs.getInt("age");
                Integer id = rs.getInt("id");
                users.add(new User(id,name,age));
            }
        } catch (SQLException throwables) {
            System.out.println("List users is null");
            return null;
        }finally {
            connection.close();
            return users;
        }

    }

    public boolean addUser(String table, User user) throws SQLException {
          Connection connection = null;
        try {
            Class.forName(this.dbDriver);
            connection = DriverManager
                    .getConnection(this.dbUrl, this.dbUser, this.dbPassword);
            PreparedStatement preparedStmt = connection.prepareStatement("INSERT INTO "+table+"(name,age) VALUES (?,?);");
            preparedStmt.setString (1, user.getName());
            preparedStmt.setInt    (2, user.getAge());
            preparedStmt.execute();
        } catch (SQLException throwables) {
            System.out.println("User does not create");
            return false;
        }finally {
            connection.close();
            return true;
        }
    }

    public User getId(String table, int id) throws SQLException {
        Connection connection = null;
        User user = null;
        try {
            Class.forName(this.dbDriver);
            connection = DriverManager
                    .getConnection(this.dbUrl, this.dbUser, this.dbPassword);
            Statement stmt = connection.createStatement();
            ResultSet rs;
            String query = String.format("SELECT * FROM %s WHERE id=%d;",table, id);
            rs = stmt.executeQuery(query);
            while ( rs.next() ) {
                String name = rs.getString("name");
                Integer age = rs.getInt("age");
                user = new User(id,name,age);
            }
        } catch (SQLException throwables) {
            System.out.println("User not exist");
            return null;
        }finally {
            connection.close();
            return user;
        }
    }

    public boolean updateUser(String table, User user) throws SQLException {
        Connection connection = null;
        try {
            Class.forName(this.dbDriver);
            connection = DriverManager
                    .getConnection(this.dbUrl, this.dbUser, this.dbPassword);
            PreparedStatement preparedStmt = connection.prepareStatement("UPDATE "+table+" SET name=? , age=? WHERE id=?;");
            preparedStmt.setString (1, user.getName());
            preparedStmt.setInt    (2, user.getAge());
            preparedStmt.setInt    (3, user.getId());
            preparedStmt.execute();
        } catch (SQLException throwables) {
            System.out.println("User not exist");
            return false;
        } finally {
            connection.close();
            return true;
        }
    }

    public boolean deleteUser(String table, int id) throws SQLException {
        Connection connection = null;
        try {
            Class.forName(this.dbDriver);
            connection = DriverManager
                    .getConnection(this.dbUrl, this.dbUser, this.dbPassword);
            PreparedStatement preparedStmt = connection.prepareStatement("DELETE FROM "+table+" WHERE id=?;");
            preparedStmt.setInt    (1, id);
            preparedStmt.execute();
        } catch (SQLException throwables) {
            System.out.println("User not exist");
            return false;
        } finally {
            connection.close();
            return true;
        }
    }

    public boolean deleteTable(String table) throws SQLException {
        Connection connection = null;
        try {
            Class.forName(this.dbDriver);
            connection = DriverManager
                    .getConnection(this.dbUrl, this.dbUser, this.dbPassword);
            PreparedStatement preparedStmt = connection.prepareStatement("DROP TABLE "+table);
            preparedStmt.execute();
        } catch (SQLException throwables) {
            System.out.println("Table not exist");
            return false;
        } finally {
            connection.close();
            return true;
        }
    }
}
