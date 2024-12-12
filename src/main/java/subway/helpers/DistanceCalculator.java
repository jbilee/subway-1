package subway.helpers;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.constants.LineData;
import subway.ui.constants.ErrorMessages;

import java.util.List;

public class DistanceCalculator {
    private final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph
            = new WeightedMultigraph(DefaultWeightedEdge.class);
    private final WeightedMultigraph<String, DefaultWeightedEdge> timeGraph
            = new WeightedMultigraph(DefaultWeightedEdge.class);

    public void addVertices(String station) {
        this.distanceGraph.addVertex(station);
        this.timeGraph.addVertex(station);
    }

    public void setEdges() {
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

    public DijkstraShortestPath getDijkstraShortestPath(String option) {
        if (option.equals("1")) {
            return new DijkstraShortestPath(this.distanceGraph);
        }
        if (option.equals("2")) {
            return new DijkstraShortestPath(this.timeGraph);
        }
        throw new IllegalStateException(ErrorMessages.UNKNOWN.getMessage());
    }

    public List<String> getShortestPath(DijkstraShortestPath dijkstraShortestPath, String departure, String destination, String option) {
        if (option.equals("1")) {
            return dijkstraShortestPath.getPath(departure, destination).getVertexList();
        }
        if (option.equals("2")) {
            return dijkstraShortestPath.getPath(departure, destination).getVertexList();
        }
        throw new IllegalStateException(ErrorMessages.UNKNOWN.getMessage());
    }

    public double getEdgeSum(String type, List<String> edges) {
        double weight = 0;
        for (int i = 0; i < edges.size(); i++) {
            if (i + 1 == edges.size()) {
                break;
            }
            String path1 = edges.get(i) + "-" + edges.get(i + 1);
            String path2 = edges.get(i + 1) + "-" + edges.get(i);
            String weights = LineData.getWeights(path1, path2);
            String weightStr = "";
            if (type.equals("km")) {
                weightStr = List.of(weights.split(",")).get(1);
            }
            if (type.equals("m")) {
                weightStr = List.of(weights.split(",")).get(2);
            }
            weight += Double.parseDouble(weightStr);
        }
        return weight;
    }
}
