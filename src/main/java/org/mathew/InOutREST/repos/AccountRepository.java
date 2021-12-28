package org.mathew.InOutREST.repos;

import org.mathew.InOutREST.services.accounts.Accounts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Accounts,Integer> {
     Accounts findByEmailAndPassword(String email,String password);
}
