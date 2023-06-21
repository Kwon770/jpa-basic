package jpabasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.rmi.server.ExportException;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("helloB");
//            em.persist(member);

//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

//            em.remove(findMember);

//            findMember.setName("helloJPA");


//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();
//            for (Member m : result) {
//                System.out.println("m.getName() = " + m.getName());
//            }


//            // 비영속
//            Member member = new Member();
//            member.setId(5L);
//            member.setName("helloB");
//
//            // 영속
//            em.persist(member);
//
//            Member findMember = em.find(Member.class, 5L);
//            // SELECT 쿼리 안나감
//            System.out.println("findMember.getId() = " + findMember.getId());
//            // SELECT 쿼리 안나감
//            Member findMember2 = em.find(Member.class, 5L);
//            System.out.println("same instance = " + (findMember == findMember2));


            // Dirty checking
            Member findMember = em.find(Member.class, 5L);
            findMember.setName("ZZZZZ");
            // em.persist(findMember) => 부자연스러움. 자바 리스트에서 객체를 불러오고 값을 수정하면 다시 리스트에 넣는가?

            em.flush();

            System.out.println("========= commit");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();;
        }

        emf.close();
    }
}
