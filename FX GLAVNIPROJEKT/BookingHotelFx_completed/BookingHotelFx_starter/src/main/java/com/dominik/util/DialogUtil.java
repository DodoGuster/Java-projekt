package com.dominik.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * JavaFX dijalozi (info / error) – koristi se u kontrolerima i util klasama.
 */
public final class DialogUtil {

    private DialogUtil() {
    }

    public static void info(String title, String message) {
        show(AlertType.INFORMATION, title, message);
    }

    public static void error(String title, String message) {
        show(AlertType.ERROR, title, message);
    }

    /**
     * Kompatibilnost s ranijim pozivima.
     */
    public static void showError(String title, String message) {
        error(title, message);
    }

    private static void show(AlertType type, String title, String message) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
