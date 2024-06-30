package controllers;

import model.MarsPhoto;
import service.ConsumerApi;
import service.MarsPhotosServices;

import java.util.List;

public class Container {

    private ConsumerApi consumerApi;
    private MarsPhotosServices marsPhotosServices;

    public Container() {
        consumerApi = new ConsumerApi();
        marsPhotosServices = new MarsPhotosServices();
    }

    public List<MarsPhoto> loadTable() throws Exception {
        return consumerApi.getAllMarsPhotos();
    }

    public List<MarsPhoto> getByDateSequential(List<MarsPhoto> marsPhotos, String aux) throws Exception {
        return measureExecutionTime(
                () -> marsPhotosServices.filterByDateSequential(marsPhotos, aux),
                "Filtering by sequential date"
        );
    }

    public List<MarsPhoto> getByDateParallel(String aux) throws Exception {
        return measureExecutionTime(
                () -> marsPhotosServices.filterByDateParallel(consumerApi.getAllMarsPhotos(), aux),
                        "Filtering by parallel date"
        );
    }

    public List<MarsPhoto> getByIdSequential(int id) throws Exception {
        return measureExecutionTime(
                () -> marsPhotosServices.filterByIdSequential(consumerApi.getAllMarsPhotos(), id),
                "Filtering by sequential Id"
        );
    }

    public List<MarsPhoto> getByIdParallel(int id) throws Exception {
        return measureExecutionTime(
                () -> marsPhotosServices.filterByIdParallel(consumerApi.getAllMarsPhotos(), id),
                "Filtering by parallel ID"
        );
    }

    public List<MarsPhoto> getByNameSequential(String aux) throws Exception {
        return measureExecutionTime(
                () -> marsPhotosServices.filterByNameSequential(consumerApi.getAllMarsPhotos(), aux),
                "Filter by sequential name"
        );
    }

    public List<MarsPhoto> getByNameParallel(String aux) throws Exception {
        return measureExecutionTime(
                () -> marsPhotosServices.filterByNameParallel(consumerApi.getAllMarsPhotos(), aux),
                "Filtering by parallel name"
        );
    }

    private <T> T measureExecutionTime(SupplierWithException<T> task, String description) throws Exception {
        long startTime = System.currentTimeMillis();
        T result = task.get();
        long endTime = System.currentTimeMillis();
        System.out.printf("Duration of %s: %d ms%n", description, (endTime - startTime));
        return result;
    }

    @FunctionalInterface
    private interface SupplierWithException<T> {
        T get() throws Exception;
    }
}

