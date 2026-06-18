package bookstore.jpa;

import bookstore.entities.VinylRecordEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaVinylApp {
    public static void main(String[] args) {
        // 1. Start Hibernate (Loads persistence.xml)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstore-pu");
        EntityManager em = emf.createEntityManager();
        // 2. Create Data
        em.getTransaction().begin();
        VinylRecordEntity vinyl = new VinylRecordEntity();
        vinyl.setPrice(34.99);
        vinyl.setCopies(10);
        vinyl.setPlaybackDurationMinutes(25);
        vinyl.setTitle("Riot!");
        vinyl.setArtist("Paramore");
        vinyl.setDateOfRelease("05-07-2007");
        vinyl.setRecordSizeInches(42);
        vinyl.setRotationsPerMinute(45);
        em.persist(vinyl);
        em.getTransaction().commit();

        // 3. Read Data (Polymorphic check)
        // Notice we can query specifically for Widgets
        List<VinylRecordEntity> vinyls = em.createQuery(
                "SELECT w FROM VinylRecordEntity w", VinylRecordEntity.class).getResultList();

        for (VinylRecordEntity v : vinyls) {
            System.out.println("Found: " + v);
        }

        em.close();


    }
}