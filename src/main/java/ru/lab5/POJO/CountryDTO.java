package ru.lab5.POJO;

/**
 * Created by Ирина on 17.02.2017.
 */
public class CountryDTO {
    private Integer idCountry;
    private String nameCountry;

    public CountryDTO() {
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
}
