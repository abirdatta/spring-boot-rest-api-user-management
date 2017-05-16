package com.ad.platform.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ad.platform.model.User;
import com.ad.platform.model.UserType;
import com.ad.platform.repository.UserRepository;
import com.ad.platform.repository.UserTypeRepository;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api(value = "user-management", description = "Operations pertaining to users in Property Management Application")
public class UserController {

    @Value("${name}")
    private String name;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private String errorMessage = "Error Occurred while processing the request '%s'. Exception Message - %s";
    private String infoMessage = "Request Uri is '%s'.";
    private String requestInputMessage = "Input to the request is '%s'.";
    private String responseOutputMessage = "Output of the request is %s";

    @ApiOperation(value = "Get the list of enrolled users", response = Iterable.class)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Iterable<User> listAllUsers(HttpServletRequest request, HttpServletResponse response) {
        logRequest(request, "");
        List<User> userList = new ArrayList<User>();
        try {
            userList = (List<User>) userRepository.findAll();
            logResponse(userList);
            return userList;
        } catch (Exception e) {
            logException(request, response, e);
            return userList;
        }
    }

    @ApiOperation(value = "Get the master data of user types", response = Iterable.class)
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public Iterable<UserType> getAllUserRoles(HttpServletRequest request, HttpServletResponse response) {
        logRequest(request, "");
        List<UserType> userRoleList = new ArrayList<UserType>();
        try {
            userRoleList = (List<UserType>) userTypeRepository.findAll();
            logResponse(userRoleList);
            return userRoleList;
        } catch (Exception e) {
            logException(request, response, e);
            return userRoleList;
        }
    }

    @ApiOperation(value = "Does no DB operation. Can be used to get the sample json for POST request.")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(HttpServletRequest request, HttpServletResponse response) {
        return "Please perform a POST request with appropriate json input. Sample - \n" + "{\n"
                + "\t\"firstName\":\"Abir\",\n" + "\t\"middleName\":\"\",\n" + "\t\"lastName\":\"Datta\",\n"
                + "\t\"emailId\":\"a@b.com\",\n" + "\t\"password\":\"password\",\n" + "\t\"city\":\"Kolkata\",\n"
                + "\t\"country\":\"India\",\n" + "\t\"userType\":{\n" + "\t\t\"userTypeId\":2\n" + "\t}\n" + "}\n";
    }

    @ApiOperation(value = "Adds a new user.")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        logRequest(request, user.toString());
        try {
            userRepository.save(user);
            return "Added User " + user.getFirstName() + " " + user.getLastName();
        } catch (Exception e) {
            logException(request, response, e);
            return "Request Failed.";            
        }
        
    }

    @ApiOperation(value = "Delete user based on input first name.")
    @RequestMapping(value = "/delete/firstName/{firstName}", method = RequestMethod.GET)
    public String deleteUserByFirstName(@PathVariable String firstName, HttpServletRequest request,
            HttpServletResponse response) {
        logRequest(request, firstName);
        List<User> deletedUsers;
        try {
            deletedUsers = userRepository.removeByFirstName(firstName);
            if (deletedUsers.size() > 0)
                return "Deleted User/s with First Name " + firstName;
            else
                return "No users found with First Name " + firstName;
        } catch (Exception e) {
            logException(request, response, e);
            return "Error Occurred while processing the request. Exception Message - \n" + e.getMessage();
        }
    }

    private void logRequest(HttpServletRequest request, String input) {
        LOGGER.info(String.format(infoMessage, request.getRequestURI()));
        LOGGER.debug(String.format(requestInputMessage, input));
    }
    
    private void logResponse(Object obj) {
        LOGGER.debug(String.format(responseOutputMessage, new Gson().toJson(obj)));
    }

    private void logException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        LOGGER.debug(String.format(errorMessage, request.getRequestURI(), e.getMessage()));
    }

}
