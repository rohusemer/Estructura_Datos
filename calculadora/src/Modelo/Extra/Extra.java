/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Extra;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 *
 * @author Luis A. Sarango-Parrales
 */
public class Extra {

    public static boolean isContained(String s1, String s2) {
        return s1.equalsIgnoreCase(s2.substring(0, s1.length()));
    }

    public static boolean isNumeric(String cadena) {
        for (int i = 0; i < cadena.length(); i++) {
            if (!Character.isDigit(cadena.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isBooleanAnswer(String s) {
        boolean c1 = s.equalsIgnoreCase("SI");
        boolean c2 = s.equalsIgnoreCase("NO");
        return c1 || c2;
    }

    public static boolean areFilesChoosed(String path1, String path2) {
        return path1 != null && path2 != null;
    }

    public static void generarFlashLabel(Label label, int vecesAparece) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), label);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(vecesAparece);
        fadeTransition.play();
    }

}
