package com.example.ryanxr.contentprovider;

/**
 * Created by RyanxR on 12/15/2015.
 */
public class Company
{
    private int id;
    private int tel;
    private String name;
    private String address;
    public Company(){}

    public Company(int tel, String name, String address) {
        super();
        this.name = name;
        this.tel = tel;
        this.address = address;
    }

    //getters & setters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getTel() {
        return tel;
    }
    public String getAddress() {
        return address;
    }
    public void setId(int ID) {
        id = ID;
    }
    public void setTel(int TEL) {
        tel = TEL;
    }
    public void setName(String NAME) {
        name = NAME;
    }
    public void setAddress(String ADDRESS) {
        address = ADDRESS;
    }

  /*  @Override
    public String toString() {
        return "Company [id = " + id + ", name = " + name + ", contact = " + contact +", address = " +address + "]";
    } */
}
