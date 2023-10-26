package org.bahadir;

import org.bahadir.repository.entity.Student;

import java.sql.*;


public class Main {
    public static void main(String[] args) {
        Student student = new Student("alex","souza","saopoulo");
        String url = "jdbc:postgresql://localhost:5432/school_Java12";
        String username = "postgres";
        String password = "1234";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Bağlantı başarılı!");

            //String sql = "INSERT INTO student(name,surname,city) VALUES('Mehmet','Yardımcı','İstanbul');";
            //execute(connection,sql);
            //createStudent(connection,new Student("bahadır","ünsal","Kırklareli"));
            //createStudent(connection,student);

            //updateStudent(connection,1);
            //Student student1 = new Student("Ahmet","Yardımcı","İzmir");
            //updateStudent(connection,student1,2);
            //findByStudentCity(connection,"İzmir");
            deleteById(connection,1);
        } catch (SQLException e) {
            System.out.println("Bağlantı hatası" + e);
        }finally {
            try {
                if (connection != null){
                    connection.close();
                    System.out.println("Bağlantı Kapatma işlemi başarılı");
                }
            } catch (SQLException e) {
                System.out.println("Bağlantı kapatma hatası!");
            }
        }
    }
    public static void execute(Connection connection,String sql){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("İşlem başarılı");
        } catch (SQLException e) {
            System.out.println("İşlem başarısız: " + e);
        }
    }
    public static void createStudent(Connection connection, Student student){
        String sql = "INSERT INTO student(name,surname,city) VALUES(?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getSurname());
            preparedStatement.setString(3,student.getCity());

            int basariliSatirSayisi = preparedStatement.executeUpdate();
            if (basariliSatirSayisi > 0){
                System.out.println("Veritabanına kaydedildi.");
            }else {
                System.out.println("Veritanına kaydedilemedi!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void updateStudent(Connection connection,Student student,int id){
        String sql = "UPDATE student SET name=?, surname=?, city=? WHERE id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getSurname());
            preparedStatement.setString(3,student.getCity());
            preparedStatement.setInt(4,id);

            int basariliSatirSayisi = preparedStatement.executeUpdate();
            if (basariliSatirSayisi>0){
                System.out.println("Veritabanında güncelleme işlemi gerçekleşti.");
            }else{
                System.out.println("Güncelleme işlemi başarısız oldu!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void findByStudentCity(Connection connection, String cityName){
        String sql = "SELECT * FROM student WHERE city= ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,cityName);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String city = resultSet.getString("city");

                System.out.println("Name: " + name + " surname: " + surname + " City: " + city);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteById(Connection connection, int id){
        String sql = "DELETE FROM student WHERE id= ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            int i = preparedStatement.executeUpdate();

            if (i>0){
                System.out.println("Silme işlemi gerçekleşti.");
            }else {
                System.out.println("Silme işlemi başarısız!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    public static void updateStudent(Connection connection,Student student){
        String sql = "UPDATE student SET name=?, surname=?, city=? WHERE id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"Mouro");
            preparedStatement.setString(2,"Icardi");
            preparedStatement.setString(3,"Ankara");
            preparedStatement.setInt(4,student.getId());

            int basariliSatirSayisi = preparedStatement.executeUpdate();
            if (basariliSatirSayisi>0){
                System.out.println("Veritabanında güncelleme işlemi gerçekleşti.");
            }else{
                System.out.println("Güncelleme işlemi başarısız oldu!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     */
}