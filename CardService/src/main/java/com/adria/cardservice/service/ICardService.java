package com.adria.cardservice.service;

import com.adria.cardservice.dtos.CardDTO;
import com.adria.cardservice.enums.CardType;
import com.adria.cardservice.exceptions.CardNotFoundException;

import java.util.List;

public interface ICardService {
    CardDTO saveCardWithGeneratedNumber(CardDTO cardDTO);

    CardDTO createCard(CardDTO cardDTO);

    CardDTO getCardById(Long cardId) throws CardNotFoundException;

    CardDTO updateCard(Long cardId, CardDTO cardDTO) throws CardNotFoundException;

    void deleteCard(Long cardId) throws CardNotFoundException;

    List<CardDTO> getAllCards();
    CardDTO updateCardStatus(Long cardId, boolean active) throws CardNotFoundException;

    List<CardDTO> getActiveCards();

    List<CardDTO> getCardsByAccount(Long accountId);

    List<CardDTO> getCardsByType(CardType cardType);

    int getcountcardByCustomer(Long customerId);
}
