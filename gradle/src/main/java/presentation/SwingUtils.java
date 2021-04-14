package presentation;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static presentation.Strings.*;

public class SwingUtils {

    public static void showGreetings(){
        JOptionPane.showMessageDialog(null, GREETINGS, APP_NAME, JOptionPane.DEFAULT_OPTION);
    }

    public static Boolean askQuestion(String foodName){
        Boolean confirm = 0 == JOptionPane.showConfirmDialog(null, formatQuestionText(foodName), APP_NAME, JOptionPane.YES_NO_OPTION);
        return confirm;
    }

    public static String learnFood(){
        return JOptionPane.showInputDialog(null, FOOD_QUESTION, FAILED, JOptionPane.INFORMATION_MESSAGE);
    }

    public static String learnQuestion(String wrongFood, String newFood){
        return JOptionPane.showInputDialog(null, formatLearnQuestion(wrongFood, newFood), FAILED, JOptionPane.INFORMATION_MESSAGE);
    }

    public static Boolean showCorrectPlayAgain(){
        return 0 == JOptionPane.showConfirmDialog(null, CORRECT_PLAY_AGAIN, APP_NAME, JOptionPane.YES_NO_OPTION);
    }

    public static Boolean showWrongPlayAgain(){
        return 0 == JOptionPane.showConfirmDialog(null, WRONG_PLAY_AGAIN, APP_NAME, JOptionPane.YES_NO_OPTION);
    }



    public static Boolean testGuess(String foodName){
        Boolean confirm = 0 == JOptionPane.showConfirmDialog(null, formatGuessText(foodName), APP_NAME, JOptionPane.YES_NO_OPTION);
        return confirm;
    }




}
