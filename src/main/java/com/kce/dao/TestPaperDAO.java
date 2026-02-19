package com.kce.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.kce.bean.TestPaper;
import com.kce.util.HibernateUtil;

public class TestPaperDAO {

    // Note: generatePaperID() is no longer needed because we used 
    // @GeneratedValue in the Bean. Hibernate handles IDs automatically.

    public boolean recordTestPaper(TestPaper paper) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(paper); // Saves the paper and auto-generates the ID
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePaperStatus(int paperID, String status) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            TestPaper paper = session.get(TestPaper.class, paperID);
            if (paper != null) {
                paper.setStatus(status);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public TestPaper findPaperByID(int paperID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(TestPaper.class, paperID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TestPaper> findPublishedPapersContainingQuestion(String questionID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Using HQL with 'like' for the question list string
            String hql = "from TestPaper where status = 'PUBLISHED' and questionIdList like :qid";
            Query<TestPaper> query = session.createQuery(hql, TestPaper.class);
            query.setParameter("qid", "%" + questionID + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}