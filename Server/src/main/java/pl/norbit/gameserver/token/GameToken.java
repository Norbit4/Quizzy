package pl.norbit.gameserver.token;

import java.util.Random;

public class GameToken {
    private static final Random random = new Random();

    public static String generate(){

        char[] lettersArray = {'A','X','B','Z','V','H','L','Q','T'};
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i<3; i++){

            int indexLetter = random.nextInt(lettersArray.length);
            char letter = lettersArray[indexLetter];

            int number = random.nextInt(10);

            stringBuilder.append(letter);
            stringBuilder.append(number);
        }
        return stringBuilder.toString();
    }
}
