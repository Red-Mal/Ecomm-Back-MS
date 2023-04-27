package org.sid.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.orderservice.model.Product;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private double price;
    private int quantity;
    private double discount;
    //Supp et ajouter a DTO
    @Transient
    private Product product;
    @ManyToOne
    //supp et ajouter les DTO
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Order order;


}
