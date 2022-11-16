package com.kaeden.watchlist.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kaeden.watchlist.DTOs.UserDto;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch= FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Watchlist> watchlistSet= new HashSet<>();

//    @ManyToMany
//    private List<Watchlist> watchlist;

    //Constructors
    public User(UserDto userDto){
        if(userDto.getName()!=null){
            this.name=userDto.getName();
        }
        if (userDto.getPassword() != null) {
            this.password = userDto.getPassword();
        }
    }
    public User() {
    }

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //    public List<Watchlist> getWatchlist() {
//        return watchlist;
//    }
//
//    public void setWatchlist(List<Watchlist> watchlist) {
//        this.watchlist = watchlist;
//    }
}
