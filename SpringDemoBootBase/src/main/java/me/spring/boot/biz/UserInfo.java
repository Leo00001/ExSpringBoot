package me.spring.boot.biz;

import java.util.List;

public class UserInfo {

    private String name;

    private int age;

    private List<BankCardInfo> bankCardList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<BankCardInfo> getBankCardList() {
        return bankCardList;
    }

    public void setBankCardList(List<BankCardInfo> bankCardList) {
        this.bankCardList = bankCardList;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", bankCardList=" + bankCardList +
                '}';
    }
}
