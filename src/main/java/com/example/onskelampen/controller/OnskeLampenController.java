package com.example.onskelampen.controller;

import com.example.onskelampen.model.OnskeLampen;
import com.example.onskelampen.service.OnskeLampenService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class OnskeLampenController {

    private final OnskeLampenService onskeLampenService;

    public OnskeLampenController(OnskeLampenService onskeLampenService) {
        this.onskeLampenService = onskeLampenService;
    }
}
