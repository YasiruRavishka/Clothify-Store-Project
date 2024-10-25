package edu.icet.util;

import edu.icet.dto.CartItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.ToString;

@ToString
public class TempCart {
    @Getter
    private static ObservableList<CartItem> cartList = FXCollections.observableArrayList();

    public static void addToCart(CartItem cartItem){
        cartList.add(cartItem);
    }

    public static CartItem popItemById(Integer id){
        for (CartItem cartItem : cartList) {
            if (cartItem.getItemCode().equals(id)){
                cartList.remove(cartItem);
                return cartItem;
            }
        }
        return null;
    }
}
