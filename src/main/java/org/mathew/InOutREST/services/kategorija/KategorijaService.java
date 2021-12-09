package org.mathew.InOutREST.services.kategorija;

import org.apache.commons.io.FileUtils;
import org.mathew.InOutREST.controllers.KategorijaAPI;
import org.mathew.InOutREST.repos.KategorijaRepo;
import org.mathew.InOutREST.repos.SlikeRepo;
import org.mathew.InOutREST.services.slike.Slike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class KategorijaService {
    @Autowired
    private KategorijaRepo repo;

    @Autowired
    private SlikeRepo slikeRepol;

    public Kategorija kategorijaPoId(Integer id){return repo.findById(id).orElse(null);}

    public Kategorija insertKategorija(Kategorija kategorija) {return repo.save(kategorija);}

    public Kategorija kategorijaPoNazivu(String naziv){return repo.findByNaziv(naziv);}

    public List<Map<String,Object>> slikeIzKategorije(Integer id) throws IOException {
        List<Map<String,Object>> response = pripremiResponse(repo.findById(id).get().getSlikeIzKategorije().stream().toList());
        return response;
    }

    public List<Kategorija> fetchAllKategorije(){return (List<Kategorija>) repo.findAll();}

    private List<Map<String,Object>> pripremiResponse(List<Slike> query) throws IOException {
        List<Map<String,Object>> response = new ArrayList<>();

        int counter = 0;
        for (Slike slike: query){
            response.add(new HashMap<>());

            response.get(counter).put("id", slike.getId());
            response.get(counter).put("adresa", slike.getAdresa());
            response.get(counter).put("datumSlikanja", slike.getDatum());
            response.get(counter).put("autor", slike.getAutor());
            response.get(counter).put("kategorije", slike.getKategorije());
            response.get(counter).put("latitude", slike.getLatitude());
            response.get(counter).put("longitude", slike.getLongitude());
            response.get(counter).put("rating", slike.getRating());

            byte[] imgByte = FileUtils.readFileToByteArray(new File(slike.getReferenca()));
            response.get(counter).put("imageData", Base64.getEncoder().encodeToString(imgByte));
            counter++;
        }
        return response;
    }
}
