package com.ad.platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ad.platform.model.User;
import com.ad.platform.model.UserType;
import com.ad.platform.repository.UserRepository;
import com.ad.platform.repository.UserTypeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api(value="user-management", description="Operations pertaining to users in Property Management Application")
public class UserController {
    
    @Value("${name}")
    private String name;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;
    
    @ApiOperation(value="Get the list of enrolled users", response=Iterable.class)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Iterable<User> listAllUsers() {
        return userRepository.findAll();
    }
    
    @ApiOperation(value="Get the master data of user types", response=Iterable.class)
    @RequestMapping(value="/types", method=RequestMethod.GET)
    public Iterable<UserType> getAllUserTypes() {
        return userTypeRepository.findAll();
    }

    @ApiOperation(value="Does no DB operation. Can be used to get the sample json for POST request.")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser() {
        return "Please perform a POST request with appropriate json input. Sample - \n" + "{\n"
                + "\t\"firstName\":\"Abir\",\n" + "\t\"middleName\":\"\",\n" + "\t\"lastName\":\"Datta\",\n"
                + "\t\"emailId\":\"a@b.com\",\n" + "\t\"password\":\"password\",\n" + "\t\"city\":\"Kolkata\",\n"
                + "\t\"country\":\"India\",\n" + "\t\"userType\":{\n" + "\t\t\"userTypeId\":2\n" + "\t}\n" + "}\n";
    }

    @ApiOperation(value="Adds a new user.")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@RequestBody User user) {
        userRepository.save(user);
        return "Added User " + user.getFirstName() + " " + user.getLastName();
    }
    
    @ApiOperation(value="Delete user based on input first name.")
    @RequestMapping(value = "/delete/{firstName}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String firstName) {
        List<User> deletedUsers = userRepository.removeByFirstName(firstName);
        if (deletedUsers.size() > 0)
            return "Deleted User/s with First Name " + firstName;
        else
            return "No users found with First Name " + firstName;
    }
}
