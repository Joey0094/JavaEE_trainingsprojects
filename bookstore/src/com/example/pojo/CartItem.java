package com.example.pojo;

import java.math.BigDecimal;

public class CartItem {

    private int id;
    private String name;
    private BigDecimal price;
    private int count;
    private BigDecimal totalPrice;

    public CartItem() {
    }

    public CartItem(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = 1;
        setTotalPrice();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getTotalPrice() {
        setTotalPrice();
        return totalPrice;

    }

    public void setTotalPrice() {
        this.totalPrice = this.price.multiply(new BigDecimal(count));
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}
