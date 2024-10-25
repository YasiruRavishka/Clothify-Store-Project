package edu.icet.service.custom.impl;

import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.ProductDao;
import edu.icet.dto.Product;
import edu.icet.entity.ProductEntity;
import edu.icet.service.custom.ProductBo;
import edu.icet.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class ProductBoImpl implements ProductBo {
    @Override
    public ObservableList<Product> getAll() {
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
        ObservableList<Product> products = FXCollections.observableArrayList();
        for (ProductEntity productEntity : productDao.getAll()) {
            products.add(new ModelMapper().map(productEntity, Product.class));
        }
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        ProductEntity entity = new ModelMapper().map(product, ProductEntity.class);
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
        return productDao.save(entity);
    }

    @Override
    public boolean updateProduct(Product product) {
        ProductEntity entity = new ModelMapper().map(product, ProductEntity.class);
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
        return productDao.update(entity);
    }

    @Override
    public boolean deleteProduct(Integer id) {
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
        return productDao.delete(id);
    }

    @Override
    public Product searchProductById(Integer id) {
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
        return new ModelMapper().map(productDao.searchById(id), Product.class);
    }

    @Override
    public ObservableList<Product> searchProductByName(String name) {
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
        ObservableList<Product> products = FXCollections.observableArrayList();
        for (ProductEntity productEntity : productDao.searchByName(name)) {
            products.add(new ModelMapper().map(productEntity, Product.class));
        }
        return products;
    }
}
