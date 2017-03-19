package ru.lab5.POJO;

import java.time.LocalDate;

/**
 * Created by Ирина on 17.02.2017.
 */
public class OrderDTO {
    private Integer idOrder;
    private LocalDate checkoutDate;

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }


    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", checkoutDate=" + checkoutDate +
                '}';
    }

}
