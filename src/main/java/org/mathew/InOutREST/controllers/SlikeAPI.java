package org.mathew.InOutREST.controllers;

import org.mathew.InOutREST.services.accounts.AccountService;
import org.mathew.InOutREST.services.kategorija.Kategorija;
import org.mathew.InOutREST.services.rating.Rating;
import org.mathew.InOutREST.services.slike.Slike;
import org.mathew.InOutREST.services.slike.SlikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("slike")
public class SlikeAPI {
    @Autowired
    private SlikeService slikeService;
    @Autowired
    private AccountService accountService;


    //Requestovi za dobijanje svih slika
    @GetMapping("sve")
    public ResponseEntity<Object> listajSveSlike() throws IOException {
        return new ResponseEntity<>(slikeService.fetchAllSlike(), HttpStatus.OK);
    }


    //dobijanje samo odredjenje slike
    @GetMapping("{idSlike}")
    public ResponseEntity<Object> slikaPoId(@PathVariable("idSlike") String idSlike) throws IOException {
        return new ResponseEntity<>(slikeService.slikaID(Integer.parseInt(idSlike)),HttpStatus.OK);
    }

    //dobijanje kategorija slike
    @GetMapping("{idSlike}/kategorije")
    public ResponseEntity<Set<Kategorija>> kategorijeSlike(@PathVariable("idSlike") String idSlike){
        return new ResponseEntity<>(slikeService.kategorijeSlike(Integer.parseInt(idSlike)),HttpStatus.OK);
    }

    //dobijanje slike odredjenog autora
    @GetMapping("{idAutora}/slikeAutora")
    public ResponseEntity<Object> slikeAutora(@PathVariable("idAutora") String idAutora) throws IOException {
        return new ResponseEntity<>(slikeService.slikeAutora(accountService.getAccountByID(Integer.parseInt(idAutora))),HttpStatus.OK);
    }

    //ubacivanje slike
    @PostMapping(value = "ubaciSliku")
    public String insertSlika(@RequestPart("slikaPodaci")Slike slika, @RequestParam("slika")MultipartFile slikaFile) throws IOException {
        String status="uspesan";
        slikeService.insertSlika(slika,slikaFile);
        return status;
    }


    //ocenjivanje slike
    @PutMapping(value = "oceniSliku")
    public String oceniSliku(@RequestBody Rating rating){
            slikeService.oceniSliku(rating);
            return "Slika je ocenjena";
    }

    //brisanje slike
    @DeleteMapping("izbaciSliku/{idSlike}")
    public ResponseEntity<Integer> izbaciSliku(@PathVariable("idSlike") String idSlike) throws IOException {
        slikeService.izbaciSliku(Integer.parseInt(idSlike));
        return new ResponseEntity<>(Integer.parseInt(idSlike),HttpStatus.OK);
    }

}
