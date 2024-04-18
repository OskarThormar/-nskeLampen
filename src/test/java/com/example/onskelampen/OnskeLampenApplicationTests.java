//package com.example.onskelampen;
//
//import com.example.onskelampen.controller.OnskeLampenController;
//import com.example.onskelampen.model.OnskeLampen;
//import com.example.onskelampen.repository.OnskeLampenRepository;
//import com.example.onskelampen.service.OnskeLampenService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import jakarta.servlet.http.HttpSession;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.ui.Model;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class OnskeLampenControllerTest {
//
//    @Mock
//    private OnskeLampenService onskeLampenService;
//
//    @InjectMocks
//    private OnskeLampenController onskeLampenController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        onskeLampenController.createWish(new OnskeLampen(13, "test", "testdescription", 13, 1, "test"));
//    }
//
//    @AfterEach
//    void tearDown() {
//        reset(onskeLampenService);
//    }
//
//    @Test
//    void showList() {
//        Model model = mock(Model.class HttpSession.class);
//        List<OnskeLampen> wishList = new ArrayList<>();
//        when(onskeLampenService.showList()).thenReturn(wishList);
//
//        String viewName = onskeLampenController.showList(model);
//        assertEquals("showList-index", viewName);
//        verify(model, times(1)).addAttribute("wishList", wishList);
//    }
//
//    @Test
//    void addWish() {
//        Model model = mock(Model.class);
//
//        String viewName = onskeLampenController.addWish(model);
//        assertEquals("wish-add", viewName);
//        verify(model, times(1)).addAttribute(eq("wish"), any(OnskeLampen.class));
//    }
//
//    @Test
//    void createWish() {
//        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
//        OnskeLampen onske = new OnskeLampen();
//
//        String viewName = onskeLampenController.createWish(onske);
//        assertEquals("redirect:/", viewName);
//        verify(onskeLampenService, times(1)).createWish(onske);
//    }
//
//    @Test
//    void deleteWish() {
//        int test = 13;
//        String deleteTest = onskeLampenController.deleteWish(test);
//        assertEquals("redirect:/showList", deleteTest);
//        verify(onskeLampenService, times(1)).deleteWish(test);
//    }
//}
