package com.softvision.foodfactory.service.impl;

import com.softvision.foodfactory.model.assemblyLineStage.AssemblyLineStage;
import com.softvision.foodfactory.service.IAssemblyLineStageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class AssemblyLineStageSetupImpl implements IAssemblyLineStageService {

    public AssemblyLineStageSetupImpl() {

    }

    @Override
    public List<AssemblyLineStage> setupAssemblyLineStages() {
        List<AssemblyLineStage> assemblyLineStages = new ArrayList<>();
        int assemblyLineStagesPerStore = new Random().nextInt(5 - 2 + 1) + 2; // [2, 4]
        IntStream.range(0, assemblyLineStagesPerStore)
                .forEach(counter -> {
                    AssemblyLineStage assemblyLineStage = new AssemblyLineStage();
                    assemblyLineStages.add(assemblyLineStage);
                });
        return assemblyLineStages;
    }
}
