package luoma.test_cms.Entity;

import lombok.Data;

@Data
public class Customer {
    int id;
    String num;
    String name;
    String address;
    String tel;
    String fax;
    String code;
    String bank;
    String account;

    public int getId() {
        return id;
    }

    public String getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

    public String getFax() {
        return fax;
    }

    public String getCode() {
        return code;
    }

    public String getBank() {
        return bank;
    }

    public String getAccount() {
        return account;
    }

    public int getDel() {
        return del;
    }

    int del = 0;

    public Customer(String name, String address, String tel, String fax, String code, String bank, String account) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.fax = fax;
        this.code = code;
        this.bank = bank;
        this.account = account;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setDel(int del) {
        this.del = del;
    }
}
