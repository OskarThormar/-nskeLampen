package com.example.onskelampen.service;

import com.example.onskelampen.repository.OnskeLampenRepository;
import com.example.onskelampen.repository.OnskeLampenRepository_DB;
import org.springframework.stereotype.Service;

@Service
public class OnskeLampenService {

    private OnskeLampenRepository onskeLampenRepository;
    private OnskeLampenRepository_DB onskeLampenRepository_db;

    public OnskeLampenService(OnskeLampenRepository onskeLampenRepository, OnskeLampenRepository_DB onskeLampenRepository_db) {
        this.onskeLampenRepository = onskeLampenRepository;
        this.onskeLampenRepository_db = onskeLampenRepository_db;
    }
}
