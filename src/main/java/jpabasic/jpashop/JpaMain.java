package jpabasic.jpashop;

import jpabasic.jpashop.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setName("hello");
            member.setHomeAddress(new Address("city", "street", "zipcode"));
            member.setWorkperiod(new Period());

            // 불변으로 만든다!
            // 변경이 필요하면 통으로 새로 만든다!
            Address newAddress = new Address("newCity", "newStreet", "newZipcode");
            member.setHomeAddress(newAddress);

            em.persist(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            ;
        }

        emf.close();
    }
}
