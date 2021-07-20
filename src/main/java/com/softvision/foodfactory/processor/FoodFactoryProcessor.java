package com.softvision.foodfactory.processor;

import com.softvision.foodfactory.base.service.assemblyLineStage.AssemblyLineStageService;
import com.softvision.foodfactory.base.service.assemblyLineStage.impl.AssemblyLineStageServiceImpl;
import com.softvision.foodfactory.base.service.oven.OvenService;
import com.softvision.foodfactory.base.service.store.StoreService;
import com.softvision.foodfactory.base.service.store.impl.StoreServiceImpl;
import com.softvision.foodfactory.exception.CapacityExceededException;
import com.softvision.foodfactory.model.assemblyLineStage.AssemblyLineStage;
import com.softvision.foodfactory.model.oven.Oven;
import com.softvision.foodfactory.model.product.BlackBread;
import com.softvision.foodfactory.model.product.CornBread;
import com.softvision.foodfactory.model.product.Product;
import com.softvision.foodfactory.model.product.WhiteBread;
import com.softvision.foodfactory.model.store.Store;
import com.softvision.foodfactory.service.IAssemblyLineStageService;
import com.softvision.foodfactory.service.IDepositService;
import com.softvision.foodfactory.service.IFoodMakerService;
import com.softvision.foodfactory.service.IOvenService;
import com.softvision.foodfactory.service.impl.*;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FoodFactoryProcessor {

    private static final Logger LOGGER = Logger.getLogger(FoodFactoryProcessor.class.getName());
    public static final int COOKING_DURATION = 1000;

    private final IAssemblyLineStageService iAssemblyLineStageService;
    private final IDepositService depositService;
    private final IOvenService ovenService;

    public FoodFactoryProcessor() {
        iAssemblyLineStageService = new AssemblyLineStageSetupImpl();
        IFoodMakerService foodMakerService = new ProductMakerServiceImpl();
        depositService = new DepositServiceImpl(foodMakerService);
        ovenService = new OvenServiceImpl();
    }

    public void startProcess() {
        initializeData();
    }

    private void initializeData() {
        processFoodFactory(new StoreSetupServiceImpl(iAssemblyLineStageService, ovenService).createStores());
    }

    private void processFoodFactory(List<Store> stores) {
        Queue<Product> productsToAddOnAssemblyLineStage = depositService.getDeposit().getProducts();
        LOGGER.info(productsToAddOnAssemblyLineStage.size() + " products incoming to process");
        boolean isProductsToAddOnAssemblyStageEmpty = false;
        Collection<Queue<Product>> cookedProducts;
        AssemblyLineStageService assemblyLineStageService;
        for (Store store : stores) {
            Queue<Product> productsToGetCooked = new LinkedList<>();
            List<AssemblyLineStage> assemblyLineStages = store.getAssemblyLineStages();
            int totalNumberOfAssemblyLineStagesCounter = 0;
            for (AssemblyLineStage assemblyLineStage : assemblyLineStages) {
                assemblyLineStage.setPowerOn(true);
                assemblyLineStageService = new AssemblyLineStageServiceImpl(productsToGetCooked);
                for (int i = 0; i < assemblyLineStage.getMaximumProductsOnStageLine(); i++) {
                    if (isProductsToAddOnAssemblyStageEmpty) {
                        break;
                    }
                    if (productsToAddOnAssemblyLineStage.size() != 0) { // check if there are still any products to process further on
                        assemblyLineStageService.putAfter(productsToAddOnAssemblyLineStage.peek());
                        productsToAddOnAssemblyLineStage.poll();
                    } else {
                        LOGGER.info("No more products to process onto");
                        isProductsToAddOnAssemblyStageEmpty = true;
                    }
                }
                totalNumberOfAssemblyLineStagesCounter++;

                if (totalNumberOfAssemblyLineStagesCounter == store.getAssemblyLineStages().size()) {
                    cookedProducts = getProductsIntoOvenAndRetrieveThem(productsToGetCooked, store.getOvens(), productsToAddOnAssemblyLineStage.size());
                    long count = cookedProducts.stream()
                            .mapToLong(Collection::size)
                            .sum();
                    Queue<Product> cookedProductsToDeliver = cookedProducts.stream()
                            .flatMap(Collection::stream)
                            .collect(Collectors.toCollection(LinkedList::new));
                    cookedProductsToDeliver.forEach(assemblyLineStageService::take);
                    StoreService storeService = new StoreServiceImpl(cookedProductsToDeliver);
                    cookedProductsToDeliver.forEach(storeService::take);
                    LOGGER.info(count + " cooked products ready for delivery");
                }
                assemblyLineStage.setPowerOn(false);
            }
            if (isThereNeedForAnotherStore(productsToAddOnAssemblyLineStage.size())) {
                LOGGER.info("No need for another store");
                break;
            }
        }
    }

    private Collection<Queue<Product>> getProductsIntoOvenAndRetrieveThem(Queue<Product> incomingProductsToGetCooked, List<Oven> ovens, int availableProductsToGetCooked) {
        // we assume that in an oven only 10 bread products can fit in
        Queue<Product> productsToGetInsideTheOven = new LinkedList<>();
        OvenService ovenService = new com.softvision.foodfactory.base.service.oven.impl.OvenServiceImpl(productsToGetInsideTheOven);
        int totalPossibleCookingProducts = (ovens.size()) * 10;
        Map<UUID, Queue<Product>> ovenToQueueMap = new LinkedHashMap<>();
        Queue<Product> products;
        boolean value = areThereAnyProductsLeftToGetCooked(availableProductsToGetCooked);
        for (Oven oven : ovens) {
            if (incomingProductsToGetCooked.size() == 0) {
                break;
            }
            oven.setOvenOn(true);
            products = new LinkedList<>();
            for (int i = 0; i < totalPossibleCookingProducts; i++) {
                if (incomingProductsToGetCooked.size() == 0) {
                    break;
                }
                if (oven.getIncomingProducts().size() == 10) {
                    oven.setOvenFull(true);
                    break;
                }
                oven.addProductInOven(incomingProductsToGetCooked.peek());
                products.add(incomingProductsToGetCooked.peek());
                try {
                    ovenService.put(incomingProductsToGetCooked.peek());
                } catch (CapacityExceededException e) {
                    e.printStackTrace();
                }
                incomingProductsToGetCooked.poll();
            }
            ovenToQueueMap.put(oven.getId(), products);
        }
        return cookProductsAndRetrieveThem(ovenToQueueMap, ovenService);
    }

    private Collection<Queue<Product>> cookProductsAndRetrieveThem(Map<UUID, Queue<Product>> ovenToQueueMap, OvenService ovenService) {
        Collection<Queue<Product>> ovenToProductsMap = ovenToQueueMap.values();
        List<UUID> ovenIds = new ArrayList<>();
        for (Map.Entry<UUID, Queue<Product>> entry : ovenToQueueMap.entrySet()) {
            ovenIds.add(entry.getKey());
        }
        try {
            ovenIds.forEach(ovenService::turnOn);
            cookProducts(ovenToProductsMap);
            ovenIds.forEach(ovenService::turnOff);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ovenToQueueMap.values();
    }

    private void cookProducts(Collection<Queue<Product>> ovenToProductsMap) throws InterruptedException {
        Thread.sleep(COOKING_DURATION);
        ovenToProductsMap.stream()
                .flatMap(Collection::stream)
                .forEach(value -> {
                    if (value instanceof WhiteBread) {
                        ((WhiteBread) value).setCooked(true);
                    }
                    if (value instanceof BlackBread) {
                        ((BlackBread) value).setCooked(true);
                    }
                    if (value instanceof CornBread) {
                        ((CornBread) value).setCooked(true);
                    }
                });
    }

    private boolean areThereAnyProductsLeftToGetCooked(int size) {
        return size > 0;
    }

    private boolean isThereNeedForAnotherStore(int totalProducts) {
        return totalProducts == 0;
    }
}
