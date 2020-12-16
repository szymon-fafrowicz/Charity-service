package pl.coderslab.charity.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.persistence.entity.Institution;
import pl.coderslab.charity.persistence.service.InstitutionService;

import java.util.List;


@Controller
public class HomeController {

    private InstitutionService institutionService;

    @Autowired
    public HomeController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        List<Institution> institutions = institutionService.findAll();
        model.addAttribute("institunions", institutions);

        return "index";
    }
}
