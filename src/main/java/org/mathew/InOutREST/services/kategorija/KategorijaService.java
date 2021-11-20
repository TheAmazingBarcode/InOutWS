package org.mathew.InOutREST.services.kategorija;

import org.mathew.InOutREST.controllers.KategorijaAPI;
import org.mathew.InOutREST.repos.KategorijaRepo;
import org.mathew.InOutREST.repos.SlikeRepo;
import org.mathew.InOutREST.services.slike.Slike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategorijaService {
    @Autowired
    KategorijaRepo repo;

    @Autowired
    SlikeRepo slikeRepol;

    public Kategorija insertKategorija(Kategorija kategorija) {return repo.save(kategorija);}

    public Kategorija kategorijaPoNazivu(String naziv){return repo.findByNaziv(naziv);}

    public List<Slike> slikeIzKategorije(Integer id) {return repo.findById(id).get().getSlikeIzKategorije().stream().toList();}

    public List<Kategorija> fetchAllKategorije(){return (List<Kategorija>) repo.findAll();}
}
