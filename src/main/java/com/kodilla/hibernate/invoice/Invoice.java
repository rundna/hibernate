package com.kodilla.hibernate.invoice;

import org.hibernate.annotations.Tables;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="INVOICES")
public class Invoice {
    private int id;
    private String number;
    private List<Item> itemList = new ArrayList<>();

    public Invoice(String number) {
        this.number = number;
    }
    public Invoice(){}

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="INVOICE_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name="INVOICE_NO")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    @OneToMany(targetEntity = Item.class,
                mappedBy = "invoice",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    public List<Item> getItemsList() {
        return itemList;
    }

    public void setItemsList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
