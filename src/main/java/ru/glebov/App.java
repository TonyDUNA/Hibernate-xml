package ru.glebov;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.glebov.entity.Event;

import java.util.Date;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        SessionFactory sessionFactory = null;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder(). // регистратор хибернейт
                configure()
                .build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry); // выкл session factory
        }

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(new Event("Screencast about Hibernate", new Date()));
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Event").list();
        for(Event event : (List<Event>)result) {
            System.out.println("Event (" + event.getDate() + ") : " + event.getTitle());
    }
        session.getTransaction().commit();
        session.close();

        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
