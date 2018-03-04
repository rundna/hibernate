package com.kodilla.hibernate.invoice;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Products")
public class Product {
    private int id;
    private String name;
    private List<Item> itemList = new ArrayList<>();


    public Product(String name) {
        this.name = name;
    }
    public Product(){}

    @Id
    @NotNull
    @GeneratedValue
    @Column(name="ID",unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name="PRODUCT_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @OneToMany(targetEntity = Item.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "product")
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> item) {
        this.itemList = itemList;
    }
}
