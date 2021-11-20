package org.mathew.InOutREST.controllers;

import org.mathew.InOutREST.services.kategorija.KategorijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kategorije")
public class KategorijaAPI {
    @Autowired
    KategorijaService service;


}
