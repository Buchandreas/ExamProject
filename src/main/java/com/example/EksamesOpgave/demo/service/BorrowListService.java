package com.example.EksamesOpgave.demo.service;

import com.example.EksamesOpgave.demo.model.BorrowList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.EksamesOpgave.demo.repository.BorrowListRepo;

import java.util.List;

@Service
public class BorrowListService {
    /**
     * Injection of dependencies by our repository
     */
    @Autowired
    BorrowListRepo borrowlistRepo;

    public List<BorrowList> fetchAllBorrowLists() {
        return borrowlistRepo.fetchAllBorrowLists();
    }

    public void deleteById(int borrowListId) {
        borrowlistRepo.deleteById(borrowListId);
    }

    public void createBorrowList(BorrowList borrowList) {
        borrowlistRepo.createBorrowList(borrowList);
    }

    public void updateBorrowList(Integer borrowList, Integer name) { borrowlistRepo.updateBorrowList(borrowList, name);
    }

    public void readByListId(int bruger) {
        borrowlistRepo.readByListId(bruger);
    }

    public boolean isItemInDb(Integer borrowListID) {
        if (borrowListID == null) {
            return false;
        } else {
            return borrowlistRepo.isItemInDb(borrowListID);
        }
    }
}


