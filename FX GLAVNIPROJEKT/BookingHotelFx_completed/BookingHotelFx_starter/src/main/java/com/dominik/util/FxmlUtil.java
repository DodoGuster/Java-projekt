package com.dominik.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Objects;

public final class FxmlUtil {

    private FxmlUtil() { }

    public static Node load(String resourcePath) {
        try {
            return FXMLLoader.load(Objects.requireNonNull(
                    FxmlUtil.class.getResource(resourcePath),
                    "FXML not found: " + resourcePath
            ));
        } catch (IOException ex) {
            DialogUtil.showError("Greška", "Ne mogu učitati ekran: " + resourcePath + "\n" + ex.getMessage());
            return null;
        }
    }
}
