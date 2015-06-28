package org.teel.hib1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class Main {
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	public void start() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "BookManagement" );
		entityManager = entityManagerFactory.createEntityManager();
				
	}
	public void run() {
		System.out.println("Hello World!");
		start();
		entityManager.getTransaction().begin();
		Book b1 = new Book("Book1");
		
		Handelse hand = new Handelse();
		hand.setDescription("A handelse");
		Autor author1 = new Autor("Thomas", b1);
		author1.getHandelser().add(hand);
		b1.getAutors().add(author1);
		b1.getAutors().add(new Autor("Khanitta", b1));
		Plot p1 = new Plot("plot1");
		b1.setPlot(p1);
		b1.getHandelser().add(hand);
		hand.setAuthor(author1);
		hand.setBook(b1);
		entityManager.flush();
		entityManager.persist( b1);
		p1.setBook(b1);
		entityManager.persist(p1);

		entityManager.getTransaction().commit();
	}
	
	public void run2() {
		entityManager.getTransaction().begin();
		List<Book> results = entityManager.createQuery("select b from Book b", Book.class)
                .getResultList();
		for(Book b: results) {
			System.out.println(b);
			System.out.println(b.getTitle());
			for(Autor a: b.getAutors()) {
				System.out.println("\t" + a.getName());
				System.out.println("\t" + a.getBook().getTitle());
			}
			for(Handelse h: b.getHandelser()) {
				System.out.println(h.getAuthor().getName());
				System.out.println(h.getBook().getTitle());
			}
		}
		entityManager.getTransaction().commit();
	
	}
	
	public void c() {
		try {
			run();
			run2();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();	
			entityManagerFactory.close();
		}
		
	}
	public static void main(String[] args) {
		new Main().c();
	}
}
