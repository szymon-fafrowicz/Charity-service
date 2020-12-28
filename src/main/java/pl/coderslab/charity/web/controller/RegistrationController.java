package pl.coderslab.charity.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.error.UserAlreadyExistException;
import pl.coderslab.charity.persistence.entity.User;
import pl.coderslab.charity.persistence.service.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String registrationFormPost(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }
        try {
            userService.registerNewUser(user);
        } catch (UserAlreadyExistException uae) {
            model.addAttribute("message", "An account for that email already exists.");
            return "registration";
        }

        return "registration-confirmation";
    }
}
