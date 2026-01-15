package app.entities.util;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JsonUtils {

    private static final Jsonb jsonb = JsonbBuilder.create();

    private JsonUtils(){}

    public static <T> List<T> readList(File file, Class<T[]> clazz) throws IOException {
        if (!file.exists()) {
            return Collections.emptyList();
        }
        try (Reader r = new FileReader(file)) {
            T[] array = jsonb.fromJson(r, clazz);
            return Arrays.asList(array);
        }
    }

    public static <T> void writeList(File file, List<T> list) throws IOException {
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        try (Writer w = new FileWriter(file)) {
            jsonb.toJson(list, w);
        }
    }
}
