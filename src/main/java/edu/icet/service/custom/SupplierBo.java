package edu.icet.service.custom;

import edu.icet.dto.Supplier;
import edu.icet.service.SuperBo;
import javafx.collections.ObservableList;

public interface SupplierBo extends SuperBo {
    ObservableList<Supplier> getAll();
    boolean addSupplier(Supplier supplier);
    boolean updateSupplier(Supplier supplier);
    boolean deleteSupplier(Integer id);
    Supplier searchSupplierById(Integer id);
}
