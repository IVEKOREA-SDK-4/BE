package com.ivekorea.ivekorea_be.random.level;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Level {

    HIGH(0, 50000, 100000, 5, 100),
    MIDDLE(1, 10000, 49999, 15, 60),
    LOW(2, 0, 9999, 30, 20),;

    private final int lev;
    private final int minPrice;
    private final int maxPrice;
    private final int percentage;
    private final int maxCount;

}
