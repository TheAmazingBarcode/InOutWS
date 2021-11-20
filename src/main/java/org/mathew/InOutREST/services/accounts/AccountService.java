package org.mathew.InOutREST.services.accounts;

import org.mathew.InOutREST.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository repo;

    public List<Accounts> fetchAllAccounts() {
        return (List<Accounts>) repo.findAll();
    }

    public Accounts insertAccount(Accounts account){
        return repo.save(account);
    }
}
