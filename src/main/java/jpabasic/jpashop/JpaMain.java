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

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);


            Member member1 = new Member();
            member1.setName("member1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("member2");
            member2.setTeam(teamA);
            em.persist(member2);

            em.flush();
            em.clear();


//            List<Member> members = em.createQuery("select m from Member m", Member.class)
//                    .getResultList();
            // SQL: select * from Member
            // SQL: select * from Team where team_id = xxx
            // !! N + 1 !!

            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();

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
