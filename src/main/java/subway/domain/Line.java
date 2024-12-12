package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String name;
    private List<String> stations = new ArrayList<>();

    public Line(String name) {
        this.name = name;
    }

    public void addStations(List<String> names) {
        this.stations.addAll(names);
    }
}
