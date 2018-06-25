package org.bill.logger.model.pojo;


import java.io.Serializable;

/**
 * 作者：Bill
 * 时间：2018-06-12 15:55
 * 描述：用户信息ViewModel
 */
public class User implements Serializable
{
    private String id;
    private String userName;
    private int age;
    private String job;
    private String password;
    private String headImageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", password='" + password + '\'' +
                ", headImageUrl='" + headImageUrl + '\'' +
                '}';
    }
}
