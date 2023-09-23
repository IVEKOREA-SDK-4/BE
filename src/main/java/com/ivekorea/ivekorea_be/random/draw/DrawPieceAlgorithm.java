package com.ivekorea.ivekorea_be.random.draw;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DrawPieceAlgorithm {

    private final List<Pair<String, Double>> candidates;

    public DrawPieceAlgorithm(List<Pair<String, Integer>> target) {
        // 총 가중치 합 계산
        double totalWeight = 1;
        for (Pair<String, Integer> pair : target) {
            totalWeight += pair.weight;
        }

        // 주어진 가중치를 백분율로 치환 => 가중치 / 총 가중치
        List<Pair<String, Double>> tempList = new ArrayList<>();
        for (Pair<String, Integer> pair : target) {
            tempList.add(new Pair<>(pair.word, pair.weight / totalWeight));
        }

        // 가중치의 오름차순으로 정렬
        tempList.sort(Comparator.comparingDouble(p -> p.weight));
        this.candidates = tempList;
    }

    public String getRandom() {
        // 랜덤 기준점 설정
        final double pivot = Math.random();

        // 가중치의 오름차순으로 원소들을 순회하며 가중치를 누적
        double acc = 0;
        for (Pair<String, Double> pair : candidates) {
            acc += pair.weight;

            // 누적 가중치 값이 기준점 이상이면 종료
            if (pivot <= acc) {
                return pair.word;
            }
        }

        return null;
    }
}