package org.mathew.InOutREST.controllers;

import org.mathew.InOutREST.services.accounts.AccountService;
import org.mathew.InOutREST.services.accounts.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountAPI {
    @Autowired
    AccountService service;

    @GetMapping("getAll")
    public ResponseEntity<List<Accounts>> fetchAccounts() {
        return new ResponseEntity<>(service.fetchAllAccounts(), HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Accounts> insertAccount(@RequestBody Accounts account){
        return new ResponseEntity<>(service.insertAccount(account),HttpStatus.CREATED);
    }

}
