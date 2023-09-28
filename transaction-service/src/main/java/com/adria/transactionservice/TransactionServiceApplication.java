package com.adria.transactionservice;

import com.adria.transactionservice.entities.Transaction;
import com.adria.transactionservice.feign.CardService;
import com.adria.transactionservice.models.Card;
import com.adria.transactionservice.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class TransactionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(TransactionRepository transactionRepository, CardService cardService) {
        return args -> {
            Card card = cardService.getCard(1L);
            Card card1 = cardService.getCard(2L);


            transactionRepository.save(new Transaction(null,Math.random() * 10500, new Date(),"GIFT", card.getId(),null));
            transactionRepository.save(new Transaction(null, Math.random() * 20000, new Date(),"achat", card1.getId(),null));
            transactionRepository.save(new Transaction(null,Math.random() * 60000, new Date(),"achat2",card.getId(),null));

            transactionRepository.findAll().forEach(account -> {
                System.out.println(account.toString());
            });
        };
    }
}