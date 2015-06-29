package org.teel.hib1;

import org.codehaus.jackson.map.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
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

        Autor author1 = new Autor("Thomas", book);
        author1.getHandelser().add(hand);
        book.getAutors().add(author1);
        book.getAutors().add(new Autor("Khanitta", book));

        Plot p1 = new Plot("plot1");
        book.setPlot(p1);

        book.getHandelser().add(hand);
        hand.setAuthor(author1);
        hand.setBook(book);

        entityManager.flush();
        entityManager.persist(book);
        p1.setBook(book);
        entityManager.persist(p1);

        entityManager.getTransaction().commit();
    }

    public void pp(Object o) {
        ObjectMapper objMapper = new ObjectMapper();

        try {
            String jsonString = objMapper.writeValueAsString(o);
            System.out.println(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run2() {
        entityManager.getTransaction().begin();
        List<Book> results = entityManager.createQuery("select b from Book b join b.handelser h join b.autors a", Book.class)
                .getResultList();
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
            /*u.setListener(new Unmarshaller.Listener() {
                @Override
                public void beforeUnmarshal(Object target, Object parent) {
                    super.beforeUnmarshal(target, parent);
                }

                @Override
                public void afterUnmarshal(Object target, Object parent) {
                    if(target instanceof  Handelse) {
                        if(parent instanceof Book) {
                            ((Handelse)target).setBook((Book)parent);
                        }
                        if(parent instanceof Autor) {
                            ((Handelse)target).setAuthor((Autor)parent);
                        }
                    }
                    //super.afterUnmarshal(target, parent);
                }
            });*/
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
