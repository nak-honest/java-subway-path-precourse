package subway.view;

public class OutputView {
    private final Writer writer;

    public OutputView(Writer writer) {
        this.writer = writer;
    }

    public void writeMainMenu() {
        writer.writeLine("## 메인 화면");
        writer.writeLine("1. 경로 조회");
        writer.writeLine("Q. 종료");
        writer.writeLine("");
    }
}
