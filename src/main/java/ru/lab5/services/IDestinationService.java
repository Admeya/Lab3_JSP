package ru.lab5.services;

import ru.lab5.Entities.Destination;
import ru.lab5.POJO.DestinationDTO;

import java.util.List;

/**
 * Created by Ирина on 02.03.2017.
 */
public interface IDestinationService {

    List<Destination> getAllDests();

    boolean updateDestination(DestinationDTO dest);

    boolean deleteDestination(int destId);

    Destination getDestByID(int destId);

    boolean addDestination(DestinationDTO dest);
}
