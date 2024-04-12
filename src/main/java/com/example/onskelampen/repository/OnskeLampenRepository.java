package com.example.onskelampen.repository;

import com.example.onskelampen.model.OnskeLampen;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OnskeLampenRepository {
    private int onskeId;

    private List<OnskeLampen> onskeLampensList;

    public OnskeLampenRepository() {
        onskeId = 1;
        this.onskeLampensList = new ArrayList<>();
        onskeLampensList.add(new OnskeLampen(1, "computer", "til at spille spil", 8000, 1, "computersalg"));
        onskeLampensList.add(new OnskeLampen(2, "bil", "mobilitet", 48000, 1, "citroen"));
    }

    private int getOnskeId() {
        return onskeId++;
    }

    public void createWish(OnskeLampen onske){
        onske.setId(getOnskeId());
        onskeLampensList.add(onske);
    }
    public List<OnskeLampen> showOnsker() {
        return onskeLampensList;
    }
    public OnskeLampen deleteWish(int id) {
        OnskeLampen removeWish = null;
        for (OnskeLampen oenske : onskeLampensList) {
            if (oenske.getId() == id)
                removeWish = oenske;
        }
        if (removeWish != null)
            onskeLampensList.remove(removeWish);
        return removeWish;
    }
    public void updatOenske(OnskeLampen oenskeUpdate) {
        int index = 0;
        for (int i=0; i<onskeLampensList.size(); i++) {
            if (onskeLampensList.get(i).getId() == oenskeUpdate.getId())
                index = i;
        }
        onskeLampensList.set(index, oenskeUpdate);
    }
    public OnskeLampen getWishById(int id) {
        OnskeLampen oenskeFind = null;
        for (OnskeLampen oenske : onskeLampensList) {
            if (oenske.getId() == id)
                oenskeFind = oenske;


        }
        if (oenskeFind != null)
            return oenskeFind;
        else
            return null;
    }
}
