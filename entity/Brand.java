package org.example.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Table(name = "brands")
@Entity
public class Brand {

    @Id
    @Column(name = "brand")
    private String brand;

    @Column(name = "year")
    private Integer year;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    private List<Cars> cars = new ArrayList<Cars>();

    public Brand() {

    }

    public Brand(String brand, Integer year, List<Cars> cars) {
        this.brand = brand;
        this.year = year;
        this.cars = cars;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Cars> getCars() {
        return cars;
    }

    public void setCars(List<Cars> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        String carsName = "";
        for(Cars car:cars){
            carsName += car.getModel() + ", ";
        }
        return "Brand{" +
                "brand='" + brand + '\'' +
                ", year=" + year +
                ", cars=" + carsName +
                '}';
    }
}
