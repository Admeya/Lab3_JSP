package ru.lab5.POJO;

import ru.lab5.Entities.Tour;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ирина on 17.02.2017.
 */
public class DestinationDTO {
    private Integer idDestination;
    private Integer idCountry;
    private String resort;
    private String hotel;

    public DestinationDTO() {
    }

    public Integer getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Integer idDestination) {
        this.idDestination = idDestination;
    }

    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
    }

    public String getResort() {
        return resort;
    }

    public void setResort(String resort) {
        this.resort = resort;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }
}
