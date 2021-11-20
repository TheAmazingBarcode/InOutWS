package org.mathew.InOutREST.repos;

import org.mathew.InOutREST.services.kategorija.Kategorija;
import org.springframework.data.repository.CrudRepository;

public interface KategorijaRepo extends CrudRepository<Kategorija,Integer> {
    Kategorija findByNaziv(String naziv);
}
