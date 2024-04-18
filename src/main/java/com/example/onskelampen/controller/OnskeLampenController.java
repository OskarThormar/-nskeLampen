package com.example.onskelampen.controller;

import com.example.onskelampen.model.OnskeLampen;
import com.example.onskelampen.service.OnskeLampenService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;


@Controller
@RequestMapping("")
public class OnskeLampenController {

    private final OnskeLampenService onskeLampenService;

    public OnskeLampenController(OnskeLampenService onskeLampenService) {
        this.onskeLampenService = onskeLampenService;
    }

    @GetMapping("")
    public String landingPage() {
        return "landingPage-index";
    }

    @GetMapping("/showList")
    public String showList(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userid");
        if (userId != null) {
            model.addAttribute("userId", userId);
            model.addAttribute("wishList", onskeLampenService.showList(userId));
            return "showList-index";
        } else {
            return "redirect:/user/login";
        }
    }

    @GetMapping("/createWish")
    public String addWish(Model model){
        OnskeLampen newWish = new OnskeLampen();
        model.addAttribute("wish", newWish);
        return "wish-add";
    }

    @PostMapping("/createWish")
    public String createWish(@ModelAttribute OnskeLampen onske, int userid) {
        onskeLampenService.createWish(onske, userid);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String deleteWish(@PathVariable int id) {
        onskeLampenService.deleteWish(id);
        return "redirect:/showList";
    }
    @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable int id, Model model) {
        OnskeLampen wish = onskeLampenService.getWishById(id);
        model.addAttribute("wish", wish);
        return "wish-update";
    }
    @PostMapping("/updateWish")
    public String updateWish(@ModelAttribute OnskeLampen updatedWish) {
        onskeLampenService.updateWish(updatedWish);
        return "redirect:/showList";
    }
}
