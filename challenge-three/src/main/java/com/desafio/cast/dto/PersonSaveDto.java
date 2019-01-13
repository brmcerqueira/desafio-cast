package com.desafio.cast.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonSaveDto {
    private Long id;
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    @NotNull
    @Size(min = 3, max = 50)
    private String street;
    @NotNull
    private Integer number;
    @NotNull
    @Size(min = 3, max = 50)
    private String neighborhood;
    @NotNull
    @Size(min = 3, max = 50)
    private String city;
    @NotNull
    @Size(min = 2, max = 2)
    private String state;
    @NotNull
    @Size(min = 8, max = 9)
    private String cellphone;
    @NotNull
    @Size(min = 8, max = 8)
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
