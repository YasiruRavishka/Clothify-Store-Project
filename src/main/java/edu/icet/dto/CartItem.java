package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CartItem {
    private Integer itemCode;
    private String name;
    private Double unitPrice;
    private Integer qty;
    private Double total;
}
