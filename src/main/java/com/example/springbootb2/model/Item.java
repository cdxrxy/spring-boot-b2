package com.example.springbootb2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private double price;
    private String name;
    private String manufacturer;
    private String description;
    private String type;
    private Date createdDateTime;
    private Date modifiedDateTime;
    private boolean isActive;
}