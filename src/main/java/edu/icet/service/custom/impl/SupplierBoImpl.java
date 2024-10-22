package edu.icet.service.custom.impl;

import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.SupplierDao;
import edu.icet.dto.Supplier;
import edu.icet.entity.SupplierEntity;
import edu.icet.service.custom.SupplierBo;
import edu.icet.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class SupplierBoImpl implements SupplierBo {
    @Override
    public ObservableList<Supplier> getAll() {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        for (SupplierEntity supplierEntity : supplierDao.getAll()) {
            suppliers.add(new ModelMapper().map(supplierEntity, Supplier.class));
        }
        return suppliers;
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        return supplierDao.save(entity);
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        return supplierDao.update(entity);
    }

    @Override
    public boolean deleteSupplier(Integer id) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        return supplierDao.delete(id);
    }

    @Override
    public Supplier searchSupplierById(Integer id) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        return new ModelMapper().map(supplierDao.search(id), Supplier.class);
    }
}
