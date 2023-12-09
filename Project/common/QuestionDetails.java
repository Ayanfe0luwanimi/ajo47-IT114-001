package Project.common;

import java.util.List;

public class QuestionDetails {
    private String category;
    private String question;
    private List<String> answerOptions;
    private String correctAnswer;

    public QuestionDetails(String category, String question, List<String> answerOptions, String correctAnswer) {
        this.category = category;
        this.question = question;
        this.answerOptions = answerOptions;
        this.correctAnswer = correctAnswer;
    }

   

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
