package com.adria.cardservice.mapper;

import com.adria.cardservice.dtos.CardDTO;
import com.adria.cardservice.entities.Card;
import com.adria.cardservice.feign.AccountServiceClient;
import com.adria.cardservice.models.Account;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardMapper {
    private AccountServiceClient accountServiceClient;

    public CardMapper(AccountServiceClient accountServiceClient) {

        this.accountServiceClient = accountServiceClient;
    }

    public CardDTO fromCard(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(card.getId());
        cardDTO.setCardNumber(card.getCardNumber());
        cardDTO.setExpirationDate(card.getExpirationDate());
        cardDTO.setActive(card.isActive());
        cardDTO.setCardType(card.getCardType());
        cardDTO.setAccountId(card.getAccountId()); // Set accountId

        if (card.getAccountId() != null) {
            Account account = accountServiceClient.getAccountById(card.getAccountId());

            // Populate account details if account information is retrieved
            if (account != null) {
                Account accountWithoutNulls = new Account();
                accountWithoutNulls.setId(account.getId());
                accountWithoutNulls.setAccountNumber(account.getAccountNumber());
                accountWithoutNulls.setBalance(account.getBalance());
                cardDTO.setAccount(accountWithoutNulls);
            }
        }

        return cardDTO;
    }





    public Card fromCardDTO(CardDTO cardDTO) {
        Card card = new Card();
        BeanUtils.copyProperties(cardDTO, card);
        card.setCardType(cardDTO.getCardType()); // Set cardType
        card.setAccountId(cardDTO.getAccountId()); // Set accountId
        return card;
    }

    public void updateCardFromDTO(CardDTO cardDTO, Card card) {
        BeanUtils.copyProperties(cardDTO, card, "id");
        card.setCardType(cardDTO.getCardType()); // Update cardType
        card.setAccountId(cardDTO.getAccountId()); // Update accountId
    }
}

