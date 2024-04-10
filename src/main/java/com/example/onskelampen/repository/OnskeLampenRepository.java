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
}
