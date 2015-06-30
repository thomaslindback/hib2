package org.teel.arende;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by thomas on 2015-06-30.
 */
public class AM {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public static void main(String[] args) {
        new AM().c();
    }

    public void run() {
        Arende a = new Arende();
        a.setTitle("a");

        Hand h = new Hand();
        h.setDescription("h");

        Dok d = new Dok();
        d.setName("d");

        // koppla
        a.getDok().add(d);
        a.getHand().add(h);
        h.setArende(a);
        d.setArende(a);
        h.setDok(d);
        d.getHand().add(h);

        //entityManager.getTransaction().begin();
        //entityManager.persist(a);
        //entityManager.getTransaction().commit();

        StringWriter sw = new StringWriter();
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Arende.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(a, System.out);
            jaxbMarshaller.marshal(a, sw);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Unmarshaller u = jaxbContext.createUnmarshaller();
            Arende b = (Arende) u.unmarshal(new StringReader(sw.toString()));

            for (Dok dok : b.getDok()) {
                dok.setArende(b);
                dok.getHand().clear();
                dok.getHand().addAll(b.getHand());
            }
            for (Hand hand : b.getHand()) {
                hand.setArende(b);
                hand.setDok(b.getDok().get(0));
            }

            entityManager.getTransaction().begin();
            entityManager.persist(b);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void run2() {

    }

    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Arende");
        entityManager = entityManagerFactory.createEntityManager();

    }

    public void c() {
        try {
            init();
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
