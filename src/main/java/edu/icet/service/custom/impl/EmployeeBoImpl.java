package edu.icet.service.custom.impl;

import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.EmployeeDao;
import edu.icet.dto.Employee;
import edu.icet.entity.EmployeeEntity;
import edu.icet.service.custom.EmployeeBo;
import edu.icet.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class EmployeeBoImpl implements EmployeeBo {
    @Override
    public ObservableList<Employee> getAll() {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        for (EmployeeEntity employeeEntity : employeeDao.getAll()) {
            employees.add(new ModelMapper().map(employeeEntity, Employee.class));
        }
        return employees;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        EmployeeEntity entity = new ModelMapper().map(employee, EmployeeEntity.class);
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        return employeeDao.save(entity);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        EmployeeEntity entity = new ModelMapper().map(employee, EmployeeEntity.class);
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        return employeeDao.update(entity);
    }

    @Override
    public boolean deleteEmployee(Integer id) {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        return employeeDao.delete(id);
    }

    @Override
    public Employee searchEmployeeById(Integer id) {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        return new ModelMapper().map(employeeDao.search(id), Employee.class);
    }
}
