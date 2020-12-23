package pl.coderslab.charity.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class RegistrationController {


    public String showRegistrationForm(Model model) {
        return "registration";
    }
}
