package model.interfaces;
import java.util.List;
import model.MarsPhoto;

public interface IMarsPhotos {

    List<MarsPhoto> filterByIdSequential(List<MarsPhoto> photos, int id);

    List<MarsPhoto> filterByIdParallel(List<MarsPhoto> photos, int id);

    List<MarsPhoto> filterByDateSequential(List<MarsPhoto> photos, String date);

    List<MarsPhoto> filterByDateParallel(List<MarsPhoto> photos, String date);

    List<MarsPhoto> filterByNameSequential(List<MarsPhoto> photos, String name);

    List<MarsPhoto> filterByNameParallel(List<MarsPhoto> photos, String name);


}
