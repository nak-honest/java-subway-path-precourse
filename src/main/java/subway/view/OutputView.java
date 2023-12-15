package subway.view;

import subway.domain.Path;
import subway.domain.Station;

import java.util.List;

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

    public void writeShortestPath(Path path) {
        writer.writeLine("## 조회 결과");
        writer.writeLine("[INFO] ---");
        writer.writeLine(String.format("[INFO] 총 거리: %dkm", path.getDistance()));
        writer.writeLine(String.format("[INFO] 총 소요 시간: %d분", path.getDuration()));
        writer.writeLine("[INFO] ---");
        writeStations(path.getStations());
        writer.writeLine("");
    }

    private void writeStations(List<Station> stations) {
        stations.forEach(station -> writer.writeLine(String.format("[INFO] %s", station.getName())));
    }
}
