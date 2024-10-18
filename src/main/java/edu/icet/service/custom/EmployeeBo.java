package edu.icet.service.custom;

import edu.icet.dto.Employee;
import edu.icet.service.SuperBo;
import javafx.collections.ObservableList;

public interface EmployeeBo extends SuperBo {
    ObservableList<Employee> getAll();
    boolean addEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(Integer id);
    Employee searchEmployeeById(Integer id);
}
