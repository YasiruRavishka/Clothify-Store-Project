package edu.icet.dao.custom.impl;

import edu.icet.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import edu.icet.dao.custom.EmployeeDao;
import edu.icet.entity.EmployeeEntity;
import org.hibernate.Session;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity employeeEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.persist(employeeEntity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.remove(session.get(EmployeeEntity.class, id));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ObservableList<EmployeeEntity> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            List<EmployeeEntity> entityList = session.createQuery("FROM EmployeeEntity", EmployeeEntity.class).getResultList();
            session.getTransaction().commit();
            return FXCollections.observableList(entityList);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(EmployeeEntity employeeEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.merge(employeeEntity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public EmployeeEntity searchById(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            EmployeeEntity entity = session.get(EmployeeEntity.class, id);
            session.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ObservableList<EmployeeEntity> searchByName(String name) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            List<EmployeeEntity> entityList = session.createQuery("FROM EmployeeEntity WHERE name='"+name+"'",EmployeeEntity.class).getResultList();
            session.getTransaction().commit();
            return FXCollections.observableList(entityList);
        } catch (Exception e) {
            return null;
        }
    }
}
