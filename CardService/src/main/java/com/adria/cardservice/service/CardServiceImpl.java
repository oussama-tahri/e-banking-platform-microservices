package com.adria.cardservice.service;

import com.adria.cardservice.dtos.CardDTO;
import com.adria.cardservice.entities.Card;
import com.adria.cardservice.enums.CardType;
import com.adria.cardservice.exceptions.CardNotFoundException;
import com.adria.cardservice.feign.AccountServiceClient;
import com.adria.cardservice.mapper.CardMapper;
import com.adria.cardservice.models.Account;
import com.adria.cardservice.repositories.CardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CardServiceImpl implements ICardService {
    private final CardRepository cardRepository;
    private final AccountServiceClient accountServiceClient;
    private final CardMapper cardMapper;


    private String generateCardNumber(String accountNumber) {
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 6); // Use a unique identifier
        return accountNumber + uniqueSuffix;
    }

    @Override
    public CardDTO saveCardWithGeneratedNumber(CardDTO cardDTO) {
        Account account = accountServiceClient.getAccountById(cardDTO.getAccountId());

        // Generate a unique card number based on the account's account number
        String cardNumber = generateCardNumber(account.getAccountNumber());

        Card card = cardMapper.fromCardDTO(cardDTO);
        card.setCardNumber(cardNumber);
        card.setExpirationDate(LocalDate.of(2026, 05, 21));
        card.setActive(true);
        card = cardRepository.save(card);
        return cardMapper.fromCard(card);
    }

    @Override
    public CardDTO createCard(CardDTO cardDTO) {
//        Card card = cardMapper.fromCardDTO(cardDTO);
//
//        if (cardDTO.getAccountId() != null) {
//            Account account = accountServiceClient.getAccountById(cardDTO.getAccountId());
//            card.setAccount(account);
//        }
//
//        card = cardRepository.save(card);
//        return cardMapper.fromCard(card);
        // Generate card number logic
        return saveCardWithGeneratedNumber(cardDTO);
    }

//    @Override
//    public CardDTO getCardById(Long cardId) throws CardNotFoundException {
////        Card card = cardRepository.findById(cardId)
////                .orElseThrow(() -> new CardNotFoundException("Card not found with ID: " + cardId));
////
////        return cardMapper.fromCard(card);
//            Card card = cardRepository.findById(cardId)
//                    .orElseThrow(() -> new CardNotFoundException("Card not found with ID: " + cardId));
//
//            // Fetch account information using Feign client
//            Account account = accountServiceClient.getAccountById(card.getAccountId());
//
//            // Populate the transient account field
//            card.setAccount(account);
//
//            return cardMapper.fromCard(card);
//
//    }

    @Override
    public CardDTO getCardById(Long cardId) throws CardNotFoundException {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardNotFoundException("Card not found"));

        CardDTO cardDTO = cardMapper.fromCard(card);

        if (card.getAccountId() != null) {
            Account account = accountServiceClient.getAccountById(card.getAccountId());
            cardDTO.setAccount(account);
        }

        return cardDTO;
    }


    @Override
    public CardDTO updateCard(Long cardId, CardDTO cardDTO) throws CardNotFoundException {
        Card existingCard = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardNotFoundException("Card not found with ID: " + cardId));

        cardMapper.updateCardFromDTO(cardDTO, existingCard);

        if (cardDTO.getAccountId() != null) {
            Account account = accountServiceClient.getAccountById(cardDTO.getAccountId());
            existingCard.setAccount(account);
        }

        existingCard = cardRepository.save(existingCard);
        return cardMapper.fromCard(existingCard);
    }

    @Override
    public void deleteCard(Long cardId) throws CardNotFoundException {
        Card existingCard = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardNotFoundException("Card not found with ID: " + cardId));

        cardRepository.delete(existingCard);
    }

    @Override
    public List<CardDTO> getAllCards() {
//        List<Card> cards = cardRepository.findAll();
//        return cards.stream().map(card -> {
//            CardDTO cardDTO = cardMapper.fromCard(card);
//            if (card.getAccountId() != null) {
//                Account account = accountServiceClient.getAccountById(card.getAccountId());
//                cardDTO.setAccountId(account.getId());
//            }
//            return cardDTO;
//        }).collect(Collectors.toList());
            List<Card> cards = cardRepository.findAll();

            return cards.stream().map(card -> {
                CardDTO cardDTO = cardMapper.fromCard(card);
                if (card.getAccountId() != null) {
                    Account account = accountServiceClient.getAccountById(card.getAccountId());
                    cardDTO.setAccount(account);
                }
                return cardDTO;
            }).collect(Collectors.toList());
    }


    @Override
    public CardDTO updateCardStatus(Long cardId, boolean active) throws CardNotFoundException {
        Card existingCard = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardNotFoundException("Card not found with ID: " + cardId));

        existingCard.setActive(active);
        existingCard = cardRepository.save(existingCard);
        return cardMapper.fromCard(existingCard);
    }

    @Override
    public List<CardDTO> getActiveCards() {
        List<Card> activeCards = cardRepository.findByActive(true);
        return activeCards.stream().map(cardMapper::fromCard).collect(Collectors.toList());
    }

    @Override
    public List<CardDTO> getCardsByAccount(Long accountId) {
        List<Card> cards = cardRepository.findByAccountId(accountId);
        return cards.stream().map(cardMapper::fromCard).collect(Collectors.toList());
    }

    @Override
    public List<CardDTO> getCardsByType(CardType cardType) {
        List<Card> cards = cardRepository.findByCardType(cardType);
        return cards.stream().map(cardMapper::fromCard).collect(Collectors.toList());
    }

    @Override
    public int getcountcardByCustomer(Long customerId){
      List<CardDTO> cardDTOS=getAllCards();

        List<CardDTO> filteredCards = cardDTOS.stream()
                .filter(cardDTO -> cardDTO.getAccount().getCustomerId() == customerId)
                .collect(Collectors.toList());


        return filteredCards.size();
    }
}
