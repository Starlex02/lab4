package org.example.entity;


import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Table(name = "cars")
@Entity
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cars_id")
    private Integer carsId;

    @Column(name = "price")
    private Integer price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "Cars_Colour",
            joinColumns = @JoinColumn(name = "cars_id"),
            inverseJoinColumns = @JoinColumn(name = "colours_id"))
    private List<Colour> colours = new ArrayList<Colour>();

    public Cars() {

    }

    public Cars(Integer carsId, Integer price, Brand brand, String model, Integer year, List<Colour> colours) {
        this.carsId = carsId;
        this.price = price;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.colours = colours;
    }

    public Integer getCarsId() {
        return carsId;
    }

    public void setCarsId(Integer carsId) {
        this.carsId = carsId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Colour> getColour() {
        return colours;
    }

    public void setColour(List<Colour> colours) {
        this.colours = colours;
    }

    @Override
    public String toString() {
        String colourName = "";
        for(Colour colour:colours){
            colourName += colour.getColour() + ", ";
        }
        return "Cars{" +
                "carsId=" + carsId +
                ", price=" + price +
                ", brand=" + brand.getBrand() +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", disc=" + colourName +
                '}';
    }
}
