package subway.view;

import java.util.function.Supplier;

public class InputView {
    private final Supplier<String> reader;
    private final Writer writer;

    public InputView(Supplier<String> reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public String readMenu() {
        writer.writeLine("## 원하는 기능을 선택하세요.");
        String menu = reader.get();
        writer.writeLine("");

        return menu;
    }
}
