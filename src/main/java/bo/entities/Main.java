package bo.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("traitement-fichier-tp");
        EntityManager em = emf.createEntityManager();
    }
}
