package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name="Orderdetails")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer price;
    Integer quantity;
    @ManyToOne @JoinColumn(name="Productid")
    Product product;

    @ManyToOne @JoinColumn(name="Orderid")
    Order order;
}
