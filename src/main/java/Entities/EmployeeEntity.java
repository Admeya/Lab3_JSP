package Entities;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Ирина on 17.02.2017.
 */
@XmlType(propOrder = {"idEmployee", "name", "surname", "phone", "login", "password", "orderssByIdEmployee"}, name = "EmployeeEntity")
@XmlRootElement
public class EmployeeEntity implements Serializable {
    private Integer idEmployee;
    private String name;
    private String surname;
    private String phone;
    private String login;
    private String password;
    private Collection<OrderEntity> orderssByIdEmployee;

    public static String tableName = "employee";
    public static String columnId = "id_employee";
    public static String columnName = "name";
    public static String columnSurname = "surname";
    public static String columnPhone = "phone";
    public static String columnLogin = "login";
    public static String columnPass = "password";

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
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

        EmployeeEntity that = (EmployeeEntity) o;

        if (!idEmployee.equals(that.idEmployee)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        return login != null ? login.equals(that.login) : that.login == null;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "idEmployee=" + idEmployee +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = idEmployee.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }

    public Collection<OrderEntity> getOrderssByIdEmployee() {
        return orderssByIdEmployee;
    }

    public void setOrderssByIdEmployee(Collection<OrderEntity> orderssByIdEmployee) {
        this.orderssByIdEmployee = orderssByIdEmployee;
    }
}
