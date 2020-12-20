package pl.coderslab.charity.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.persistence.entity.Donation;
import pl.coderslab.charity.persistence.service.CategoryService;
import pl.coderslab.charity.persistence.service.DonationService;
import pl.coderslab.charity.persistence.service.InstitutionService;

import javax.validation.Valid;


@Controller
public class DonationController {
    CategoryService categoryService;
    InstitutionService institutionService;
    DonationService donationService;

    @Autowired
    public DonationController(CategoryService categoryService, InstitutionService institutionService, DonationService donationService) {
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @GetMapping("/form")
    public String donationForm(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("institutions", institutionService.findAll());
        model.addAttribute("donation", new Donation());
        return "form";
    }

    @PostMapping("/form")
    public String donationFormPost(@ModelAttribute("donation") @Valid Donation donation, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "form";
//        }

        try {
            donationService.save(donation);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            model.addAttribute("message", "Operation failed.");
            return "form";
        }
        return "redirect:/";
    }
}
