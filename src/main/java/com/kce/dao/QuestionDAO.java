package com.kce.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.kce.bean.Question;
import com.kce.util.HibernateUtil;

public class QuestionDAO {

	public Question findQuestion(String questionID) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Question.class, questionID);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Question> viewAllQuestions() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Question", Question.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean insertQuestion(Question q) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(q); // persist is the Hibernate 6 way to save
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (transaction != null) transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	public List<Question> findQuestionsBySubjectAndDifficulty(String subject, String difficulty) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Question where subject = :sub and difficulty = :diff and status = 'ACTIVE'";
			Query<Question> query = session.createQuery(hql, Question.class);
			query.setParameter("sub", subject);
			query.setParameter("diff", difficulty);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateQuestionStatus(String questionID, String status) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Question q = session.get(Question.class, questionID);
			if (q != null) {
				q.setStatus(status); // Dirty checking automatically updates the DB on commit
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

	public boolean deleteQuestion(String questionID) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Question q = session.get(Question.class, questionID);
			if (q != null) {
				session.remove(q); // remove replaces the old delete method
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
}