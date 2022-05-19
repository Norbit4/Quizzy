package pl.norbit.gameclient.objects;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String tittle;
    private final List<String> answers;
    private int goodAnswer;

    public Question() {
        answers = new ArrayList<>();
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void addAnswer(String question, boolean isTrue){

        answers.add(question);

        if(isTrue){
            goodAnswer = answers.indexOf(question);
        }
    }

    public void addAnswer(String question){

        answers.add(question);
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getGoodAnswer() {
        return goodAnswer;
    }
}
