package com.deepakrohan.contactmgr;

import com.deepakrohan.contactmgr.model.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by rohan on 7/1/16.
 */
public class Application {


    public static final SessionFactory sessionFactory = getSessionFactory();

    private static SessionFactory getSessionFactory() {

        ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        Contact contact = new Contact.ContactBuilder("deepak","rohan")
                .addEmail("deepakrohan@hotmail.com")
                .build();

//        Create a Session
        Session session = sessionFactory.openSession();

//        Begin Transaction
        session.beginTransaction();

//        Use the session to save contact
        session.save(contact);

//        Commit transaction
        session.getTransaction().commit();

//        Close transaction
        session.close();
//        System.out.println(contact);

    }

}
