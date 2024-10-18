package edu.icet.service;

import edu.icet.service.custom.impl.EmployeeBoImpl;
import edu.icet.service.custom.impl.SupplierBoImpl;
import edu.icet.util.ServiceType;

public class BoFactory {
    private static BoFactory instance;
    private BoFactory(){}

    public static BoFactory getInstance() {
        return null==instance?instance=new BoFactory():instance;
    }

    public <T extends SuperBo>T getServiceType(ServiceType type){
        switch (type){
            case EMPLOYEE:return (T) new EmployeeBoImpl();
            case SUPPLIER:return (T) new SupplierBoImpl();
        }
        return null;
    }
}
