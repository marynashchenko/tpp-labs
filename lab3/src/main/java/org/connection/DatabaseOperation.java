package org.connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

import static org.util.HibernateUtil.getEntityManagerFactory;

public class DatabaseOperation {


    public static void insertItem(Object entity) {
        EntityManager em = getEntityManagerFactory().createEntityManager();

        try {
            // Start a transaction
            em.getTransaction().begin();

            // Persist the object
            em.persist(entity);

            // Commit the transaction
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            // Close the EntityManager
            em.close();
        }
    }

    public static void updateItem(Object entity) {
        EntityManager em = getEntityManagerFactory().createEntityManager();

        try {
            // Start a transaction
            em.getTransaction().begin();

            // Update the object
            em.merge(entity);

            // Commit the transaction
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            // Close the EntityManager
            em.close();
        }
    }

    public static void deleteItem(Object entity) {
        EntityManager em = getEntityManagerFactory().createEntityManager();

        try {
            // Start a transaction
            em.getTransaction().begin();

            // Merge the entity to ensure it's managed
            entity = em.merge(entity);

            // Delete the entity
            em.remove(entity);

            // Commit the transaction
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            // Close the EntityManager
            em.close();
        }
    }

    public static void readAllItems(String tableName) {

        EntityManager em = getEntityManagerFactory().createEntityManager();

        try {
            // Start a transaction
            em.getTransaction().begin();

            // Create a query to select all entities from the given table
            Query query = em.createQuery("SELECT e FROM " + tableName + " e");

            // Execute the query and get the result list
            List<?> resultList = query.getResultList();

            // Print the result
            for (Object entity : resultList) {
                System.out.println(entity.toString());
            }

            // Commit the transaction
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            // Close the EntityManager
            em.close();
        }
    }

}
