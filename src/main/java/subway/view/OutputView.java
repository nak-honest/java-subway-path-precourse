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

    public void writePathMenu() {
        writer.writeLine("## 경로 기준");
        writer.writeLine("1. 최단 거리");
        writer.writeLine("2. 최소 시간");
        writer.writeLine("B. 돌아가기");
        writer.writeLine("");
    }
}
