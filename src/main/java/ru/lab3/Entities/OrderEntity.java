package ru.lab3.Entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Ирина on 17.02.2017.
 */
public class OrderEntity implements Serializable{
    public static String tableName = "order";
    public static String columnId = "id_order";
    public static String columnIdEmployee = "id_employee";
    public static String columnIdClient = "id_client";
    public static String columnIdTour = "id_tour";
    public static String columnCheckoutDate = "checkout_date";
    private Integer idOrder;
    private Integer idEmployee;
    private Integer idClient;
    private Integer idTour;
    private LocalDate checkoutDate;
    private String nameClient;
    private String surnameClient;
    private String phoneClient;
    private String nameTour;

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getIdTour() {
        return idTour;
    }

    public void setIdTour(Integer idTour) {
        this.idTour = idTour;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getSurnameClient() {
        return surnameClient;
    }

    public void setSurnameClient(String surnameClient) {
        this.surnameClient = surnameClient;
    }

    public String getPhoneClient() {
        return phoneClient;
    }

    public void setPhoneClient(String phoneClient) {
        this.phoneClient = phoneClient;
    }

    public String getNameTour() {
        return nameTour;
    }

    public void setNameTour(String nameTour) {
        this.nameTour = nameTour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (idOrder != null ? !idOrder.equals(that.idOrder) : that.idOrder != null) return false;
        if (idEmployee != null ? !idEmployee.equals(that.idEmployee) : that.idEmployee != null) return false;
        if (idClient != null ? !idClient.equals(that.idClient) : that.idClient != null) return false;
        if (idTour != null ? !idTour.equals(that.idTour) : that.idTour != null) return false;
        if (checkoutDate != null ? !checkoutDate.equals(that.checkoutDate) : that.checkoutDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOrder != null ? idOrder.hashCode() : 0;
        result = 31 * result + (idEmployee != null ? idEmployee.hashCode() : 0);
        result = 31 * result + (idClient != null ? idClient.hashCode() : 0);
        result = 31 * result + (idTour != null ? idTour.hashCode() : 0);
        result = 31 * result + (checkoutDate != null ? checkoutDate.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "idOrder=" + idOrder +
                ", idEmployee=" + idEmployee +
                ", idClient=" + idClient +
                ", idTour=" + idTour +
                ", checkoutDate=" + checkoutDate +
                '}';
    }

}
