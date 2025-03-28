package com.aozelce.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * The type Session factory provider.
 *
 *  @author aozelce
 */
public class SessionFactoryProvider {

    private static SessionFactory sessionFactory;

    private SessionFactoryProvider() {
    }

    /**
     * Gets session factory.
     *
     * @return the session factory
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;
    }

    private static void createSessionFactory() {
        try {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml") // your hibernate.cfg.xml file name
                    .build();

            Metadata metadata = new MetadataSources(standardRegistry)
                    .getMetadataBuilder()
                    .build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();

        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}