package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.ProductDao;
import edu.icet.entity.ProductEntity;
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

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(ProductEntity productEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.persist(productEntity);
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
            session.remove(session.get(ProductEntity.class, id));
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ObservableList<ProductEntity> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            List<ProductEntity> entityList = session.createQuery("FROM ProductEntity", ProductEntity.class).getResultList();
            session.getTransaction().commit();
            return FXCollections.observableList(entityList);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(ProductEntity productEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.merge(productEntity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ProductEntity search(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            ProductEntity entity = session.get(ProductEntity.class, id);
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
                    .addAnnotatedClass(ProductEntity.class)
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
