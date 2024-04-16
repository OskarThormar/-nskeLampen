package com.example.onskelampen.service;

import com.example.onskelampen.model.OnskeLampen;
import com.example.onskelampen.repository.OnskeLampenRepository;
//import com.example.onskelampen.repository.OnskeLampenRepository_DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnskeLampenService {

    private OnskeLampenRepository onskeLampenRepository;
    //private OnskeLampenRepository_DB onskeLampenRepository_db;

    @Autowired
    public OnskeLampenService(OnskeLampenRepository onskeLampenRepository /*OnskeLampenRepository_DB onskeLampenRepository_db*/) {
        this.onskeLampenRepository = onskeLampenRepository;
        //this.onskeLampenRepository_db = onskeLampenRepository_db;
    }

    public List<OnskeLampen> showList(){
        return onskeLampenRepository.showOnsker();
        //return onskeLampenRepository_db.showList();
    }
    public void createWish(OnskeLampen onske){
        //onskeLampenRepository.createWish(onske);
        onskeLampenRepository_db.addWish(onske);
    }
    public void deleteWish(int id) {
        onskeLampenRepository.deleteWish(id);
    }
    public void updateWish(OnskeLampen oenskeLampen){
        //onskeLampenRepository.updatOenske(oenskeLampen);
        onskeLampenRepository_db.changeWish(oenskeLampen);
    }
    public OnskeLampen getWishById(int id) {
        return onskeLampenRepository.getWishById(id);
    }
}

