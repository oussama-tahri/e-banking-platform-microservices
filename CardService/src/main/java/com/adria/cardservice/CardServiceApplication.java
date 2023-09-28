package com.adria.cardservice;

import com.adria.cardservice.dtos.CardDTO;
import com.adria.cardservice.entities.Card;
import com.adria.cardservice.enums.CardType;
import com.adria.cardservice.exceptions.CardNotFoundException;
import com.adria.cardservice.feign.AccountServiceClient;
import com.adria.cardservice.models.Account;
import com.adria.cardservice.repositories.CardRepository;
import com.adria.cardservice.service.ICardService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableFeignClients
public class CardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ICardService cardService, AccountServiceClient accountServiceClient) {
//        return args -> {
//            // Create sample card
//            CardDTO cardDTO = new CardDTO();
//            cardDTO.setCardNumber("1234567890123456");
//            cardDTO.setExpirationDate(LocalDate.of(2025, Month.DECEMBER, 31));
//            cardDTO.setCardType(CardType.MASTERCARD);
//            cardDTO.setActive(true);
//            //cardDTO.setAccountId(1L);
//
//            CardDTO createdCard = cardService.createCard(cardDTO);
//            System.out.println("Created Card: " + createdCard);
//
//            // Get card by ID
//            try {
//                CardDTO retrievedCard = cardService.getCardById(createdCard.getId());
//                System.out.println("Retrieved Card: " + retrievedCard);
//            } catch (CardNotFoundException e) {
//                System.err.println(e.getMessage());
//            }
//
//            // Update card
//            cardDTO.setExpirationDate(LocalDate.of(2026, Month.MAY, 22));
//            cardDTO.setCardType(CardType.VISA);
//            try {
//                CardDTO updatedCard = cardService.updateCard(createdCard.getId(), cardDTO);
//                System.out.println("Updated Card: " + updatedCard);
//            } catch (CardNotFoundException e) {
//                System.err.println(e.getMessage());
//            }
//
//            // Get all cards
//            List<CardDTO> allCards = cardService.getAllCards();
//            System.out.println("All Cards: " + allCards);
//
//            // Update card status
//            try {
//                CardDTO updatedCardStatus = cardService.updateCardStatus(createdCard.getId(), false);
//                System.out.println("Updated Card Status: " + updatedCardStatus);
//            } catch (CardNotFoundException e) {
//                System.err.println(e.getMessage());
//            }
//
//            // Get active cards
//            List<CardDTO> activeCards = cardService.getActiveCards();
//            System.out.println("Active Cards: " + activeCards);
//
//            // Delete card
//            try {
//                cardService.deleteCard(createdCard.getId());
//                System.out.println("Card deleted.");
//            } catch (CardNotFoundException e) {
//                System.err.println(e.getMessage());
//            }
//        };
//    }



    @Bean
    CommandLineRunner run(ICardService cardService, AccountServiceClient accountServiceClient) {
        return args -> {
            // Create a new card for 1st account
            CardDTO newCardDTO = new CardDTO();
            newCardDTO.setExpirationDate(LocalDate.of(2025, 12, 31));
            newCardDTO.setActive(true);
            newCardDTO.setCardType(CardType.VISA);

            // Set a sample account ID (replace with a valid account ID)
            newCardDTO.setAccountId(1L);

            // Use the saveCardWithGeneratedNumber method to create a card with a generated card number
            CardDTO createdCard = cardService.saveCardWithGeneratedNumber(newCardDTO);
            System.out.println("Created Card: " + createdCard);

            // -----------------------------------------------------------------------

            // Create a new card for 2nd account
            CardDTO newCardDTO2 = new CardDTO();
            newCardDTO.setExpirationDate(LocalDate.of(2026, 05, 21));
            newCardDTO.setActive(true);
            newCardDTO.setCardType(CardType.MASTERCARD);

            // Set a sample account ID (replace with a valid account ID)
            newCardDTO.setAccountId(2L);

            // Use the saveCardWithGeneratedNumber method to create a card with a generated card number
            CardDTO createdCard2 = cardService.saveCardWithGeneratedNumber(newCardDTO);
            System.out.println("Created Card: " + createdCard);


            // -----------------------------------------------------------------------

            // Create a new card for 3rd account
            CardDTO newCardDTO3 = new CardDTO();
            newCardDTO.setExpirationDate(LocalDate.of(2027, 12, 30));
            newCardDTO.setActive(false);
            newCardDTO.setCardType(CardType.MASTERCARD);

            // Set a sample account ID (replace with a valid account ID)
            newCardDTO.setAccountId(3L);

            // Use the saveCardWithGeneratedNumber method to create a card with a generated card number
            CardDTO createdCard3 = cardService.saveCardWithGeneratedNumber(newCardDTO);
            System.out.println("Created Card: " + createdCard);

            // Retrieve the card and fetch the associated account data
            CardDTO retrievedCard = cardService.getCardById(createdCard.getId());
            Long accountId = retrievedCard.getAccountId();

            if (accountId != null) {
                Account account = accountServiceClient.getAccountById(accountId);
                System.out.println("Retrieved Card: " + retrievedCard);
                System.out.println("Associated Account: " + account);
            }

            // Update the card's expiration date
            retrievedCard.setExpirationDate(LocalDate.of(2026, 5, 22));
            CardDTO updatedCard = cardService.updateCard(createdCard.getId(), retrievedCard);
            System.out.println("Updated Card: " + updatedCard);

            // Get all cards
            List<CardDTO> allCards = cardService.getAllCards();
            for (CardDTO cardDTO : allCards) {
                if (cardDTO.getAccountId() != null) {
                    Account account = accountServiceClient.getAccountById(cardDTO.getAccountId());
                    cardDTO.setAccount(account);
                }
            }
            System.out.println("All Cards: " + allCards);

            // Update card status
            CardDTO updatedStatusCard = cardService.updateCardStatus(createdCard.getId(), false);
            System.out.println("Updated Card Status: " + updatedStatusCard);

            // Get active cards
            List<CardDTO> activeCards = cardService.getActiveCards();
            System.out.println("Active Cards: " + activeCards);

            // Delete a card
            // cardService.deleteCard(cardId);
            // System.out.println("Card deleted.");
        };
    }


}
