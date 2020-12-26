package ru.gureev.meteringandmonitorsystem.enities;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

import ru.gureev.meteringandmonitorsystem.EnumUserType;

@IgnoreExtraProperties
public class User implements Serializable {

    private String id;
    private String login;
    private String name;
    private String lastName;
    private String fatherName;
    private String position;
    private String personalNum;
    private String brigadeNum;
    private String departmentNum;
    private EnumUserType enumUserType;

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if (position.equals("контролёр")) {
            enumUserType = EnumUserType.CONTROLLER;
        } else if (position.equals("диспетчер")) {
            enumUserType = EnumUserType.DISPATCHER;
        } else {
            enumUserType = EnumUserType.NONE;
        }
        this.position = position;
    }

    public String getPersonalNum() {
        return personalNum;
    }

    public void setPersonalNum(String personalNum) {
        this.personalNum = personalNum;
    }

    public String getBrigadeNum() {
        return brigadeNum;
    }

    public void setBrigadeNum(String brigadeNum) {
        this.brigadeNum = brigadeNum;
    }

    public String getDepartmentNum() {
        return departmentNum;
    }

    public void setDepartmentNum(String departmentNum) {
        this.departmentNum = departmentNum;
    }

    public EnumUserType getEnumUserType() {
        return enumUserType;
    }

    public void setEnumUserType(EnumUserType enumUserType) {
        this.enumUserType = enumUserType;
    }

    @Override
    public String toString() {
        return personalNum + ", " + lastName + " " + name + " " + fatherName;
    }
}
