package com.example.dell.jaimaaapp.modal;

public class Results {

    public String name,age,fathername,cheque;

    public Results() {
    }

    public Results(String name, String age, String fathername, String cheque) {
        this.name = name;
        this.age = age;
        this.fathername = fathername;
        this.cheque = cheque;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }
}
