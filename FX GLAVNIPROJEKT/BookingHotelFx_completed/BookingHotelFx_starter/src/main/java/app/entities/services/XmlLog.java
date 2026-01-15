package app.entities.services;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static app.entities.Staff.log;

public class XmlLog {
    private static final String LOG_FILE = "log.xml";
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void log(String text) {
        try {
            File file = new File(LOG_FILE);
            boolean newFile = !file.exists();

            PrintWriter pw = new PrintWriter(new FileWriter(file, true));

            if (newFile) {
                pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                pw.println("<log>");
            }

            String time = LocalDateTime.now().format(FORMAT);
            pw.println("  <entry time=\"" + time + "\">" + text + "</entry>");

            pw.flush();
            pw.close();
        } catch (IOException e) {
            log.info("Greška pri zapisivanju loga.");
        }
    }

    public void printWithoutTags() {
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("<entry")) {
                    // izvuci vrijeme
                    int start = line.indexOf("time=\"") + 6;
                    int end = line.indexOf("\"", start);
                    String time = line.substring(start, end);

                    // izvuci tekst unutar taga
                    int open = line.indexOf('>') + 1;
                    int close = line.indexOf("</entry>");
                    String text = line.substring(open, close).trim();

                    log.info("{} + {}", time, text);

                }
            }
        } catch (IOException e) {
            log.info("Nema log datoteke.");
        }
    }
}

