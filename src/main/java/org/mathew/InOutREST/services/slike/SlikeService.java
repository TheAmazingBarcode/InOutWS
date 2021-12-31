package org.mathew.InOutREST.services.slike;

import org.apache.commons.io.FileUtils;
import org.mathew.InOutREST.repos.RatingRepo;
import org.mathew.InOutREST.repos.SlikeRepo;
import org.mathew.InOutREST.services.accounts.AccountService;
import org.mathew.InOutREST.services.accounts.Accounts;
import org.mathew.InOutREST.services.kategorija.Kategorija;
import org.mathew.InOutREST.services.kategorija.KategorijaService;
import org.mathew.InOutREST.services.rating.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.mathew.InOutREST.services.slike.ImgUtil.kompresujSliku;

@Service
public class SlikeService {
    @Autowired
    private SlikeRepo repo;

    @Autowired
    private AccountService accountService;

    @Autowired
    private KategorijaService service;

    @Autowired
    private RatingRepo ratingRepo;

    public List<Map<String,Object>> fetchAllSlike() throws IOException {
        List<Map<String,Object>> response = pripremiResponse((List<Slike>) repo.findAll());
        return response;
    }

    public Set<Kategorija> kategorijeSlike(Integer id){return repo.findById(id).get().getKategorije();}

    public List<Map<String,Object>> slikeAutora(Accounts autor) throws IOException {
        List<Map<String,Object>> response = pripremiResponse(repo.findByAutor(autor));
        return response;
    }

    public List<Map<String,Object>> slikeAdresa(String adresa) throws IOException {
        List<Map<String,Object>> response = pripremiResponse(repo.findByAdresa(adresa));
        return response;
    }

    public Slike insertSlika(Slike slika, MultipartFile file) throws IOException {
        String folder="/home/mathew/Programming/InOutSlike/";
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String path = folder+filename;

        boolean uspesno = ImgUtil.kompresujSliku(ImgUtil.kreirajFileRef(file,folder));
        if (!uspesno){
            throw new RuntimeException("Greska pri kompresiji");
        }

        Set<Kategorija> kategorije = new HashSet<>();
        slika.getKategorije().forEach(kat-> kategorije.add(service.kategorijaPoId(kat.getId())));
        slika.setKategorije(kategorije);
        slika.setReferenca(path.toString());
        return repo.save(slika);
    }

    public Map<String,Object> slikaID(Integer id) throws IOException {
        Map<String,Object> response = new HashMap<>();
        Slike slika = repo.findById(id).orElse(null);

        response.put("id",slika.getId());
        response.put("adresa",slika.getAdresa());
        response.put("datumSlikanja",slika.getDatum());
        response.put("autor",slika.getAutor());
        response.put("kategorije",slika.getKategorije());
        response.put("latitude",slika.getLatitude());
        response.put("longitude",slika.getLongitude());
        response.put("rating",slika.getRating());

        byte[] imgByte = FileUtils.readFileToByteArray(new File(slika.getReferenca()));
        response.put("imageData",Base64.getEncoder().encodeToString(imgByte));

        return response;
    }

    public void izbaciSliku(Integer id) throws IOException {
        if(!repo.findById(id).isPresent()){
            return;
        }
        Path fileZaBrisanje = Paths.get(repo.findById(id).get().getReferenca());
        try {
            ratingRepo.deleteAllBySlika(repo.findById(id).orElse(null));

            Files.delete(fileZaBrisanje);
            repo.deleteById(id);
        }
        catch(NoSuchFileException e){
            repo.deleteById(id);
        }
    }

    public void oceniSliku(Rating rating){
       ratingRepo.save(rating);
       List<Integer> ocene = new ArrayList<>();

       ratingRepo.findBySlika(rating.getSlika()).forEach(rating1 -> ocene.add(rating1.getOcena()));
       double prosek = ocene.stream().mapToInt(value -> value).average().orElse(0.0);

       Optional<Slike> slika = repo.findById(rating.getSlika().getId());
       slika.get().setRating(prosek);
       repo.save(slika.orElse(null));
    }

    private List<Map<String,Object>> pripremiResponse(List<Slike> query) throws IOException {
        List<Map<String,Object>> response = new ArrayList<>();

        int counter = 0;
        for (Slike slike: query){
            slike.getKategorije().forEach(kategorija -> {kategorija.setId(null);});

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
