package presentation;

public class Strings {

    public static String APP_NAME = "Jogo Gourmet";
    public static String FAILED = "Desisto";
    public static String CORRECT_PLAY_AGAIN = "Acertei denovo, quer tentar mais uma vez?";
    public static String WRONG_PLAY_AGAIN = "Ponto seu, quer jogar mais uma vez?";
    public static String GREETINGS = "Pense em um prato para que eu possa advinha-lo";
    public static String FOOD_QUESTION = "Qual prato voce pensou?";

    public static String formatQuestionText(String foodName) {
        return "O prato que voce pensou é " + foodName + "?";
    }

    public static String formatLearnQuestion(String wrongFood, String newFood) {
        return newFood + " é ______ mas " + wrongFood +" não.";
    }

    public static String formatChooseOptions(String foodDescription) {
        return "Me ajude a melhorar, marque todos os alimentos que são " + foodDescription;
    }

    public static String formatGuessText(String foodDescription) {
        return "A resposta é " + foodDescription + "?";
    }

}
