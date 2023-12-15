package subway.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFileReaderWriter {
    public static List<String> read(File textFile) {
        List<String> strings = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                strings.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strings;
    }

    public static void write(File textFile, List<String> strings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFile))) {
            for (String string : strings) {
                writer.write(string);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void append(File textFile, String string) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFile, true))) {
            writer.append(string);
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeText(File textFile, String string) {
        try {
            List<String> strings = read(textFile);
            strings.remove(string);
            write(textFile, strings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clear(File textFile) {
        try {
            List<String> strings = List.of("");
            write(textFile, strings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
