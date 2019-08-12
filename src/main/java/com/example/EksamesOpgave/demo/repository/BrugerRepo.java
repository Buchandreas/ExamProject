//Dele skrevet af Andreas Buch
//Specifikt dem hvor der er længere kommentarer

package com.example.EksamesOpgave.demo.repository;

import com.example.EksamesOpgave.demo.model.Bruger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class that handle our SQL querries
 */

@Repository
public class BrugerRepo{
    @Autowired
    JdbcTemplate template;

    public List<Bruger> fetchAllBruger(){
        String sql =" SELECT * FROM bruger";
        RowMapper<Bruger> rowMapper = new BeanPropertyRowMapper<>(Bruger.class);
        return template.query(sql, rowMapper);
    }

    public Bruger readById(int brugerId){
        String sql = "SELECT * FROM bruger WHERE brugerId=?";
        RowMapper<Bruger> rowMapper = new BeanPropertyRowMapper<>(Bruger.class);
        return template.queryForObject(sql, rowMapper, brugerId);
    }

    // tester cpr imod databasen, hvis count variablen er > 0, vil det sige vores indtastede
    // cpr befinder sig i databasen en eller flere gange. Ellers returneres false, og vi refresher siden tilbage i
    // homecontrolleren, så en ny cpr kan indtastes.

    public boolean isCprInDb(int cpr){
        String sql = "SELECT count(*) FROM Bruger WHERE cpr=?";
        int count = template.queryForObject(sql, new Object[] {cpr}, Integer.class);

        if(count > 0 ){
            return true;
        } else {
            return false;
        }
    }

    public String currentUserName(int cpr){
        String sql = "SELECT navn FROM Bruger WHERE cpr=?";
        return template.queryForObject(sql, new Object[] {cpr}, String.class);
    }

    public String loginInformation(int cpr){
        String sql = "SELECT * FROM Bruger WHERE cpr=?";
        return template.queryForObject(sql, new Object[] {cpr}, String.class);
    }

    public void createBruger(Bruger bruger){
        String sql = "INSERT INTO bruger (brugerId, navn, cpr, sms, email, niveau) VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql, bruger.getId(), bruger.getNavn(), bruger.getCpr(),bruger.getSms(),bruger.getEmail(), bruger.getNiveau());
    }

    public void updateBruger(Bruger bruger){
        String sql ="UPDATE bruger SET navn=?, cpr=?, sms=?, email=?, niveau=? WHERE brugerId=? ";
        template.update(sql, bruger.getId(), bruger.getNavn(), bruger.getCpr(), bruger.getSms(), bruger.getEmail(), bruger.getNiveau());
    }

    public void deleteById(int brugerId){
        String sql = " DELETE FROM bruger WHERE brugerId=?";
        template.update(sql, brugerId);
    }
}

