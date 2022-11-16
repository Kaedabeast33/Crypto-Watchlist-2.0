package com.kaeden.watchlist.Service;


import com.kaeden.watchlist.DTOs.UserDto;
import com.kaeden.watchlist.Entities.User;
import com.kaeden.watchlist.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public List<User> addUsers(UserDto userDto) {
        User user = new User(userDto);
        if (userRepository.findByName(user.getName()).isEmpty()) {
            userRepository.saveAndFlush(user);

        }
        return userRepository.findAll();
    }

    @Override
    public List<String> userLogin(UserDto userDto) {
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByName(userDto.getName());
        if (userOptional.isPresent()) {
            if(Objects.equals(userOptional.get().getPassword(), userDto.getPassword())){
                response.add("http://localhost:8080/home.html");
                response.add(String.valueOf(userOptional.get().getName()));
                response.add(String.valueOf(userOptional.get().getId()));
//               response.add( userOptional.get().getPassword());
//               response.add( userDto.getPassword());
            }else{
                response.add("Username or password incorrect");
            }
        }else{response.add("Username or password incorrect");}
        return response;

    }

}
