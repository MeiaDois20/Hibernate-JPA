package br.com.thalysravel.application;

import br.com.thalysravel.model.entity.Department;
import br.com.thalysravel.model.entity.Seller;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();

        Department eletronic = em.find(Department.class, 1);
        Seller s = new Seller(null, "Thalys Ravel", "ravelthalys@proton.me", eletronic);

        em.getTransaction().begin();
            em.persist(s);
        em.getTransaction().commit();
        System.out.println("Pronto!");

        em.close();
        emf.close();
    }
}