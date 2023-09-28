package com.adria.transactionservice.service;

import com.adria.transactionservice.dtos.TransactionDTO;
import com.adria.transactionservice.entities.Transaction;
import com.adria.transactionservice.exceptions.BalanceNotSufficentException;
import com.adria.transactionservice.exceptions.CardNotActivedException;
import com.adria.transactionservice.exceptions.CardNotFoundException;
import com.adria.transactionservice.exceptions.TransactionNotFoundException;
import com.adria.transactionservice.feign.AccountService;
import com.adria.transactionservice.feign.CardService;
import com.adria.transactionservice.mappers.TransactionMapper;
import com.adria.transactionservice.models.Account;
import com.adria.transactionservice.models.Card;
import com.adria.transactionservice.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    TransactionRepository transactionRepository;
    TransactionMapper transactionMapper;
    AccountService accountService;
    CardService cardService;

    @Override
    public TransactionDTO saveTransaction(TransactionDTO transactionDTO) throws BalanceNotSufficentException, CardNotFoundException, CardNotActivedException {

        System.out.println(transactionDTO.getCardId());
        Card card=cardService.getCard(transactionDTO.getCardId());

        if(card==null )
            throw  new CardNotFoundException("card not found");
        if(!card.isActive()) {
            throw new CardNotActivedException("card not actived");
        }

            Account account = accountService.getAccountById(card.getAccountId());

            System.out.println(account.getBalance());
            if (transactionDTO.getMontant() > account.getBalance()) {
                throw new BalanceNotSufficentException("balance not sufficent");
            }
            account.setBalance(account.getBalance() - transactionDTO.getMontant());
            System.out.println(account.getBalance());
            System.out.println(account.getId());
            Account destination = accountService.getAccountByNumber(transactionDTO.getDestination());
            destination.setBalance(destination.getBalance() + transactionDTO.getMontant());
            accountService.updateAccount(destination, destination.getId());
            accountService.updateAccount(account, account.getId());

            Transaction transaction = transactionRepository.save(transactionMapper.fromTransactionDto(transactionDTO));
            transaction.setDateTransaction(new Date());

        return transactionMapper.fromTransaction(transaction);
    }

    @Override
    public TransactionDTO getTransaction(Long transactionId) throws TransactionNotFoundException {
        Transaction transaction=transactionRepository.findById(transactionId).orElseThrow(() -> new TransactionNotFoundException("transaction not found"));
        return transactionMapper.fromTransaction(transaction);
    }

    @Override
    public List<TransactionDTO> getTransactions() {
        List<Transaction> transactions=transactionRepository.findAll(Sort.by(Sort.Direction.DESC, "dateTransaction"));
        return transactions.stream().map(transaction -> transactionMapper.fromTransaction(transaction)).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> getTransactionsByCard(Long cardId) {
        List<Transaction> transactions=transactionRepository.findTransactionByCardId(cardId);
        return transactions.stream().map(transaction -> transactionMapper.fromTransaction(transaction)).collect(Collectors.toList());
    }
    @Override
    public List<TransactionDTO> getTransactionsByCustomer(Long customerId) {
        List<TransactionDTO> listetransactionDTOS=getTransactions();
       List<TransactionDTO> liste=listetransactionDTOS.stream()
                .filter(transaction -> transaction.getAccount().getCustomerId()==customerId)
                .collect(Collectors.toList());

        return liste;
    }

    @Override
    public List<TransactionDTO> getRecentTransactions(Long customerId) {
       List<TransactionDTO> liste=getTransactionsByCustomer(customerId);
       if(liste.size()>10)
       return liste.subList(liste.size() - 10, liste.size());
      return liste;
    }

    @Override
    public List<TransactionDTO> getTransactionsByAccount(Long accountId) {
        List<TransactionDTO> listetransactionDTOS=getTransactions();
        List<TransactionDTO> liste=listetransactionDTOS.stream()
                .filter(transaction -> transaction.getCard().getAccountId()==accountId)
                .collect(Collectors.toList());

        return liste;
    }

}
