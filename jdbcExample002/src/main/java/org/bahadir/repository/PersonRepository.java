package org.bahadir.repository;

import org.bahadir.entity.Person;
import org.bahadir.util.JdbcHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonRepository {
    static Scanner sc = new Scanner(System.in);
    public static void createPerson(Person person){
        Connection connection  = null;
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO persons(first_name, last_name, email) VALUES (?, ?, ?)";
        try {
            connection = JdbcHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,person.getFirst_name());
            preparedStatement.setString(2,person.getLast_name());
            preparedStatement.setString(3,person.getEmail());

            int i = preparedStatement.executeUpdate();

            if (i>0){
                System.out.println("Ekleme başarılı");
            }else {
                System.out.println("Ekleme başarısız!!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Sorgusunda hata olabilir! " + e.getMessage());
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void updatePerson(int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE persons SET first_name=?, last_name=?, email=? WHERE id=?;";
        try {
            connection = JdbcHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"Alex de");
            preparedStatement.setString(2,"Souza");
            preparedStatement.setString(3,"alex@gmail.com");
            preparedStatement.setInt(4,id);

            int i = preparedStatement.executeUpdate();

            if (i > 0 ){
                System.out.println("Güncelleme işlemi başarılı...");
            }else {
                System.out.println("Güncelle işlemi yapılırken hata oluştu!!");
            }

        } catch (SQLException e) {
            System.out.println("SQL Sorgusunda hata olabilir! " + e.getMessage());
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void deletePersonById(int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM persons WHERE id=?;";

        try {
            connection = JdbcHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            int i = preparedStatement.executeUpdate();

            if (i > 0 ){
                System.out.println("Silme işlemi başarılı...");
            }else {
                System.out.println("Silme işleminde hata meydana geldi!!!");
            }
        } catch (SQLException e) {
            System.out.println("SQL Sorgusunda hata olabilir! " + e.getMessage());
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void findPersonById(int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM persons WHERE id=?;";

        try {
            connection = JdbcHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int idd = resultSet.getInt("id");
                String name = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String joinedDate = resultSet.getString("joined_date");

                System.out.println("Id: " + id + " Name: " + name + " Lastname: " + lastName + " JoinedDate: " + joinedDate);
            }
        } catch (SQLException e) {
            System.out.println("SQL Sorgusunda hata olabilir! " + e.getMessage());
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void getAllPersons(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM persons";

        try {
            connection = JdbcHelper.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String joinedDate = resultSet.getString("joined_date");

                System.out.println("Id: " + id + " Name: " + name + " Lastname: " + lastName + " Email: " + email + " JoinedDate: " + joinedDate);
            }
        } catch (SQLException e) {
            System.out.println("SQL Sorgusunda hata olabilir! " + e.getMessage());
        }finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
