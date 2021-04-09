package presentation;

import domain.entity.Food;

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

    public static String learnQuestion(String foodName){
        return JOptionPane.showInputDialog(null, formatLearnQuestion(foodName), FAILED, JOptionPane.INFORMATION_MESSAGE);
    }

    public static Boolean showCorrectPlayAgain(){
        return 0 == JOptionPane.showConfirmDialog(null, CORRECT_PLAY_AGAIN, APP_NAME, JOptionPane.YES_NO_OPTION);
    }

    public static Boolean showWrongPlayAgain(){
        return 0 == JOptionPane.showConfirmDialog(null, WRONG_PLAY_AGAIN, APP_NAME, JOptionPane.YES_NO_OPTION);
    }

    public static List<JRadioButton> showOptionsToLearn(String foodDescription, List<Food> foodList){
        final JPanel panel = new JPanel();
        final JLabel label = new JLabel(formatChooseOptions(foodDescription));
        panel.add(label);

        List<JRadioButton> buttonList = new ArrayList();
        for(var i = 0; i < foodList.size(); i++){
            var button = new JRadioButton(foodList.get(i).getName());
            buttonList.add(button);
            panel.add(button);
        }
        JOptionPane.showMessageDialog(null, panel,APP_NAME, JOptionPane.YES_OPTION);
        return buttonList;
    }

    public static Boolean testGuess(String foodName){
        Boolean confirm = 0 == JOptionPane.showConfirmDialog(null, formatGuessText(foodName), APP_NAME, JOptionPane.YES_NO_OPTION);
        return confirm;
    }




}
