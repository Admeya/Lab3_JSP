package ru.lab5.Entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ирина on 17.02.2017.
 */
@Entity
@Table
public class Country {
    @Id
    @Column(name = "id_country")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCountry;

    @Column(name = "name_country")
    private String nameCountry;

    @Version
    private int version;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "country")
    private List<Destination> destinationsList;

    public Country() {
    }

    public Integer getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(Integer idCountry) {
        this.idCountry = idCountry;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Destination> getDestinationsList() {
        return destinationsList;
    }

    public void setDestinationsList(List<Destination> destinationsList) {
        this.destinationsList = destinationsList;
    }
}
