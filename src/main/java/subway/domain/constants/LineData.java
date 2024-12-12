package subway.domain.constants;

import subway.ui.constants.ErrorMessages;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum LineData {
    TWO("2호선", List.of("교대역", "강남역", "역삼역"), List.of("교대역-강남역,2,3", "강남역-역삼역,2,3")),
    THREE("3호선", List.of("교대역", "남부터미널역", "양재역", "매봉역"), List.of("교대역-남부터미널역,3,2", "남부터미널역-양재역,6,5", "양재역-매봉역,1,1")),
    SHINBUN("신분당선", List.of("강남역", "양재역", "양재시민의숲역"), List.of("강남역-양재역,2,8", "양재역-양재시민의숲역,10,3"));

    private final String name;
    private final List<String> stations;
    private final List<String> distances;

    LineData(String name, List<String> stations, List<String> distances) {
        this.name = name;
        this.stations = stations;
        this.distances = distances;
    }

    public String getName() {
        return name;
    }

    public List<String> getDistances() {
        return distances;
    }

    public static String getWeights(String path) {
        for (LineData data : LineData.values()) {
            for (String distances : data.getDistances()) {
                if (distances.contains(path)) {
                    return distances;
                }
            }
        }
        throw new IllegalStateException(ErrorMessages.UNKNOWN.getMessage());
    }

    public static boolean hasCommonRoute(String departure, String destination) {
        List<String> departureLines = Arrays.stream(LineData.values())
                .filter(line -> line.hasStation(departure))
                .map(LineData::getName)
                .collect(Collectors.toList());
        List<String> destinationLines = Arrays.stream(LineData.values())
                .filter(line -> line.hasStation(destination))
                .map(LineData::getName)
                .collect(Collectors.toList());
        departureLines.retainAll(destinationLines);
        return !departureLines.isEmpty();
    }

    public boolean hasStation(String target) {
        return stations.contains(target);
    }

    public List<String> getStations() {
        return stations;
    }
}
