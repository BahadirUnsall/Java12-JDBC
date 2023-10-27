package com.bahadir.entity;

public class Car {
    private int id;
    private String brand;
    private String car_model;
    private String model_year;
    private int dealer_id;

    public Car() {
    }

    public Car(int id, String brand, String car_model, String model_year, int dealer_id) {
        this.id = id;
        this.brand = brand;
        this.car_model = car_model;
        this.model_year = model_year;
        this.dealer_id = dealer_id;
    }

    public Car(String brand, String car_model, String model_year, int dealer_id) {
        this.brand = brand;
        this.car_model = car_model;
        this.model_year = model_year;
        this.dealer_id = dealer_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public String getModel_year() {
        return model_year;
    }

    public void setModel_year(String model_year) {
        this.model_year = model_year;
    }

    public int getDealer_id() {
        return dealer_id;
    }

    public void setDealer_id(int dealer_id) {
        this.dealer_id = dealer_id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", car_model='" + car_model + '\'' +
                ", model_year='" + model_year + '\'' +
                ", dealer_id=" + dealer_id +
                '}';
    }
}
