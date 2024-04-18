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

}
