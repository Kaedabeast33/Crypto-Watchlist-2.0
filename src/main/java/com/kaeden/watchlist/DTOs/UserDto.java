package com.kaeden.watchlist.DTOs;

import com.kaeden.watchlist.Entities.User;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class UserDto implements Serializable {
    private Long id;

    private String name;

    private String password;

    public UserDto(User user) {
        if(user.getId()!=null){
            this.id= user.getId();
        }if(user.getName()!=null){
            this.name=user.getName();
        }if(user.getPassword()!=null){
            this.password=user.getPassword();
        }
    }
    public UserDto(){

    }

    //GETTERS
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
