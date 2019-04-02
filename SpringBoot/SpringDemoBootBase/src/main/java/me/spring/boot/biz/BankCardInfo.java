package me.spring.boot.biz;

public class BankCardInfo {

    private String bankName;
    private String bankCode;
    private String bankAddress;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    @Override
    public String toString() {
        return "BankCardInfo{" +
                "bankName='" + bankName + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", bankAddress='" + bankAddress + '\'' +
                '}';
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }
}
