package com.softvision.foodfactory.service.impl;

import com.softvision.foodfactory.model.oven.Oven;
import com.softvision.foodfactory.service.IOvenService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class OvenServiceImpl implements IOvenService {

    @Override
    public List<Oven> setupOvens() {
        List<Oven> ovens = new ArrayList<>();
        int numberOfOvensPerStore = new Random().nextInt(6 - 3 + 1) + 3; // [3, 5]
        IntStream.range(0, numberOfOvensPerStore)
                .forEach(oven -> ovens.add(new Oven()));
        return ovens;
    }
}
