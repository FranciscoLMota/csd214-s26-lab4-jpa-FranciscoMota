package bookstore.jpa;

import bookstore.entities.CassetteTapeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaCassetteApp {
    public static void main(String[] args) {
        // 1. Start Hibernate (Loads persistence.xml)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore-pu");
        EntityManager em = emf.createEntityManager();
        // 2. Create Data
        em.getTransaction().begin();
        CassetteTapeEntity cassette = new CassetteTapeEntity();
        cassette.setPrice(34.99);
        cassette.setCopies(10);
        cassette.setPlaybackDurationMinutes(25);
        cassette.setTitle("Riot!");
        cassette.setArtist("Paramore");
        cassette.setDateOfRelease("05-07-2007");
        cassette.setHasAutoReverse(true);
        em.persist(cassette);
        em.getTransaction().commit();

        // 3. Read Data (Polymorphic check)
        // Notice we can query specifically for Widgets
        List<CassetteTapeEntity> cassettes = em.createQuery(
                "SELECT w FROM CassetteTapeEntity w", CassetteTapeEntity.class).getResultList();

        for (CassetteTapeEntity c : cassettes) {
            System.out.println("Found: " + c);
        }

        em.close();


    }
}