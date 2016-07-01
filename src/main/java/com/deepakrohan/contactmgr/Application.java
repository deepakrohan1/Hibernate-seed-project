package com.deepakrohan.contactmgr;

import com.deepakrohan.contactmgr.model.Contact;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

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
        int id = save(contact);
        System.out.println("Before Update");
        for (Contact c: fetchAll() ) {
            System.out.println(c);
        }

        Contact contact1 = getContact(id);
        contact1.setLastName("sekar");

        updateContact(contact1);
        System.out.println("After Update");
        for (Contact c: fetchAll() ) {
            System.out.println(c);
        }

        System.out.println("Aftef Deletion");
        Contact contact2 = getContact(id);
        deleteContact(contact2);

        for (Contact c: fetchAll() ) {
            System.out.println(c);
        }

    }

    private static void deleteContact(Contact c){
//        open session
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.delete(c);

        session.getTransaction().commit();

        session.close();
    }

    private static void updateContact(Contact c){
//        open session
        Session session = sessionFactory.openSession();
//        create transaction
        session.beginTransaction();
//        make update
        session.update(c);
//        commit transaction
        session.getTransaction().commit();
//        close session
        session.close();

    }


    private static Contact getContact(int id){
//        open session
        Session session = sessionFactory.openSession();
//        set criteria
        Contact contact = session.get(Contact.class,id);
//        close session
        session.close();

        return  contact;
    }


@SuppressWarnings("unchecked")
    private static List<Contact> fetchAll(){
//        open session
        Session session = sessionFactory.openSession();
//        Set criteria to fetch data
        Criteria criteria = session.createCriteria(Contact.class);
        List<Contact> contacts = criteria.list();
//      Close session
        session.close();

        return contacts;
    }

    private static int save(Contact contact){

//        Create a Session
        Session session = sessionFactory.openSession();

//        Begin Transaction
        session.beginTransaction();

//        Use the session to save contact
        int id = (int)session.save(contact);

//        Commit transaction
        session.getTransaction().commit();

//        Close transaction
        session.close();
//        System.out.println(contact);
        return id;
    }

}
