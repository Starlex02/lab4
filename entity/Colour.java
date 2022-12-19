package org.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Table(name = "colour")
@Entity
public class Colour {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "colour_id")
    private Integer colourId;

    @Column(name = "colour")
    private String colour;

    @Column(name = "firm")
    private String firm;

    @Column(name = "capacity")
    private Integer capacity;

    @ManyToMany(mappedBy = "colours", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Cars> cars = new ArrayList<Cars>();

    public Colour() {

    }

    public Colour(Integer colourId, String colour, String firm, Integer capacity, List<Cars> cars) {
        this.colourId = colourId;
        this.colour = colour;
        this.firm = firm;
        this.capacity = capacity;
        this.cars = cars;
    }

    public Integer getColourId() {
        return colourId;
    }

    public void setColourId(Integer colourId) {
        this.colourId = colourId;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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
        return "Colour{" +
                "colourId=" + colourId +
                ", colour='" + colour + '\'' +
                ", firm='" + firm + '\'' +
                ", capacity=" + capacity +
                ", cars=" + carsName +
                '}';
    }
}
