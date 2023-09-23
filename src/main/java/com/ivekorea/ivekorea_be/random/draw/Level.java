package com.ivekorea.ivekorea_be.random.draw;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Level {

    HIGH(0, 50000, 100000, 3, 100),
    MIDDLE(1, 10000, 49999, 9, 60),
    LOW(2, 0, 9999, 18, 20),;

    private final int lev;
    private final int minPrice;
    private final int maxPrice;
    private final int percentage;
    private final int maxCount;

}
