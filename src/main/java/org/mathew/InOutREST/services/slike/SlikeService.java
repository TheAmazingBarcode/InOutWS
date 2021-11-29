package org.mathew.InOutREST.services.slike;

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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SlikeService {
    @Autowired
    SlikeRepo repo;

    @Autowired
    AccountService accountService;

    @Autowired
    KategorijaService service;

    @Autowired
    RatingRepo ratingRepo;

    public List<Slike> fetchAllSlike(){return (List<Slike>) repo.findAll();}

    public List<Kategorija> kategorijeSlike(Integer id){return repo.findById(id).get().getKategorije();}

    public List<Slike> slikeAutora(Accounts autor){return repo.findByAutor(autor);}

    public Slike insertSlika(Slike slika, MultipartFile file) throws IOException {
        String folder="/home/mathew/Programming/InOutSlike/";
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        byte[] slikaData = file.getBytes();
        Path path = Paths.get(folder+filename);
        Files.write(path,slikaData);
        slika.setReferenca(path.toString());
        return repo.save(slika);
    }

    public Slike slikaID(Integer id){return repo.findById(id).orElse(null);}

    public void izbaciSliku(Integer id){repo.deleteById(id);}

    public void oceniSliku(Rating rating){
       ratingRepo.save(rating);
       List<Integer> ocene = new ArrayList<>();

       ratingRepo.findBySlika(rating.getSlika()).forEach(rating1 -> ocene.add(rating1.getOcena()));
       double prosek = ocene.stream().mapToInt(value -> value).average().orElse(0.0);

       Optional<Slike> slika = repo.findById(rating.getSlika().getId());
       slika.get().setRating(prosek);
       repo.save(slika.orElse(null));
    }
}
