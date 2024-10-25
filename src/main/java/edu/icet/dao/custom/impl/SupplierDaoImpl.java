package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.SupplierDao;
import edu.icet.entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity supplierEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.persist(supplierEntity);
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
            session.remove(session.get(SupplierEntity.class, id));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ObservableList<SupplierEntity> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            List<SupplierEntity> entityList = session.createQuery("FROM SupplierEntity", SupplierEntity.class).getResultList();
            session.getTransaction().commit();
            return FXCollections.observableList(entityList);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(SupplierEntity supplierEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.merge(supplierEntity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public SupplierEntity search(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            SupplierEntity entity = session.get(SupplierEntity.class, id);
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
                    .addAnnotatedClass(SupplierEntity.class)
                    .getMetadataBuilder()
                    .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                    .build();
            return metadata.getSessionFactoryBuilder().build();
        }

        private static Session getSession(){
            return session.openSession();
        }
    }
}
