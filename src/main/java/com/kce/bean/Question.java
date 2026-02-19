package com.kce.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "questions_tbl") // This creates/links to the table in Oracle/MySQL
public class Question {
	
	@Id
	@Column(name = "question_id")
	private String questionID;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "topic")
	private String topic;
	
	@Column(name = "difficulty")
	private String difficulty;
	
	@Column(name = "marks")
	private double marks;
	
	@Column(name = "status")
	private String status = "ACTIVE"; // Default status like your friend's project

	// Standard Getters and Setters
	public String getQuestionID() {
		return questionID;
	}
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		this.marks = marks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}