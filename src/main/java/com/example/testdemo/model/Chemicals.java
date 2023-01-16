package com.example.testdemo.model;

import javax.persistence.*;

@Entity
@Table(name="Chemicals")
public class Chemicals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="NameOfChemical")
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public Chemicals(Long id, String name, Long numberOfElements, Double price) {
        this.id = id;
        this.name = name;
        this.numberOfElements = numberOfElements;
        this.price = price;
    }

    @Column(name="AmountOfChemicals")
    private Long numberOfElements;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Long numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name="Price")
    private Double price;

    public Chemicals() {

    }
}
