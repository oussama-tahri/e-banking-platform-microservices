package com.adria.transactionservice.mappers;

import com.adria.transactionservice.dtos.TransactionDTO;
import com.adria.transactionservice.entities.Transaction;
import com.adria.transactionservice.feign.AccountService;
import com.adria.transactionservice.feign.CardService;
import com.adria.transactionservice.models.Account;
import com.adria.transactionservice.models.Card;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionMapper {
    CardService cardService;
    AccountService accountService;
    public TransactionDTO fromTransaction(Transaction transaction){
        TransactionDTO transactionDTO=new TransactionDTO();
        Card card=cardService.getCard(transaction.getCardId());

        if(card!=null) {
            System.out.println(accountService.getAccountById(card.getAccountId()));
            Account account=accountService.getAccountById(card.getAccountId());
            System.out.println(account);
            BeanUtils.copyProperties(transaction, transactionDTO);
            transactionDTO.setCard(card);
            transactionDTO.setAccount(account);
        }
        return  transactionDTO;
    }

    public Transaction fromTransactionDto(TransactionDTO transactionDTO){

        Transaction transaction=new Transaction();
        BeanUtils.copyProperties(transactionDTO,transaction);

        return  transaction;
    }
}
