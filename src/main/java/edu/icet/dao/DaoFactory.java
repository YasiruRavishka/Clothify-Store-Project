package edu.icet.dao;

import edu.icet.dao.custom.impl.EmployeeDaoImpl;
import edu.icet.dao.custom.impl.ProductDaoImpl;
import edu.icet.dao.custom.impl.SupplierDaoImpl;
import edu.icet.util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}

    public static DaoFactory getInstance() {
        return null==instance?instance=new DaoFactory():instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType type){
        switch (type){
            case EMPLOYEE:return (T) new EmployeeDaoImpl();
            case SUPPLIER:return (T) new SupplierDaoImpl();
            case PRODUCT:return (T) new ProductDaoImpl();
            default:
                throw new IllegalStateException("DaoFactory --> Unexpected value: " + type);
        }
    }
}
