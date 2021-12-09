package org.mathew.InOutREST.controllers;

import org.mathew.InOutREST.services.kategorija.Kategorija;
import org.mathew.InOutREST.services.kategorija.KategorijaService;
import org.mathew.InOutREST.services.slike.Slike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("kategorije")
public class KategorijaAPI {
    @Autowired
    private KategorijaService service;


    @GetMapping("sve")
    public ResponseEntity<List<Kategorija>> prikaziKategorije(){
        return new ResponseEntity<>(service.fetchAllKategorije(),HttpStatus.OK);
    }

    @PostMapping("ubaciKategoriju")
    public String ubaciKategoriju(@RequestBody Kategorija kategorija){
        service.insertKategorija(kategorija);
        return "ubacema nova kategorija";
    }

    @GetMapping("{idKategorije}/slike")
    public ResponseEntity<Object> slikeIzKategorije(@PathVariable("idKategorije") String idKategorije) throws IOException {
        return new ResponseEntity<>(service.slikeIzKategorije(Integer.parseInt(idKategorije)), HttpStatus.OK);
    }
}
