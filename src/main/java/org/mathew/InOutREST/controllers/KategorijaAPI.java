package org.mathew.InOutREST.controllers;

import org.mathew.InOutREST.services.kategorija.Kategorija;
import org.mathew.InOutREST.services.kategorija.KategorijaService;
import org.mathew.InOutREST.services.slike.Slike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("kategorije")
public class KategorijaAPI {
    @Autowired
    KategorijaService service;


    @GetMapping("sve")
    public ResponseEntity<List<Kategorija>> prikaziKategorije(){
        return new ResponseEntity<>(service.fetchAllKategorije(),HttpStatus.OK);
    }

    @GetMapping("{idKategorije}/slike")
    public ResponseEntity<List<Slike>> slikeIzKategorije(@PathVariable("idKategorije") String idKategorije){
        return new ResponseEntity<>(service.slikeIzKategorije(Integer.parseInt(idKategorije)), HttpStatus.OK);
    }
}
