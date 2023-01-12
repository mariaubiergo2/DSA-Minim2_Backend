package edu.upc.dsa.models;

public class FAQ {
    String questionFAQ;
    String answerFAQ;

    public FAQ() {}

    public FAQ(String q, String a){
        this.questionFAQ=q;
        this.answerFAQ=a;
    }

    public String getQuestionFAQ() {
        return questionFAQ;
    }

    public void setQuestionFAQ(String questionFAQ) {
        this.questionFAQ = questionFAQ;
    }

    public String getAnswerFAQ() {
        return answerFAQ;
    }

    public void setAnswerFAQ(String answerFAQ) {
        this.answerFAQ = answerFAQ;
    }
}
