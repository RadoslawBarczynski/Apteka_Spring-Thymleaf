package com.example.testdemo.model;

import javax.persistence.*;

@Entity
@Table(name="Receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Receipt_Code")
    private String receiptCode;

    public Receipt(Long id, String receiptCode, Chemicals chemical) {
        this.id = id;
        this.receiptCode = receiptCode;
        this.chemical = chemical;
    }

    public Receipt() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(String receiptCode) {
        this.receiptCode = receiptCode;
    }

    @ManyToOne
    @JoinColumn(name = "chemical_id")
    private Chemicals chemical;

    public Chemicals getChemical() {
        return chemical;
    }

    public void setChemical(Chemicals chemical) {
        this.chemical = chemical;
    }
}
