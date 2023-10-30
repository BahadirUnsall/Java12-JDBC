package com.bahadir.repository;

import com.bahadir.entity.Account;
import com.bahadir.entity.TransferDekont;
import com.bahadir.entity.User;
import com.bahadir.util.DbConnection;
import com.bahadir.util.RandomGenerateAccountNo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountRepository {
    public Account createAccount(User user){
        String query = "INSERT INTO accounts(user_id, account_no) VALUES (?, ?);";
        PreparedStatement preparedStatement = null;

        Account account = Account.builder()
                .userId(user.getId())
                .accountNo(RandomGenerateAccountNo.generateAccountNo())
                .build();
        try {
            preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1,account.getUserId());
            preparedStatement.setString(2, account.getAccountNo());

            if (preparedStatement.executeUpdate() > 0){
                return account;
            }else{
                throw new RuntimeException("Hesap oluşturulamadı!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Account> getAccountByEmail(String email){
        String query ="SELECT accounts.* FROM users\n" +
                "INNER JOIN accounts\n" +
                "ON users.id = accounts.user_id\n" +
                "WHERE users.email = ?;";
        List<Account> accounts = new ArrayList<>();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1,email);

            ResultSet resultSet = preparedStatement.executeQuery();

            Account account ;
            while((resultSet.next())){
                int id = resultSet.getInt("id");
                double balance = resultSet.getDouble("balance");
                String accountNo = resultSet.getString("account_no");
                account = Account.builder()
                        .id(id)
                        .balance(balance)
                        .accountNo(accountNo)
                        .build();
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public TransferDekont transferMoney(int amount,String accountNo,User user){
        String query = "UPDATE accounts SET balance = balance + ? WHERE account_no = ?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1,amount);
            preparedStatement.setString(2,accountNo);
            preparedStatement.executeUpdate();

            String receiverName = findUsernameByAccountNo(accountNo);
            TransferDekont transferDekont = TransferDekont.builder()
                    .transactionDate(new Date())
                    .sendAmountTotal(amount)
                    .senderName(user.getName())
                    .receiverName(receiverName)
                    .build();

            return transferDekont;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String findUsernameByAccountNo(String accountNo){
        String query = "SELECT users.name FROM users INNER JOIN accounts ON users.id = accounts.user_id WHERE account_no = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1,accountNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
               return resultSet.getString("name");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
