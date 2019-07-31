//Dele skrevet af Andreas Buch
//Specifikt dem hvor der er længere kommentarer

package com.example.EksamesOpgave.demo.service;

import com.example.EksamesOpgave.demo.model.Bruger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.EksamesOpgave.demo.repository.BrugerRepo;

import java.util.List;

@Service
public class BrugerService {
    /**
     *  Injection of dependencies by our repository
     */
    @Autowired
    BrugerRepo brugerRepo;
    public List<Bruger> fetchAllBruger(){
        return brugerRepo.fetchAllBruger();
    }
    public void deleteById(int brugerId){
        brugerRepo.deleteById(brugerId);
    }
    public void createBruger(Bruger bruger) {
        brugerRepo.createBruger(bruger);
    }
    public Bruger readById(int brugerId){
        return brugerRepo.readById(brugerId);
    }

    //boolean på Integer wrapper klasse. Hvis cpr er null returnerer vi false,
    // ellers tjekker vi efter i db.

    public boolean isCprInDb(Integer cpr){
        if(cpr == null){
            return false;
        } else {
            return brugerRepo.isCprInDb(cpr);
        }
    }
    public void updateBruger(Bruger bruger){
        brugerRepo.updateBruger(bruger);
    }

}
