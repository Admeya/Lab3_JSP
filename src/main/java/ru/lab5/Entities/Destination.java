package ru.lab5.Entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ирина on 17.02.2017.
 */
@Entity
@Table
public class Destination {
    @Id
    @Column(name = "id_destination")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idDestination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_country")
    private Country country;

    @Column
    private String resort;

    @Column
    private String hotel;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "destination")
    private List<Tour> tourList;

    public Destination() {
    }

    public Integer getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Integer idDestination) {
        this.idDestination = idDestination;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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

    public List<Tour> getTourList() {
        return tourList;
    }

    public void setTourList(List<Tour> tourList) {
        this.tourList = tourList;
    }
}
