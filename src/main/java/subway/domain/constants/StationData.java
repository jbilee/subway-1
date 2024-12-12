package subway.domain.constants;

import subway.ui.constants.ErrorMessages;

import java.util.Arrays;

public enum StationData {
    KYO("교대역"),
    KANG("강남역"),
    YEOK("역삼역"),
    NAM("남부터미널역"),
    YANG("양재역"),
    SOUP("양재시민의숲역"),
    MAE("매봉역");

    private final String name;

    StationData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static StationData findStation(String target) {
        return Arrays.stream(StationData.values())
                .filter(name -> name.hasStation(target))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.INVALID.getMessage()));
    }

    public boolean hasStation(String target) {
        return name.equals(target);
    }
}
