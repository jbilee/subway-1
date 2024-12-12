package subway.domain;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.constants.LineData;
import subway.domain.constants.StationData;
import subway.ui.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Handler {
    private List<Line> lines = new ArrayList<>();
    private List<Station> stations = new ArrayList<>();
    private final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph
            = new WeightedMultigraph(DefaultWeightedEdge.class);
    private final WeightedMultigraph<String, DefaultWeightedEdge> timeGraph
            = new WeightedMultigraph(DefaultWeightedEdge.class);

    public Handler() {
        this.initStations();
        this.initLines();
        this.initGraphs();
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

    private void initStations() {
        StationData[] data = StationData.values();
        for (StationData key : data) {
            Station newStation = new Station(key.getName());
            this.stations.add(newStation);
            this.distanceGraph.addVertex(key.getName());
            this.timeGraph.addVertex(key.getName());
        }
    }

    private void initGraphs() {
        LineData[] data = LineData.values();
        for (LineData key : data) {
            List<String> distances = key.getDistances();
            distances.forEach(distance -> {
                String departure = distance.split(",")[0].split("-")[0];
                String destination = distance.split(",")[0].split("-")[1];
                int km = Integer.parseInt(distance.split(",")[1]);
                int m = Integer.parseInt(distance.split(",")[2]);
                this.distanceGraph.setEdgeWeight(this.distanceGraph.addEdge(departure, destination), km);
                this.timeGraph.setEdgeWeight(this.timeGraph.addEdge(departure, destination), m);
            });
        }
    }

    public void getDijkstraShortestPath(String departure, String destination, String option) {
        OutputView outputView = new OutputView();
        DijkstraShortestPath dijkstraShortestPath;
        List<String> shortestPath = new ArrayList<>();
        double km = 0;
        double m = 0;
        if (option.equals("1")) {
            dijkstraShortestPath = new DijkstraShortestPath(this.distanceGraph);
            shortestPath = dijkstraShortestPath.getPath(departure, destination).getVertexList();
            km = dijkstraShortestPath.getPath(departure, destination).getWeight();
        }
        if (option.equals("2")) {
            dijkstraShortestPath = new DijkstraShortestPath(this.timeGraph);
            shortestPath = dijkstraShortestPath.getPath(departure, destination).getVertexList();
            m = dijkstraShortestPath.getPath(departure, destination).getWeight();
        }
        if (km == 0) {
            km = getEdgeSum("km", shortestPath);
        }
        if (m == 0) {
            m = getEdgeSum("m", shortestPath);
        }
        outputView.printResults(shortestPath, km, m);
    }

    public double getEdgeSum(String type, List<String> edges) {
        double weight = 0;
        for (int i = 0; i < edges.size(); i++) {
            if (i + 1 == edges.size()) {
                break;
            }
            String path = edges.get(i) + "-" + edges.get(i + 1);
            if (type.equals("km")) {
                String weights = LineData.getWeights(path);
                String weightStr = List.of(weights.split(",")).get(1);
                weight += Double.parseDouble(weightStr);
            }
            if (type.equals("m")) {
                String weights = LineData.getWeights(path);
                String weightStr = List.of(weights.split(",")).get(2);
                weight += Double.parseDouble(weightStr);
            }
        }
        return weight;
    }
}
