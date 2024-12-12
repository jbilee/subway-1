package subway.domain;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.constants.LineData;
import subway.domain.constants.StationData;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getDijkstraShortestPath(String departure, String destination) {
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(this.distanceGraph);
        return dijkstraShortestPath.getPath(departure, destination).getVertexList();
    }
}
