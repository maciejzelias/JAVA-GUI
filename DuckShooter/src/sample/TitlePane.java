package sample;

import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TitlePane extends Text {

    public TitlePane() {
        String name = "D u c k    S h o o t e r";
        setText(name);
        setFill(Color.DARKGREEN);
        /**
         * javafx fonts found at
         * https://coderslegacy.com/java/javafx-font/
         */
        Font font = Font.font("Impact", FontWeight.NORMAL, 30);
        setFont(font);
        /**
         * javafx text effects found at
         * https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/text-effects.htm
         */
        Reflection reflection = new Reflection();
        reflection.setFraction(0.7f);
        setEffect(reflection);
    }
}
