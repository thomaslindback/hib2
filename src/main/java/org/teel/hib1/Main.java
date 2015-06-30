package org.teel.hib1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

/**
 * Hello world!
 */
public class Main {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public static void main(String[] args) {
        new Main().c();
    }

    public void start() {
        entityManagerFactory = Persistence.createEntityManagerFactory("BookManagement");
        entityManager = entityManagerFactory.createEntityManager();

    }

    public void run() {
        System.out.println("Hello World!");
        start();
        entityManager.getTransaction().begin();
        Book book = new Book("Book1");

        Handelse hand = new Handelse();
        hand.setDescription("A handelse");

        Handelse handelse2 = new Handelse();
        handelse2.setDescription("Handelse 3");

        Autor author1 = new Autor("Thomas", book);
        author1.getHandelser().add(hand);
        author1.getHandelser().add(handelse2);
        handelse2.setAuthor(author1);
        book.getAutors().add(author1);
        book.getAutors().add(new Autor("Khanitta", book));

        Plot p1 = new Plot("plot1");
        book.setPlot(p1);

        book.getHandelser().add(hand);
        //book.getHandelser().add(handelse2);
        hand.setAuthor(author1);
        hand.setBook(book);
        //handelse2.setBook(book);

        entityManager.flush();
        entityManager.persist(book);
        p1.setBook(book);
        entityManager.persist(p1);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }


    public void run2() {
        entityManager.getTransaction().begin();
        List<Book> results = entityManager.createQuery("select b from Book b", Book.class)
                .getResultList();
        Book b1 = results.get(0);
        for (Book b : results) {
            entityManager.detach(b);
            System.out.println(b);
            System.out.println(b.getTitle());
            for (Autor a : b.getAutors()) {
                System.out.println("\t" + a.getName());
                System.out.println("\t" + a.getBook().getTitle());
            }
            for (Handelse h : b.getHandelser()) {
                System.out.println("\t" + h.getDescription());
                System.out.println("\t" + h.getAuthor().getName());
                System.out.println("\t" + h.getBook().getTitle());
            }
        }

        List<Handelse> res = entityManager.createQuery("select h from Handelse h", Handelse.class).getResultList();
        for (Handelse h : res) {
            System.out.println(h);
            System.out.println(h.getAuthor().getName());
        }
        StringWriter sw = new StringWriter();
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Book.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(results.get(0), System.out);
            jaxbMarshaller.marshal(results.get(0), sw);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // unmarsal
        try {
            Unmarshaller u = jaxbContext.createUnmarshaller();
            Book b = (Book) u.unmarshal(new StringReader(sw.toString()));
            System.out.println(b);
            System.out.println(b.getTitle());
            System.out.println();
            for (Autor a : b.getAutors()) {
                System.out.println("\t" + a.getName());
                System.out.println("\t" + a.getBook().getTitle());
                for (Handelse h : a.getHandelser()) {
                    System.out.println(h);
                }
            }
            System.out.println();
            for (Handelse h : b.getHandelser()) {
                System.out.println("\t" + h.getDescription());
                System.out.println("\t" + h.getAuthor().getName());
                System.out.println("\t" + h.getBook().getTitle());
            }
            //System.out.println("\t" + d_um.getEmployees().get(0).getDepartment().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        entityManager.getTransaction().commit();
    }

    public void c() {
        try {
            run();
            run2();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }
}
