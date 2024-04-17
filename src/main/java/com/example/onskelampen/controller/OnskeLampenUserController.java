package com.example.onskelampen.controller;

import com.example.onskelampen.model.User;
import com.example.onskelampen.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/user")
public class OnskeLampenUserController {

    UserService userService;
    private final User user;

    public OnskeLampenUserController(UserService userService) {
        this.userService = userService;
        this.user = new User();
    }

    @GetMapping("")
    public String landingPage() {
        return "User-LandingPage";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userForm", new User());
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userForm") User userForm, Model model, RedirectAttributes redirectAttributes) {
        // Try to register the user (add your registration logic here)
        boolean isRegistered = userService.register(userForm);

        if (isRegistered) {
            redirectAttributes.addFlashAttribute("success", "Registration successful!");
            return "redirect:/user/login"; // Redirect to login page after successful registration
        } else {
            model.addAttribute("error", "Registration failed. Try again.");
            return "register"; // Stay on the registration page if registration fails
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String userName,
                        @RequestParam("password") String password,
                        HttpSession session) {
        if (userService.validate(userName, password)) {
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("username", userName);
            return "redirect:/";
        } else {
            return "redirect:/user/login/error";
        }
    }

    @GetMapping("/user/login/error")
    public String error() {
        return "login-error";
    }

    @GetMapping("/result")
    public String result(HttpSession session, Model model) {
        // Check if the user is logged in by looking for a session attribute
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (Boolean.TRUE.equals(isLoggedIn)) {
            // Retrieve user information from the session
            String username = (String) session.getAttribute("username");

            // Add user information to the model to display on the result page
            model.addAttribute("username", username);

            // Display the result page with user details
            return "login-result";
        } else {
            // If not logged in, redirect to the login page
            return "redirect:/user/login";
        }
    }

}
