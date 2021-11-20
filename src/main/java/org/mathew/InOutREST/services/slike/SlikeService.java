package org.mathew.InOutREST.services.slike;

import org.mathew.InOutREST.repos.SlikeRepo;
import org.mathew.InOutREST.services.kategorija.Kategorija;
import org.mathew.InOutREST.services.kategorija.KategorijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlikeService {
    @Autowired
    SlikeRepo repo;

    @Autowired
    KategorijaService service;


    public List<Slike> fetchAllSlike(){return (List<Slike>) repo.findAll();}
    public List<Kategorija> slikeKategorije(Integer id){return repo.findById(id).get().getKategorije();}
    public List<Slike> slikeAutora(Integer id){return repo.findByIdautora(id);}

}
