package org.mathew.InOutREST.services.kategorija;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.mathew.InOutREST.controllers.KategorijaAPI;
import org.mathew.InOutREST.repos.KategorijaRepo;
import org.mathew.InOutREST.repos.SlikeRepo;
import org.mathew.InOutREST.services.slike.Slike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
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

    public List<Map<String,Object>> slikeIzKategorije(Integer id,String kod) throws IOException {
        List<Map<String,Object>> response = pripremiResponse(new ArrayList<>(repo.findById(id).get().getSlikeIzKategorije().stream().toList()),Integer.parseInt(kod));
        return response;
    }

    public List<Kategorija> fetchAllKategorije(){return (List<Kategorija>) repo.findAll();}

    private List<Map<String,Object>> pripremiResponse(ArrayList<Slike> query,Integer kod) throws IOException {
        if(kod == 0){}
        else if(kod == 1){
            //Slike danas
            DateTime protekliDan = new DateTime().minusDays(1);
            query = filtrirajVremenski(protekliDan.toDate(),query);
            }
        else{
            //Slike protekle nedelje
            DateTime nedeljuPre = new DateTime().minusDays(7);
            query = filtrirajVremenski(nedeljuPre.toDate(),query);
        }

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

        Collections.shuffle(response);

        return response;
    }

    private ArrayList<Slike> filtrirajVremenski(java.util.Date date, ArrayList<Slike> slike){

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        ArrayList<Slike> slikeZaBrisanje = new ArrayList<>();

        for (Slike slika:slike ) {
            if(slika.getDatum().before(sqlDate)){
                slikeZaBrisanje.add(slika);
            }
        }

        slike.removeAll(slikeZaBrisanje);

        return slike;
    }
}
