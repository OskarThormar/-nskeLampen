/*package com.example.onskelampen;

import com.example.onskelampen.controller.OnskeLampenController;
import com.example.onskelampen.model.OnskeLampen;
import com.example.onskelampen.service.OnskeLampenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OnskeLampenControllerTest {

    @Mock
    private OnskeLampenService onskeLampenService;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private OnskeLampenController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLandingPage() {
        String landingPage = controller.landingPage();
        assertEquals("landingPage-index", landingPage);
    }

    @Test
    void testShowListWhenUserIdExistsInSession() {
        Integer userId = 1;
        List<OnskeLampen> wishList = new ArrayList<>();
        when(session.getAttribute("userid")).thenReturn(userId);
        when(onskeLampenService.showList(userId)).thenReturn(wishList);

        String viewName = controller.showList(session, model);

        assertEquals("showList-index", viewName);
        verify(model).addAttribute("userId", userId);
        verify(model).addAttribute("wishList", wishList);
    }

    @Test
    void testShowListWhenUserIdDoesNotExistInSession() {
        when(session.getAttribute("userid")).thenReturn(null);

        String viewName = controller.showList(session, model);

        assertEquals("redirect:/user/login", viewName);
    }

    @Test
    void testAddWish() {
        OnskeLampen newWish = new OnskeLampen();
        String viewName = controller.addWish(model);
        assertEquals("wish-add", viewName);
        verify(model).addAttribute("wish", newWish);
    }

    // Similarly, you can write tests for other methods like createWish, deleteWish, showUpdateForm, and updateWish.
}*/
