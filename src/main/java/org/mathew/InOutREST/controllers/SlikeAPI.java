package org.mathew.InOutREST.controllers;

import org.mathew.InOutREST.services.accounts.AccountService;
import org.mathew.InOutREST.services.kategorija.Kategorija;
import org.mathew.InOutREST.services.slike.Slike;
import org.mathew.InOutREST.services.slike.SlikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("slike")
public class SlikeAPI {
    @Autowired
    SlikeService slikeService;
    @Autowired
    AccountService accountService;

    @GetMapping("sve")
    public ResponseEntity<List<Slike>> listajSveSlike(){
        return new ResponseEntity<>(slikeService.fetchAllSlike(), HttpStatus.OK);
    }

    @GetMapping("{idSlike}")
    public ResponseEntity<Slike> slikaPoId(@PathVariable("idSlike") String idSlike){
        return new ResponseEntity<>(slikeService.slikaID(Integer.parseInt(idSlike)),HttpStatus.OK);
    }

    @GetMapping("{idSlike}/kategorije")
    public ResponseEntity<Set<Kategorija>> kategorijeSlike(@PathVariable("idSlike") String idSlike){
        return new ResponseEntity<>(slikeService.kategorijeSlike(Integer.parseInt(idSlike)),HttpStatus.OK);
    }

    @GetMapping("{idAutora}/slikeAutora")
    public ResponseEntity<List<Slike>> slikeAutora(@PathVariable("idAutora") String idAutora){
        return new ResponseEntity<>(slikeService.slikeAutora(accountService.getAccountByID(Integer.parseInt(idAutora))),HttpStatus.OK);
    }

    @PostMapping("ubaciSLiku")
    public ResponseEntity<Slike> insertSlika(@RequestBody Slike slika){
        return new ResponseEntity<>(slikeService.insertSlika(slika),HttpStatus.CREATED);
    }

    @DeleteMapping("izbaciSliku/{idSlike}")
    public ResponseEntity<Integer> izbaciSliku(@PathVariable("idSlike") String idSlike){
        slikeService.izbaciSliku(Integer.parseInt(idSlike));
        return new ResponseEntity<>(Integer.parseInt(idSlike),HttpStatus.OK);
    }

}
