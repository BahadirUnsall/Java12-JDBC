package com.bahadir.service;

import com.bahadir.entity.Account;
import com.bahadir.entity.User;
import com.bahadir.repository.AccountRepository;

import java.util.List;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(){
        this.accountRepository = new AccountRepository();
    }

    public void createAccount(User user){
        Account account = accountRepository.createAccount(user);
        System.out.println("Hesap numaranız: " + account.getAccountNo());
    }
    public void getAccountByEmail(String email){
        List<Account> accounts = accountRepository.getAccountByEmail(email);
        for (Account account : accounts) {
            System.out.println(account.getId()+" Hesap numaraniz: "+account.getAccountNo() + " Bakiyeniz: " + account.getBalance());
        }
    }
    public void transferMoney(int amount,String accountNo,User user){
        accountRepository.transferMoney(amount,accountNo,user);
    }
}
