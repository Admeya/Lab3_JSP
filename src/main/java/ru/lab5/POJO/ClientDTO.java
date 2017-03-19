package ru.lab5.POJO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Ирина on 17.02.2017.
 */
public class ClientDTO {
    private Integer idClient;
    private String name;
    private String surname;
    private String middlename;
    private Date birthdate;
    private String passportSerNum;
    private String phone;
    private String login;
    private String password;

    public ClientDTO() {
    }

    public ClientDTO(String name, String surname, String middlename, Date birthdate, String passportSerNum,
                     String phone, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.birthdate = birthdate;
        this.passportSerNum = passportSerNum;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }

    public ClientDTO(int id, String name, String surname, String middlename, Date birthdate, String passportSerNum,
                     String phone, String login, String password) {
        this.idClient = id;
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.birthdate = birthdate;
        this.passportSerNum = passportSerNum;
        this.phone = phone;
        this.login = login;
        this.password = password;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassportSerNum() {
        return passportSerNum;
    }

    public void setPassportSerNum(String passportSerNum) {
        this.passportSerNum = passportSerNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientDTO that = (ClientDTO) o;

        if (!idClient.equals(that.idClient)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;
        if (passportSerNum != null ? !passportSerNum.equals(that.passportSerNum) : that.passportSerNum != null)
            return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        return login != null ? login.equals(that.login) : that.login == null;
    }

    @Override
    public int hashCode() {
        int result = idClient.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (passportSerNum != null ? passportSerNum.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", birthdate=" + birthdate +
                ", passportSerNum='" + passportSerNum + '\'' +
                ", phone='" + phone + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
