package org.mathew.InOutREST.controllers;

import org.mathew.InOutREST.services.accounts.AccountService;
import org.mathew.InOutREST.services.accounts.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("accounts")
public class AccountAPI {
    @Autowired
    private AccountService service;

    @GetMapping("getAll")
    public ResponseEntity<List<Accounts>> fetchAccounts() {
        return new ResponseEntity<>(service.fetchAllAccounts(), HttpStatus.OK);
    }

    @PostMapping("check")
    public ResponseEntity<Accounts> getAccountsEmailPass(@RequestBody Accounts details){
        return new ResponseEntity<>(service.getAccountEmailPass(details), HttpStatus.FOUND);
    }

    @PostMapping("create")
    public ResponseEntity<Accounts> insertAccount(@RequestBody Accounts account){
        return new ResponseEntity<>(service.insertAccount(account),HttpStatus.CREATED);
    }
        
}
