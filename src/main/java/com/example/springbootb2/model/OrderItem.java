package com.example.springbootb2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "orders_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @EmbeddedId
    private OrderItemKey id;
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private Order order;
    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;
    private int quantity;
    private double subtotal;
}