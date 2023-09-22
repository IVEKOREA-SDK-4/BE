package com.ivekorea.ivekorea_be.random.level;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Level {

    HIGH(50000, 100000, 5),
    MIDDLE(10000, 49999, 15),
    LOW(0, 9999, 30);

    private final int minPrice;
    private final int maxPrice;
    private final int percentage;

}
