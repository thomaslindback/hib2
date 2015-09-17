package org.teel.arende2;

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
 * Created by thomas on 2015-06-30.
 */
public class AM {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public static void main(String[] args) {
        new AM().c();
    }

    public Arende getInitialArende() {
        Arende a = new Arende();
        a.setTitle("arende");
        Hand h = new Hand();
        h.setDescription("SKAPAT");
        h.setXid(Long.toString(Cntr.inst().n()));
        a.getHand().add(h);
        return a;
    }

    public void kopplaDok(Arende a, String dokname, long dokid) {
        Hand h = new Hand();
        h.setDescription("NYTT DOK");
        h.setXid(Long.toString(Cntr.inst().n()));

        Dok d = new Dok();
        d.setName(dokname);
        d.setDokId(dokid);
        d.setXid(Long.toString(Cntr.inst().n()));

        // koppla
        a.getDok().add(d);
        a.getHand().add(h);
        h.setDok(d);
        d.getHand().add(h);
    }

    public void kopplaBes(Arende a, String dokname, long dokid) {
        Hand h = new Hand();
        h.setDescription("NYTT BES");
        h.setXid(Long.toString(Cntr.inst().n()));

        Bes b = new Bes();
        b.setName(dokname);
        b.setDokId(dokid);
        b.setXid(Long.toString(Cntr.inst().n()));

        // koppla
        a.getBes().add(b);
        a.getHand().add(h);
        h.setBes(b);
        b.getHand().add(h);
    }

    public void addHandelseToDok(Arende a) {
        Hand h = new Hand();
        h.setDescription("DOK VALIDATED");
        h.setXid(Long.toString(Cntr.inst().n()));
        //h.setRid(Long.toString(System.currentTimeMillis()));
        a.getHand().add(h);
        for (Dok d : a.getDok()) {
            d.getHand().add(h);
            h.setDok(d);
            break;
        }
    }

    public void addHandelseToBes(Arende a) {
        Hand h = new Hand();
        h.setDescription("BES VALIDATED");
        h.setXid(Long.toString(Cntr.inst().n()));
        //h.setRid(Long.toString(System.currentTimeMillis()));
        a.getHand().add(h);
        for (Bes b : a.getBes()) {
            b.getHand().add(h);
            h.setBes(b);
            break;
        }
    }

    public Arende marshalUnmarshal(Arende a) {
        StringWriter sw = new StringWriter();
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Arende.class, Dok.class, Hand.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //jaxbMarshaller.setListener(new org.teel.arlt.circ.CycleListener());
            jaxbMarshaller.marshal(a, System.out);
            jaxbMarshaller.marshal(a, sw);
            //jaxbMarshaller.marshal(new ObjectWrapper(a), sw);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Unmarshaller u = jaxbContext.createUnmarshaller();
            Arende b = (Arende) u.unmarshal(new StringReader(sw.toString()));
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Arende read() {
        entityManager.getTransaction().begin();
        List<Arende> results = entityManager.createQuery("select a from Arende a", Arende.class)
                .getResultList();
        entityManager.getTransaction().commit();
        return results.get(0);
    }

    public void run() {

        Arende a = getInitialArende();
        Arende a2 = marshalUnmarshal(a);

        entityManager.getTransaction().begin();
        entityManager.persist(a2);
        entityManager.getTransaction().commit();

        Arende a3 = read();
        Arende a4 = marshalUnmarshal(a3);

        kopplaDok(a4, "dok1", 8888L);

        Arende a5 = marshalUnmarshal(a4);

        entityManager.getTransaction().begin();
        entityManager.merge(a5);
        entityManager.getTransaction().commit();

        Arende a6 = read();
        System.out.println(a6);
        Arende a7 = marshalUnmarshal(a6);
        System.out.println(a7);

        kopplaDok(a7, "dok2", 9999L);
        Arende a8 = marshalUnmarshal(a7);
        entityManager.getTransaction().begin();
        entityManager.merge(a8);
        entityManager.getTransaction().commit();
        Arende a9 = read();
        System.out.println(a9);
        Arende a10 = marshalUnmarshal(a9);

        addHandelseToDok(a10);
        Arende a11 = marshalUnmarshal(a10);
        entityManager.getTransaction().begin();
        entityManager.merge(a11);
        entityManager.getTransaction().commit();
        Arende a12 = read();
        System.out.println(a12);

        Arende a13 = marshalUnmarshal(a12);

        kopplaBes(a13, "bes1", 33322L);

        Arende a14 = marshalUnmarshal(a13);

        entityManager.getTransaction().begin();
        entityManager.merge(a14);
        entityManager.getTransaction().commit();

        Arende a15 = read();
        System.out.println(a15);
    }


    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Arende2");
        entityManager = entityManagerFactory.createEntityManager();

    }

    public void c() {
        try {
            init();
            run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }

    static class Cntr {
        private static final Cntr i = new Cntr();
        private static int n = 400;

        public static Cntr inst() {
            return i;
        }

        public int n() {
            n++;
            return n;
        }
    }


}
