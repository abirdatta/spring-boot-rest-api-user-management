package com.ad.platform.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="user_type")
public class UserType {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userTypeId;
    private String type;
    private String description;
    
    @JsonBackReference
    @OneToMany
    @JoinColumn(name="user_type_id")
    private Set<User> users;
    
    public UserType(){
        
    }
    
    public UserType(int id, String type, String description){
        this.setUserTypeId(id);
        this.setType(type);
        this.setDescription(description);
    }
    
    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    
}
