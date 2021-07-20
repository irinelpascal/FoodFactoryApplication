package com.softvision.foodfactory.service.impl;

import com.softvision.foodfactory.model.oven.Oven;
import com.softvision.foodfactory.model.store.Store;
import com.softvision.foodfactory.service.IAssemblyLineStageService;
import com.softvision.foodfactory.service.IOvenService;
import com.softvision.foodfactory.service.IStoreSetupService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class StoreSetupServiceImpl implements IStoreSetupService {

    private final IAssemblyLineStageService assemblyLineStage;
    private final IOvenService ovenService;

    public StoreSetupServiceImpl(IAssemblyLineStageService assemblyLineStage, IOvenService ovenService) {
        this.assemblyLineStage = assemblyLineStage;
        this.ovenService = ovenService;
    }

    @Override
    public List<Store> createStores() {
        int storesCount = new Random().nextInt(6 - 3 + 1) + 3; // [3, 5]
        List<Store> stores = new ArrayList<>();
        IntStream.range(0, storesCount)
                .forEach(store -> {
                    List<Oven> ovens = ovenService.setupOvens();
                    stores.add(new Store(assemblyLineStage.setupAssemblyLineStages(), ovens));
                });
        return stores;
    }
}
