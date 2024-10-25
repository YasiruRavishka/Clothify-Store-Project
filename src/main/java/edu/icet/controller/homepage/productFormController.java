package edu.icet.controller.homepage;

import com.jfoenix.controls.JFXButton;
import edu.icet.dto.CartItem;
import edu.icet.dto.Product;
import edu.icet.util.ProductSize;
import edu.icet.util.TempCart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class productFormController {
    private Product product;

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private Label lblProductName;

    @FXML
    private Label lblProductPrice;

    @FXML
    private Label lblProductSize;

    @FXML
    private Label lblStockCount;

    @FXML
    private ImageView productImg;

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        if (product.getQtyOnHand() < 1) {
            return;
        }
        product.setQtyOnHand(product.getQtyOnHand() - 1);
        lblStockCount.setText(String.format("%3d", product.getQtyOnHand()));
        CartItem cartItem = TempCart.popItemById(product.getId());
        if (cartItem == null){
            if (product.getSize() == ProductSize.NONE){
                TempCart.addToCart(
                        new CartItem(product.getId(), product.getName(), product.getUnitPrice(), 1, product.getUnitPrice())
                );
            } else {
                TempCart.addToCart(
                        new CartItem(product.getId(), product.getName() + " : " + product.getSize(), product.getUnitPrice(), 1, product.getUnitPrice())
                );
            }
        } else {
            cartItem.setQty(cartItem.getQty()+1);
            cartItem.setTotal(cartItem.getTotal() + cartItem.getUnitPrice());
            TempCart.addToCart(cartItem);
        }
        if (product.getQtyOnHand() < 1) {
            btnAddToCart.setDisable(true);
        }
    }

    public void setData(Product newProduct) {
        product = newProduct;
        productImg.setImage(new Image(product.getImgSrc()));
        lblProductName.setText(product.getName());
        lblProductPrice.setText(String.format("%.2f", product.getUnitPrice()));
        lblProductSize.setText(product.getSize().toString());
        lblStockCount.setText(String.format("%3d", product.getQtyOnHand()));
    }
}
