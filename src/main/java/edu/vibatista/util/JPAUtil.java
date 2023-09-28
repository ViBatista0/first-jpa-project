package edu.vibatista.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("jpa-project");

    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }
}
