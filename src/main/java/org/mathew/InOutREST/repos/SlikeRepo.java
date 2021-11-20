package org.mathew.InOutREST.repos;

import org.mathew.InOutREST.services.accounts.Accounts;
import org.mathew.InOutREST.services.slike.Slike;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface SlikeRepo extends CrudRepository<Slike,Integer> {
    List<Slike> findByDatum(Date date);

    List<Slike> findByautor(Integer id);

    List<Slike> findByAutor(Accounts account);
}
