package com.example.onskelampen;

import com.example.onskelampen.controller.OnskeLampenUserController;
import com.example.onskelampen.model.User;
import com.example.onskelampen.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import jakarta.servlet.http.HttpSession;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.when;

class OnskeLampenUserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private OnskeLampenUserController controller;

    private MockMvc mockMvc;

    @Test
    void testLandingPage() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("User-LandingPage"));
    }

    @Test
    void testRegister() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/user/register"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("register"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userForm"));
    }

    @Test
    void testRegisterUser_Success() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        User user = new User();
        when(userService.register(user)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .flashAttr("userForm", user))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/login"));
    }

    @Test
    void testRegisterUser_Failure() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        User user = new User();
        when(userService.register(user)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .flashAttr("userForm", user))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("register"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("error"));
    }
}
