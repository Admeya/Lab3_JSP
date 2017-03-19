package ru.lab5.POJO;

import ru.lab5.Entities.Destination;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by Ирина on 17.02.2017.
 */
public class TourDTO {
    private Integer idTour;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private Integer cost;

    public TourDTO() {
    }

    public Integer getIdTour() {
        return idTour;
    }

    public void setIdTour(Integer idTour) {
        this.idTour = idTour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
