
package Day8.class_problems;

import java.util.*;

abstract class Question {
    protected String questionText;
    protected String correctAnswer;

    public Question(String questionText, String correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public abstract void display();

    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class MultipleChoiceQuestion extends Question {
    private String[] options;

    public MultipleChoiceQuestion(String questionText,
                                  String[] options,
                                  String correctAnswer) {
        super(questionText, correctAnswer);
        this.options = options;
    }

    @Override
    public void display() {
        System.out.println(questionText);
        for (String option : options) {
            System.out.println(option);
        }
    }
}

class TrueFalseQuestion extends Question {
    public TrueFalseQuestion(String questionText, String correctAnswer) {
        super(questionText, correctAnswer);
    }

    @Override
    public void display() {
        System.out.println(questionText + " (True/False)");
    }
}

class Student {
    private String name;
    private String studentId;

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }
}

class Attempt {
    private Student student;
    private Map<Integer, String> answers = new HashMap<>();
    private boolean submitted = false;

    public Attempt(Student student) {
        this.student = student;
    }

    public void answerQuestion(int questionNumber, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers after submission.");
            return;
        }

        answers.put(questionNumber, answer);
    }

    public void submit() {
        if (!submitted) {
            submitted = true;
            System.out.println("Exam submitted successfully.");
        } else {
            System.out.println("Exam has already been submitted.");
        }
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public String getAnswer(int questionNumber) {
        return answers.get(questionNumber);
    }

    public Student getStudent() {
        return student;
    }
}

class Examination {
    private String title;
    private List<Question> questions = new ArrayList<>();
    private Map<String, Attempt> attempts = new HashMap<>();

    public Examination(String title) {
        this.title = title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Attempt startExam(Student student) {
        if (attempts.containsKey(student.getStudentId())) {
            System.out.println("This student has already started this exam.");
            return attempts.get(student.getStudentId());
        }

        Attempt attempt = new Attempt(student);
        attempts.put(student.getStudentId(), attempt);
        return attempt;
    }

    public void displayQuestions() {
        System.out.println("\nExam: " + title);

        for (int i = 0; i < questions.size(); i++) {
            System.out.print((i + 1) + ". ");
            questions.get(i).display();
        }
    }

    public void showResult(Attempt attempt) {
        if (!attempt.isSubmitted()) {
            System.out.println("Submit the exam before viewing results.");
            return;
        }

        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            String answer = attempt.getAnswer(i + 1);

            if (answer != null && questions.get(i).evaluate(answer)) {
                score++;
            }
        }

        System.out.println("\nStudent: " + attempt.getStudent().getName());
        System.out.println("Score: " + score + "/" + questions.size());
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Examination exam = new Examination("Math Quiz");

        exam.addQuestion(new MultipleChoiceQuestion(
            "Q1. What is 2 + 2?",
            new String[]{"A. 4", "B. 3", "C. 5"},
            "A"
        ));

        exam.addQuestion(new MultipleChoiceQuestion(
            "Q2. What is 3 x 3?",
            new String[]{"A. 6", "B. 9", "C. 12"},
            "B"
        ));

        Student student = new Student("Rahul", "S101");

        Attempt attempt = exam.startExam(student);

        exam.displayQuestions();

        attempt.answerQuestion(1, "A");
        attempt.answerQuestion(2, "C");

        attempt.submit();

        exam.showResult(attempt);

        // Attempting to change an answer after submission
        attempt.answerQuestion(2, "B");
        exam.showResult(attempt);
    }
}
