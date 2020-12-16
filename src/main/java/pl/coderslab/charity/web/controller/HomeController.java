package pl.coderslab.charity.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.persistence.service.DonationService;
import pl.coderslab.charity.persistence.service.InstitutionService;


@Controller
public class HomeController {

    private InstitutionService institutionService;
    private DonationService donationService;

    @Autowired
    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @RequestMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("institunions", institutionService.findAll());
        model.addAttribute("sumOfBags", donationService.sumOfReceivedBags());
        model.addAttribute("sumOfGifts", donationService.sumOfGifts());
        return "index";
    }
}
