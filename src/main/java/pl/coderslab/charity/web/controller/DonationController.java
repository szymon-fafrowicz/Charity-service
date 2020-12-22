package pl.coderslab.charity.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.persistence.entity.Category;
import pl.coderslab.charity.persistence.entity.Donation;
import pl.coderslab.charity.persistence.entity.Institution;
import pl.coderslab.charity.persistence.service.CategoryService;
import pl.coderslab.charity.persistence.service.DonationService;
import pl.coderslab.charity.persistence.service.InstitutionService;

import javax.validation.Valid;
import java.util.Collection;


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

    @ModelAttribute("categories")
    public Collection<Category> categories() {
        return categoryService.findAll();
    }

    @ModelAttribute("institutions")
    public Collection<Institution> institutions() {
        return institutionService.findAll();
    }


    @GetMapping("/form")
    public String donationForm(Model model) {
        model.addAttribute("donation", new Donation());
        return "form";
    }

    @PostMapping("/form")
    public String donationFormPost(@ModelAttribute("donation") @Valid Donation donation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "form";
        }

        donationService.save(donation);

//        try {
//            donationService.save(donation);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//            model.addAttribute("message", "Operation failed.");
//            return "form";
//        }
        return "form-confirmation";
    }
}
