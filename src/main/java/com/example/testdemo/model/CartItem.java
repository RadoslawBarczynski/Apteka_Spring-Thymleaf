package com.example.testdemo.model;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name="CartItems")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "chemicals_id")
    private Chemicals chemicals;

    public void setId(Long id) {
        this.id = id;
    }

    public CartItem(Chemicals chemicals) {
        this.chemicals = chemicals;
    }



    public CartItem() {

    }


    public Chemicals getChemicals() {
        return chemicals;
    }

    public void setChemicals(Chemicals chemicals) {
        this.chemicals = chemicals;
    }

    public Long getId() {
        return id;
    }





}
