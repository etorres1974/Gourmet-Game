package entity;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class FoodFormData {
    public String name;
    public Boolean active;

    public FoodFormData(String name, Boolean active){
        this.name = name;
        this.active = active;
    }
    public static List<FoodFormData> fromButtonList(List<JRadioButton> buttonList){
        return buttonList
                .stream()
                .map( button -> new FoodFormData(button.getText(), button.isSelected()))
                .collect(Collectors.toList()) ;
    }
}
