package com.kce.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "test_papers_tbl")
public class TestPaper {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "paper_id")
	private int paperID;
	
	@Column(name = "paper_title")
	private String paperTitle;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "total_marks")
	private double totalMarks;
	
	@Column(name = "question_id_list")
	private String questionIdList; // Stores IDs like "Q1,Q2,Q5"
	
	@Column(name = "status")
	private String status = "DRAFT"; // Default status

	// Standard Getters and Setters
	public int getPaperID() {
		return paperID;
	}
	public void setPaperID(int paperID) {
		this.paperID = paperID;
	}
	public String getPaperTitle() {
		return paperTitle;
	}
	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public double getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(double totalMarks) {
		this.totalMarks = totalMarks;
	}
	public String getQuestionIdList() {
		return questionIdList;
	}
	public void setQuestionIdList(String questionIdList) {
		this.questionIdList = questionIdList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}