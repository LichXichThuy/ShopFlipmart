package groupthree.shopflipmart.dto;

import groupthree.shopflipmart.entity.*;

import javax.persistence.*;
import java.util.Set;

public class UserDto {

    private int id;

    private String email;

    private String name;

    private int phoneNum;

    private String address;

    private String addressComment;

    private int roleId;

    public void getUserDto(User user){
        this.address = user.getAddress();
        this.roleId = user.getRole().getId();
        this.id = user.getId();
        this.name = user.getName();
        this.addressComment = user.getAddressComment();
        this.email = user.getEmail();
        this.phoneNum = user.getPhoneNum();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressComment() {
        return addressComment;
    }

    public void setAddressComment(String addressComment) {
        this.addressComment = addressComment;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
