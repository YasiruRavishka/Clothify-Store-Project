package edu.icet.dao;

import javafx.collections.ObservableList;

public interface CrudDao<T> extends SuperDao {
    boolean save(T t);
    boolean delete(Integer id);
    ObservableList<T> getAll();
    boolean update(T t);
    T searchById(Integer id);
}
