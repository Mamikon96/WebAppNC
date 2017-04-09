package dao;

import models.Ration;

import java.util.List;

public interface RationDAO {
    boolean addRation(Ration ration);
    boolean updateRation(Ration ration);
    boolean deleteRation(Ration ration);
    Ration getRationById(long id);
    Ration getRationByDescription(String description);
    List<Ration> getAllRations();
}
