package edu.icet.service.custom;

import edu.icet.dto.Product;
import edu.icet.service.SuperBo;
import javafx.collections.ObservableList;

public interface ProductBo extends SuperBo {
    ObservableList<Product> getAll();
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(Integer id);
    Product searchProductById(Integer id);
    ObservableList<Product> searchProductByName(String name);
}
