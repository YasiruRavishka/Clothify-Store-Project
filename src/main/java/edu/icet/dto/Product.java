package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import edu.icet.util.ProductSize;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private Integer itemCode;
    private String name;
    private Enum<ProductSize> size;
    private Double unitPrice;
    private Integer qtyOnHand;
    private String imgSrc;
}
