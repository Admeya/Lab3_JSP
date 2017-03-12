package ru.lab5.services;

import ru.lab5.Entities.CountryEntity;
import ru.lab5.Entities.DestinationEntity;

import java.util.List;

/**
 * Created by Ирина on 02.03.2017.
 */
public interface IDestinationService {

    List<DestinationEntity> getAllDests();

    boolean updateDestination(DestinationEntity dest);

    boolean deleteDestination(int destId);

    DestinationEntity getDestByID(int destId);

    boolean addDestination(DestinationEntity dest);
}
