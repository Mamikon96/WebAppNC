package dao;

import models.Pilot;

import java.sql.SQLException;
import java.util.List;

public interface PilotDAO {
    boolean addPilot(Pilot pilot);
    boolean updatePilot(Pilot pilot);
    boolean deletePilot(Pilot pilot);
    Pilot getPilotById(long pilotId);
    Pilot getPilotByFullname(String fullname);
    List<Pilot> getAllPilots();
}
