package com.kaeden.watchlist.Controller;

import com.kaeden.watchlist.DTOs.UserDto;
import com.kaeden.watchlist.Entities.User;
import com.kaeden.watchlist.Entities.Watchlist;
import com.kaeden.watchlist.Repository.UserRepository;
import com.kaeden.watchlist.Repository.WatchlistRepository;
import com.kaeden.watchlist.Service.UserService;
import com.kaeden.watchlist.Service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    WatchlistService watchlistService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WatchlistRepository watchlistRepository;

    @GetMapping("/getUsers")
    public List<User> getUsers(){
       return userRepository.findAll();
    }
    @PostMapping("/addUser")
    public List<String> addUser(@RequestBody UserDto userDto){
        List<String > response = new ArrayList<>();
        userService.addUsers(userDto);
        response.add("user Added successfully");
        response.add(userDto.getName());
        response.add(userDto.getPassword());

        return response;
    }
    @PostMapping("/{userId}/addWatchlist")
    public List<Watchlist> addWatchlist (@PathVariable Long userId){
        watchlistService.addWatchlist(userId);

       return  watchlistRepository.getWatchlistByUserId(userId);
    }

    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto){
        return userService.userLogin(userDto);
    }
}
