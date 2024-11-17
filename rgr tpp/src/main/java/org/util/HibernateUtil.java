package org.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class HibernateUtil {

    private static EntityManagerFactory emFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emFactory == null) {
            emFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        }
        return emFactory;
    }

    public static void closeEntityManagerFactory() {
        if (emFactory != null) {
            emFactory.close();
        }
    }

    public static void init() {
        getEntityManagerFactory();
    }
}