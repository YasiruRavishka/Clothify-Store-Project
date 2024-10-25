package edu.icet.entity;

import edu.icet.util.ProductSize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private ProductSize size;
    @Column(nullable = false)
    private Double unitPrice;
    @Column(nullable = false)
    private Integer qtyOnHand;
    private String imgSrc;
}
