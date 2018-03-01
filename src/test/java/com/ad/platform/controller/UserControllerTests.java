package com.ad.platform.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import com.ad.platform.model.User;
import com.ad.platform.model.UserType;
import com.ad.platform.repository.UserRepository;
import com.ad.platform.repository.UserTypeRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserTypeRepository userTypeRepository;

    @Test
    @WithMockUser(username="abir", password="password")
    public void testUserList() throws Exception {
        given(this.userRepository.findAll()).willReturn(buildUser());
        this.mockMvc.perform(get("/user/list")).andExpect(status().isOk()).andExpect(content().json(
                "[{\"userId\":1,\"firstName\":\"Abir\",\"middleName\":\"\",\"lastName\":\"Datta\",\"emailId\":\"a@b.com\",\"password\":\"password\",\"city\":\"Kolkata\",\"country\":\"India\",\"userType\":{\"userTypeId\":2,\"type\":\"Buyer\",\"description\":\"Want to buy the property\"}}]"));
    }
    
    @Test
    @WithMockUser(username="abir", password="password")
    public void testUserTypes() throws Exception {
        given(this.userTypeRepository.findAll()).willReturn(buildUserTypes());
        this.mockMvc.perform(get("/user/types")).andExpect(status().isOk()).andExpect(content().json(
                "[{\"userTypeId\":2,\"type\":\"Buyer\",\"description\":\"Want to buy the property\"}]"));
    }
    
    private Iterable<UserType> buildUserTypes() {
        UserType userType = new UserType(2, "Buyer", "Want to buy the property");
        List<UserType> userTypes = new ArrayList<UserType>();
        userTypes.add(userType);
        return userTypes;
    }

    private Iterable<User> buildUser() {
        UserType userType = new UserType(2, "Buyer", "Want to buy the property");
        User user = new User(1, "Abir", "", "Datta", "a@b.com", "password", "Kolkata", "India", userType);
        List<User> users = new ArrayList<User>();
        users.add(user);
        return users;
    }

}
