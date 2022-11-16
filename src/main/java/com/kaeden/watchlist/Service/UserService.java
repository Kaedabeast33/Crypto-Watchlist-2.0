package com.kaeden.watchlist.Service;

import com.kaeden.watchlist.DTOs.UserDto;
import com.kaeden.watchlist.Entities.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    @Transactional
    public List<User> addUsers(UserDto userDto);
    List<String> userLogin(UserDto userDto);

}
