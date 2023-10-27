package com.bahadir.repository;

import com.bahadir.entity.Car;
import com.bahadir.util.DbConnection;

import javax.xml.transform.Result;
import java.awt.dnd.DnDConstants;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarRepository implements ICrud<Car>{


    @Override
    public void saveAll(List<Car> t) {
        if (databaseControl()){
            System.out.println("Zaten db'ye verileri attınız.");
        }else{
            String query = "INSERT INTO cars(brand,car_model,model_year,dealer_id) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
                for (Car car : t) {
                    preparedStatement.setString(1, car.getBrand());
                    preparedStatement.setString(2,car.getCar_model());
                    preparedStatement.setString(3,car.getModel_year());
                    preparedStatement.setInt(4,car.getDealer_id());
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    private boolean databaseControl(){
        boolean control = false;

        String sql = "SELECT * FROM cars";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            control = resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return control;
    }
}
