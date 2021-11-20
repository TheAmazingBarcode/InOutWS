package org.mathew.InOutREST.services.kategorija;

import org.mathew.InOutREST.repos.KategorijaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KategorijaService {
    @Autowired
    KategorijaRepo repo;

    public Kategorija insertKategorija(Kategorija kategorija) {return repo.save(kategorija);}

    public Kategorija kategorijaPoNazivu(String naziv){return repo.findByNaziv(naziv);}
}
