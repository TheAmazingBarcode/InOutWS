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

    @GetMapping("findId/{id}")
    public ResponseEntity<Accounts> findUserId(@PathVariable("id") String id){
        return new ResponseEntity<>(service.getAccountByID(Integer.parseInt(id)),HttpStatus.OK);
    }

    @GetMapping("findUser/{username}")
    public ResponseEntity<List<Accounts>> findUser(@PathVariable("username") String username){
        return new ResponseEntity<>(service.findUserByName(username),HttpStatus.OK);
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
