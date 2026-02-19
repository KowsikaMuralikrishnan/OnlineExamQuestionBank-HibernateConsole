package com.kce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kce.bean.Question;
import com.kce.bean.TestPaper;
import com.kce.dao.QuestionDAO;
import com.kce.dao.TestPaperDAO;
import com.kce.util.QuestionInPublishedPaperException;
import com.kce.util.QuestionPoolInsufficientException;
import com.kce.util.ValidateException;

public class ExamService {

	private QuestionDAO questionDAO = new QuestionDAO();
	private TestPaperDAO testPaperDAO = new TestPaperDAO();

	public List<Question> viewAllQuestions() {
		return questionDAO.viewAllQuestions();
	}

	public boolean addNewQuestion(Question question) {
		if (question == null || question.getQuestionID() == null) return false;
		
		// Check if question already exists to avoid primary key error
		if (questionDAO.findQuestion(question.getQuestionID()) != null) {
			return false;
		}

		question.setDifficulty(question.getDifficulty().toUpperCase());
		question.setStatus("ACTIVE");
		return questionDAO.insertQuestion(question);
	}

	public boolean removeQuestion(String questionID) throws QuestionInPublishedPaperException {
		if (questionID == null) return false;
		
		// Professional check: Is this question part of a published exam?
		List<TestPaper> papers = testPaperDAO.findPublishedPapersContainingQuestion(questionID.trim());
		if (papers != null && !papers.isEmpty()) {
			throw new QuestionInPublishedPaperException();
		}

		// Soft delete: just set status to INACTIVE
		return questionDAO.updateQuestionStatus(questionID.trim(), "INACTIVE");
	}

	public boolean createTestPaper(String paperTitle, String subject, double requiredTotalMarks, String difficultyMixSpec)
			throws ValidateException, QuestionPoolInsufficientException {

		// 1. Validation
		if (paperTitle == null || subject == null || difficultyMixSpec == null) {
			throw new ValidateException();
		}

		// 2. Parse the Difficulty Spec (EASY=10,MEDIUM=10)
		Map<String, Integer> difficultyMap = new HashMap<>();
		int totalFromSpec = 0;
		try {
			String[] parts = difficultyMixSpec.split(",");
			for (String part : parts) {
				String[] kv = part.split("=");
				int marks = Integer.parseInt(kv[1].trim());
				difficultyMap.put(kv[0].trim().toUpperCase(), marks);
				totalFromSpec += marks;
			}
		} catch (Exception e) {
			throw new ValidateException();
		}

		if (totalFromSpec != (int) requiredTotalMarks) {
			throw new ValidateException();
		}

		// 3. Selection Logic
		List<String> selectedQuestionIds = new ArrayList<>();

		for (String difficulty : difficultyMap.keySet()) {
			int requiredMarksForDiff = difficultyMap.get(difficulty);
			
			List<Question> available = questionDAO.findQuestionsBySubjectAndDifficulty(subject, difficulty);
			
			double currentPoolMarks = 0;
			List<String> tempIds = new ArrayList<>();

			for (Question q : available) {
				if (currentPoolMarks >= requiredMarksForDiff) break;
				tempIds.add(q.getQuestionID());
				currentPoolMarks += q.getMarks();
			}

			if (currentPoolMarks < requiredMarksForDiff) {
				throw new QuestionPoolInsufficientException();
			}
			selectedQuestionIds.addAll(tempIds);
		}

		// 4. Record Paper (No Manual Connection/Commit needed!)
		TestPaper paper = new TestPaper();
		paper.setPaperTitle(paperTitle);
		paper.setSubject(subject);
		paper.setTotalMarks(requiredTotalMarks);
		paper.setQuestionIdList(String.join(",", selectedQuestionIds));
		paper.setStatus("DRAFT");

		return testPaperDAO.recordTestPaper(paper);
	}
}