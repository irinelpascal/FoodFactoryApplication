package com.softvision.foodfactory.model.store;

import com.softvision.foodfactory.model.assemblyLineStage.AssemblyLineStage;
import com.softvision.foodfactory.model.oven.Oven;

import java.util.List;
import java.util.UUID;

public class Store {

    private final UUID id = UUID.randomUUID();
    private final List<AssemblyLineStage> assemblyLineStages;
    private final List<Oven> ovens;

    public Store(List<AssemblyLineStage> assemblyLineStages, List<Oven> ovens) {
        this.assemblyLineStages = assemblyLineStages;
        this.ovens = ovens;
    }

    public List<Oven> getOvens() {
        return ovens;
    }

    public List<AssemblyLineStage> getAssemblyLineStages() {
        return assemblyLineStages;
    }

}
