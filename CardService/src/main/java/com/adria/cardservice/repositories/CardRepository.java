package com.adria.cardservice.repositories;

import com.adria.cardservice.entities.Card;
import com.adria.cardservice.enums.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByAccountId(Long accountId);

    List<Card> findByActive(boolean b);

    List<Card> findByCardType(CardType cardType);

    @Query(value = "SELECT COALESCE(MAX(card_number), '0') FROM card WHERE account_id = :accountId", nativeQuery = true)
    String findMaxCardNumberByAccountId(@Param("accountId") Long accountId);

    default String generateNextCardNumber(Long accountId) {
        String maxCardNumber = findMaxCardNumberByAccountId(accountId);

        // Extract the numeric portion of the maxCardNumber and increment it
        String numericPart = maxCardNumber.replaceAll("[^0-9]", "");
        Long nextNumber = Long.parseLong(numericPart) + 1;

        // Format the next number with leading zeros to match your desired length
        String formattedNextNumber = String.format("%016d", nextNumber);

        // Combine the formatted number with a prefix or any other formatting you need
        String generatedCardNumber = "ADRIA" + formattedNextNumber;

        return generatedCardNumber;
    }


    default Card saveCardWithGeneratedNumber(Card card) {
        Long accountId = card.getAccountId();
        String generatedCardNumber = generateNextCardNumber(accountId);
        card.setCardNumber(generatedCardNumber);
        return save(card);
    }
}
