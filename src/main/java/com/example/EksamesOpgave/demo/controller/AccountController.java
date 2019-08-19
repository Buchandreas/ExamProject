package com.example.EksamesOpgave.demo.controller;

import javax.servlet.http.HttpSession;

import com.example.EksamesOpgave.demo.repository.BrugerRepo;
import com.example.EksamesOpgave.demo.service.BrugerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {

    @Autowired
    BrugerService brugerService;

    @Autowired
    BrugerRepo brugerRepo;

    Integer name;

    //PostMapping til login. Bruger metode authenticate på cpr, og hvis den
    // er i databasen sendes vi videre til /actionPage

    @PostMapping("/login")
    public String authenticate(Integer cpr, HttpSession session, RedirectAttributes ra){
        if (brugerService.isCprInDb(cpr)){
            name = cpr;
            session.setAttribute("cpr", cpr);
            ra.addAttribute("msg","Logged in as " + brugerRepo.currentUserName(cpr));
            return "redirect:/actionPage";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("cpr");
        name = 0;
        return "redirect:/login";
    }

    public static Boolean isLoggedIn(HttpSession session){
        if(session.getAttribute("cpr") == null){
            return false;
        }
        return true;
    }

    public Integer getName(Integer name){
        name = this.name;
        return name;
    }
}