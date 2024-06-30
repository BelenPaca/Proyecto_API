package service;

import model.MarsPhoto;
import model.interfaces.IMarsPhotos;
import java.util.stream.Collectors;
import java.util.List;

public class MarsPhotosServices implements IMarsPhotos {


    @Override
    public List<MarsPhoto> filterByIdSequential(List<MarsPhoto> photos, int id) {
        return photos.stream()
                .filter(photo -> photo.getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<MarsPhoto> filterByIdParallel(List<MarsPhoto> photos, int id) {
        return photos.parallelStream()
                .filter(photo -> photo.getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<MarsPhoto> filterByDateSequential(List<MarsPhoto> photos, String date) {
        return photos.stream()
                .filter(photo -> photo.getEarth_date().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<MarsPhoto> filterByDateParallel(List<MarsPhoto> photos, String date) {
        return photos.parallelStream()
                .filter(photo -> photo.getEarth_date().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<MarsPhoto> filterByNameSequential(List<MarsPhoto> photos, String name) {
        return photos.stream()
                .filter(photo -> photo.getCamera().getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<MarsPhoto> filterByNameParallel(List<MarsPhoto> photos, String name) {
        return photos.parallelStream()
                .filter(photo -> photo.getCamera().getName().equals(name))
                .collect(Collectors.toList());
    }
}
