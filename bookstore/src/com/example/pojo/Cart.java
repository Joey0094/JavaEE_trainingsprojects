package com.example.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    private int totalCount;
    private BigDecimal totalPrice;
    private Map<Integer, CartItem> cartItems = new LinkedHashMap<>();

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> cartItems) {
        setTotalCount();
        setTotalPrice();
        this.cartItems = cartItems;
    }

    public int getTotalCount() {
        setTotalCount();
        return totalCount;
    }

    public void setTotalCount() {
        this.totalCount = 0;
        for(CartItem i : cartItems.values()) {
            this.totalCount += i.getCount();
        }
    }

    public BigDecimal getTotalPrice() {
        setTotalPrice();
        return totalPrice;
    }

    public void setTotalPrice() {
        // BigDecimal 不赋值默认为 null，不能调用方法
        this.totalPrice = new BigDecimal(0);
        for(CartItem i : cartItems.values()) {
            this.totalPrice = this.totalPrice.add(i.getTotalPrice());
        }
    }

    public Map<Integer, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<Integer, CartItem> cartItems) {
        this.cartItems = cartItems;
    }


    public void addItem(Book book) {
        CartItem item = cartItems.get(book.getId());
        if(item == null) {
            cartItems.put(book.getId(), new CartItem(book.getId(), book.getName(), book.getPrice()));
        } else {
            item.setCount(item.getCount() + 1);
        }
    }

    public void deleteItem(int id) {
        cartItems.remove(id);
    }

    public void clear() {
        cartItems.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", cartItems=" + cartItems +
                '}';
    }
}
