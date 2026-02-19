package com.kce.main;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.kce.bean.Question;
import com.kce.service.ExamService;
import com.kce.util.HibernateUtil;
import com.kce.util.QuestionInPublishedPaperException;
import com.kce.util.QuestionPoolInsufficientException;
import com.kce.util.ValidateException;

public class ExamMain {

    public static void main(String[] args) {
    	HibernateUtil.getSessionFactory();

        Scanner sc = new Scanner(System.in);
        ExamService examService = new ExamService();

        int choice;

        do {
            System.out.println("\n===============================");
            System.out.println(" Online Exam Question Bank ");
            System.out.println("===============================");
            System.out.println("1. Add New Question");
            System.out.println("2. Create Test Paper");
            System.out.println("3. Remove Question");
            System.out.println("4. View All Questions");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            // Handle non-integer input to prevent crashing
            while (!sc.hasNextInt()) {
                System.out.println("❌ Please enter a valid number (1-5).");
                sc.next();
            }
            
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        Question q = new Question();
                        System.out.print("Enter Question ID (e.g., Q101): ");
                        q.setQuestionID(sc.nextLine());

                        System.out.print("Enter Subject: ");
                        q.setSubject(sc.nextLine());

                        System.out.print("Enter Topic: ");
                        q.setTopic(sc.nextLine());

                        System.out.print("Enter Difficulty (EASY/MEDIUM/HARD): ");
                        q.setDifficulty(sc.nextLine());

                        System.out.print("Enter Marks: ");
                        q.setMarks(Double.parseDouble(sc.nextLine()));

                        if (examService.addNewQuestion(q))
                            System.out.println("✅ Question Added Successfully!");
                        else
                            System.out.println("❌ Add Failed (Question might already exist).");
                        break;

                    case 2:
                        System.out.print("Enter Paper Title: ");
                        String title = sc.nextLine();

                        System.out.print("Enter Subject: ");
                        String sub = sc.nextLine();

                        System.out.print("Enter Total Marks: ");
                        double totalMarks = Double.parseDouble(sc.nextLine());

                        System.out.print("Enter Difficulty Mix (e.g., EASY=2,MEDIUM=3): ");
                        String mix = sc.nextLine();

                        if (examService.createTestPaper(title, sub, totalMarks, mix))
                            System.out.println("✅ Test Paper Created Successfully!");
                        else
                            System.out.println("❌ Creation Failed.");
                        break;

                    case 3:
                        System.out.print("Enter Question ID to remove: ");
                        String rid = sc.nextLine();

                        if (examService.removeQuestion(rid))
                            System.out.println("✅ Question marked as INACTIVE!");
                        else
                            System.out.println("❌ Removal Failed.");
                        break;

                    case 4:
                    	
                        List<Question> questions = examService.viewAllQuestions();

                        if (questions == null || questions.isEmpty()) {
                            System.out.println("\n[!] No questions found in the bank.");
                        } else {
                            System.out.println("\n--- CURRENT QUESTION LIST ---");
                            for (Question item : questions) {
                                // This format is clean and vertical, making it easy to read
                                System.out.println("ID        : " + item.getQuestionID());
                                System.out.println("Subject   : " + item.getSubject());
                                System.out.println("Topic     : " + item.getTopic());
                                System.out.println("Difficulty: " + item.getDifficulty());
                                System.out.println("Marks     : " + item.getMarks());
                                System.out.println("Status    : " + item.getStatus());
                                System.out.println("-----------------------------");
                            }
                        }
                        break;

                    case 5:
                        System.out.println("Thank You for Using the System!");
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }
            } catch (ValidateException e) {
                System.out.println("⚠ Validation Error: Please check your inputs.");
            } catch (QuestionPoolInsufficientException e) {
                System.out.println("⚠ Pool Error: Not enough questions to create the paper.");
            } catch (QuestionInPublishedPaperException e) {
                System.out.println("⚠ Removal Error: Question is locked in a published paper.");
            } catch (Exception e) {
                System.out.println("⚠ Unexpected Error: " + e.getMessage());
            }

        } while (choice != 5);

        sc.close();
    }
}