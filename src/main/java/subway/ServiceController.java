package subway;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import subway.domain.Line;
import subway.domain.Station;
import subway.domain.constants.LineData;
import subway.domain.constants.StationData;
import subway.helpers.DistanceCalculator;
import subway.ui.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ServiceController {
    private final List<Line> lines = new ArrayList<>();
    private final List<Station> stations = new ArrayList<>();
    private final DistanceCalculator distanceCalculator;

    public ServiceController() {
        this.distanceCalculator = new DistanceCalculator();
        this.initStations(this.distanceCalculator);
        this.initLines();
        this.distanceCalculator.setEdges();
    }

    private void initLines() {
        LineData[] data = LineData.values();
        for (LineData key : data) {
            Line newLine = new Line(key.getName());
            List<String> stationNames = key.getStations();
            newLine.addStations(stationNames);
            this.lines.add(newLine);
        }
    }

    private void initStations(DistanceCalculator distanceCalculator) {
        StationData[] data = StationData.values();
        for (StationData key : data) {
            Station newStation = new Station(key.getName());
            this.stations.add(newStation);
            distanceCalculator.addVertices(key.getName());
        }
    }

    public void handleResults(String departure, String destination, String option) {
        OutputView outputView = new OutputView();
        DijkstraShortestPath dijkstraShortestPath = this.distanceCalculator.getDijkstraShortestPath(option);
        List<String> shortestPath = this.distanceCalculator.getShortestPath(dijkstraShortestPath, departure, destination, option);
        double km = 0;
        double m = 0;
        if (option.equals("1")) {
            km = dijkstraShortestPath.getPath(departure, destination).getWeight();
            m = this.distanceCalculator.getEdgeSum("m", shortestPath);
        }
        if (option.equals("2")) {
            m = dijkstraShortestPath.getPath(departure, destination).getWeight();
            km = this.distanceCalculator.getEdgeSum("km", shortestPath);
        }
        outputView.printResults(shortestPath, km, m);
    }
}
