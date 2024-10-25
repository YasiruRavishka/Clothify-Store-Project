package edu.icet.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import edu.icet.dao.custom.EmployeeDao;
import edu.icet.entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
    public EmployeeEntity search(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            EmployeeEntity entity = session.get(EmployeeEntity.class, id);
            session.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            return null;
        }
    }

    private static class HibernateUtil {
        private static final SessionFactory session = createSession();
        private static SessionFactory createSession() {
            StandardServiceRegistry build = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();

            Metadata metadata = new MetadataSources(build)
                    .addAnnotatedClass(EmployeeEntity.class)
                    .getMetadataBuilder()
                    .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                    .build();
            return metadata.getSessionFactoryBuilder().build();
        }

        public static Session getSession(){
            return session.openSession();
        }
    }
}
