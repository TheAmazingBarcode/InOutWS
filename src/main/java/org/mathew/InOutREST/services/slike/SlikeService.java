package org.mathew.InOutREST.services.slike;

import org.mathew.InOutREST.repos.SlikeRepo;
import org.mathew.InOutREST.services.accounts.Accounts;
import org.mathew.InOutREST.services.kategorija.Kategorija;
import org.mathew.InOutREST.services.kategorija.KategorijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SlikeService {
    @Autowired
    SlikeRepo repo;

    @Autowired
    KategorijaService service;

    public List<Slike> fetchAllSlike(){return (List<Slike>) repo.findAll();}

    public Set<Kategorija> kategorijeSlike(Integer id){return repo.findById(id).get().getKategorije();}

    public List<Slike> slikeAutora(Accounts autor){return repo.findByAutor(autor);}

    public Slike insertSlika(Slike slika) {return repo.save(slika);}

    public Slike slikaID(Integer id){return repo.findById(id).orElse(null);}

    public void izbaciSliku(Integer id){repo.deleteById(id);}
}
