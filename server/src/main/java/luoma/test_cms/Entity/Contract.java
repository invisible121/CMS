package luoma.test_cms.Entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Contract {
    int id;
    int cus_id;
    String num;
    String name;
    int user_id;
    String customer;
    String content;
    Date beginTime;
    Date endTime;
    int del;

//    public Contract(int cus_id, String num, String name, int user_id, String customer, String content, Date beginTime, Date endTime, int del) {
//        this.cus_id = cus_id;
//        this.num = num;
//        this.name = name;
//        this.user_id = user_id;
//        this.customer = customer;
//        this.content = content;
//        this.beginTime = beginTime;
//        this.endTime = endTime;
//        this.del = del;
//    }

    public Contract(int id, int cus_id, String num, String name, int user_id, String customer, String content, Date beginTime, Date endTime, int del) {
        this.cus_id = cus_id;
        this.num = num;
        this.name = name;
        this.user_id = user_id;
        this.customer = customer;
        this.content = content;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.del = del;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

//    public Contract(int id, int cus_id, String num, String name, int user_id, String customer, String content, Date beginTime, Date endTime, int del) {
//        this.id = id;
//        this.cus_id = cus_id;
//        this.num = num;
//        this.name = name;
//        this.user_id = user_id;
//        this.customer = customer;
//        this.content = content;
//        this.beginTime = beginTime;
//        this.endTime = endTime;
//        this.del = del;
//    }
}
