package org.mathew.InOutREST.controllers;

import org.mathew.InOutREST.services.kategorija.Kategorija;
import org.mathew.InOutREST.services.slike.Slike;
import org.mathew.InOutREST.services.slike.SlikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("slike")
public class SlikeAPI {
    @Autowired
    SlikeService service;

    @GetMapping("sve")
    public ResponseEntity<List<Slike>> listajSveSlike(){
        return new ResponseEntity<>(service.fetchAllSlike(), HttpStatus.OK);
    }

    @GetMapping("{idSlike}/kategorije")
    public ResponseEntity<List<Kategorija>> kategorijeSlike(@PathVariable("idSlike") String idSlike){
        return new ResponseEntity<>(service.slikeKategorije(Integer.parseInt(idSlike)),HttpStatus.OK);
    }

    @GetMapping("{idAutora}/slikeAutora")
    public ResponseEntity<List<Slike>> slikeAutora(@PathVariable("idAutora") String idAutora){
        return new ResponseEntity<>(service.slikeAutora(Integer.parseInt(idAutora)),HttpStatus.OK);
    }
}
