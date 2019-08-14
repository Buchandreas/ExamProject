//Dele skrevet af Andreas Buch
//Specifikt dem hvor der er længere kommentarer

package com.example.EksamesOpgave.demo.controller;

import com.example.EksamesOpgave.demo.model.Bruger;
import com.example.EksamesOpgave.demo.service.BorrowListService;
import com.example.EksamesOpgave.demo.service.BrugerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class homeController {

    @Autowired
    BrugerService brugerService;

    @Autowired
    BorrowListService borrowListService;


    //route - vi bruger view model til at gå til og fra browser.

    @GetMapping("")
    public String frontPage(){
        return "frontPage";
    }
    @PostMapping("")
    public String goFrontPage(){
        return "redirect:/frontPage";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/actionPage")
    public String actionPage(HttpSession session){
        if(AccountController.isLoggedIn(session)) {
            return "actionPage";
        }

        return "redirect:/login";
    }

    @PostMapping("/actionPage")
    public String goActionPage(){
        return "redirect:/borrow";
    }

    @GetMapping("/brugerdata")
    public String brugerdata(Model model){
        model.addAttribute("bruger", brugerService.fetchAllBruger());
        return "brugerdata";
    }

    @GetMapping("/create")
    public String create(){
        return "create";
    }

    //PostMapping til kreation af ny bruger i DB, redirecter til "/" med success besked.
    @PostMapping("/create")
    public String createBruger(@ModelAttribute Bruger bruger, RedirectAttributes ra){
        brugerService.createBruger(bruger);
        ra.addAttribute("msg", "User was created!");
        return "redirect:/";
    }

    @GetMapping("/website")
    public String website(){
        return "website";
    }
    @PostMapping("/website")
    public String goWebsite(){
        return "redirect:/website";
    }


    @GetMapping("/borrow")
    public String borrow(HttpSession session){
        if(AccountController.isLoggedIn(session)) {
            return "borrow";
        }

        return "redirect:/login";
    }

    //metode til at route baseret på boolean resultat

    @PostMapping("/borrow")
    public String authenticateBorrow(Integer borrowListID, Integer cpr) {
        if (borrowListService.isItemInDb(borrowListID)) {
            return "redirect:/";
        } else {
            return "redirect:/borrow";
        }
    }

    @GetMapping("/opdater/{brugerId}")
    public String opdaterBruger(@PathVariable("brugerId") int brugerId, Model model){
        model.addAttribute("bruger", brugerService.readById(brugerId));
        return "opdater";
    }


    @PostMapping("/opdater")
    public String opdateringfærdig(@ModelAttribute Bruger bruger){
        brugerService.updateBruger(bruger);
        return "redirect:/createBruger" + bruger.getId();
    }

    @GetMapping("/delete/{brugerId}")
    public String delete(@PathVariable("brugerId") int brugerId){
        brugerService.deleteById(brugerId);
        return "redirect:/createBruger";
    }



}
