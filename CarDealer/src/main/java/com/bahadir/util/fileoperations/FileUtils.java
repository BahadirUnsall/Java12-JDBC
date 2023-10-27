package com.bahadir.util.fileoperations;

import com.bahadir.entity.Car;
import com.bahadir.util.constant.JdbcConstant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<String> readFile(String path){
        List<String> data = new ArrayList();

        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String satir;

            while((satir = reader.readLine()) != null){
                data.add(satir);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
    public static List<Car> createEntities(List<String> lineList){
        List<Car> carList = new ArrayList<>();

        for (String line : lineList) {
            String[] wordArray = line.split(",");
            int id = Integer.parseInt(wordArray[0]);
            String brand = wordArray[1];
            String car_model = wordArray[2];
            String model_year = wordArray[3];
            int dealer_id = Integer.parseInt(wordArray[4]);
            carList.add(new Car(id,brand,car_model,model_year,dealer_id));
        }
        return carList;
    }

    public static void main(String[] args) {
        createEntities(readFile(JdbcConstant.CAR_FILE)).forEach(System.out::println);
    }
    /*
    List<T> carList = new ArrayList<>();

        for (String line : lineList) {
            String[] wordArray = line.split(",");
            int id = Integer.parseInt(wordArray[0]);
            String brand = wordArray[1];
            String car_model = wordArray[2];
            String model_year = wordArray[3];
            int dealer_id = Integer.parseInt(wordArray[4]);
            try {
                T car = T.class.getDeclaredConstructor().newInstance(); // boş nesne
                for (Method method : T.class.getMethods()) {
                    if (method.getName().contains("set")){
                        switch (method.getGenericParameterTypes()[0].getTypeName()){
                            case "int":
                                method.invoke(car,id);
                                break;
                            case "java.util.String":
                                method.invoke(car,brand);
                                break;
                        }

                    }
                }
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            carList.add(new Car(id,brand,car_model,model_year,dealer_id));
        }
        return carList;
     */
}
