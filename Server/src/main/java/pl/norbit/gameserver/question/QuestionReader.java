package pl.norbit.gameserver.question;

import pl.norbit.gameserver.objects.Question;

import java.io.*;
import java.util.Objects;
import java.util.Random;

public class QuestionReader {

    private static final String prefix = "q-";
    private static final String suffix = ".txt";
    private static final String mainPath = System.getProperty("user.dir") + "/questions";
    private static final Random random;

    static{
        random = new Random();
    }

    public static Question getQuestion(){

        File file = new File(mainPath +"/"+ prefix + getRandomQuestionIndex() + suffix);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String[] questionArray = new String[8];
            int i = 0;

            String line;
            while ((line = br.readLine()) != null) {
                questionArray[i] = line;
                i++;
            }
            br.close();

            Question question = new Question();
            question.setTittle(questionArray[1]);

            question.addAnswer(questionArray[3], true);
            question.addAnswer(questionArray[4]);
            question.addAnswer(questionArray[5]);
            question.addAnswer(questionArray[6]);

            return question;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int checkFileAmount(){
        System.out.println(mainPath);

        File file = new File(mainPath);

        return Objects.requireNonNull(file.list()).length;
    }

    private static int getRandomQuestionIndex(){

        return random.nextInt(checkFileAmount());
    }
}
