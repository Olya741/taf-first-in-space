package ru.firstinspace.abramovicho.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchService {
    public static Map<String, Integer> getParams(int priceMin, int priceMax) {
        Map<String, Integer> params = new HashMap<>();
        params.put("price_min", priceMin);
        params.put("price_max", priceMax);
        return params;
    }

    public static Map<String, Double> getParams(double priceMin, double priceMax) {
        Map<String, Double> params = new HashMap<>();
        params.put("price_min", priceMin);
        params.put("price_max", priceMax);
        return params;
    }

    public static Map<String, String> getParams(String priceMin, String priceMax) {
        Map<String, String> params = new HashMap<>();
        params.put("price_min", priceMin);
        params.put("price_max", priceMax);
        return params;
    }

    private static List<Integer> getJoinedList(List<Integer> price, List<Integer> priceWithSale) {
        List<Integer> joinedList = new ArrayList<>();
        for (int i = 0; i < price.size(); i++) {
            if (priceWithSale.get(i) > 0) {
                joinedList.add(priceWithSale.get(i));
            } else {
                joinedList.add(price.get(i));
            }
        }
        return joinedList;
    }

    public static int findPriceOutOfRange(List<Integer> price, List<Integer> priceWithSale, int min, int max) {
        int numberOutOfRange = 0;
        List<Integer> joinedList = getJoinedList(price, priceWithSale);
        for (int i = 0; i < joinedList.size(); i++) {
            if (joinedList.get(i) < min | joinedList.get(i) > max) {
                numberOutOfRange = numberOutOfRange + 1;
            }
        }
        return numberOutOfRange;
    }
}
