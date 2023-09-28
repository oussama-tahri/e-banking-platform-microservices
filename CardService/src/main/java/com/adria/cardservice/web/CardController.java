package com.adria.cardservice.web;

import com.adria.cardservice.dtos.CardDTO;
import com.adria.cardservice.enums.CardType;
import com.adria.cardservice.exceptions.CardNotFoundException;
import com.adria.cardservice.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE ,RequestMethod.PATCH})
public class CardController {

    private final ICardService cardService;

    @PostMapping
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CardDTO> createCard(@RequestBody CardDTO cardDTO) {
        CardDTO createdCard = cardService.createCard(cardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCard);
    }

    @GetMapping("/{cardId}")
    //@PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CardDTO> getCardById(@PathVariable Long cardId) throws CardNotFoundException {
        CardDTO cardDTO = cardService.getCardById(cardId);
        return ResponseEntity.ok(cardDTO);
    }

    @PutMapping("/{cardId}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CardDTO> updateCard(@PathVariable Long cardId, @RequestBody CardDTO cardDTO) throws CardNotFoundException {
        CardDTO updatedCard = cardService.updateCard(cardId, cardDTO);
        return ResponseEntity.ok(updatedCard);
    }

    @DeleteMapping("/{cardId}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteCard(@PathVariable Long cardId) throws CardNotFoundException {
        cardService.deleteCard(cardId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    //@PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<CardDTO>> getAllCards() {
        List<CardDTO> cards = cardService.getAllCards();
        return ResponseEntity.ok(cards);
    }


    @PatchMapping("/{cardId}/status")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CardDTO> updateCardStatus(@PathVariable Long cardId, @RequestParam boolean active) throws CardNotFoundException {
        CardDTO updatedCard = cardService.updateCardStatus(cardId, active);
        return ResponseEntity.ok(updatedCard);
    }

    @GetMapping("/active")
    //@PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<CardDTO>> getActiveCards() {
        List<CardDTO> activeCards = cardService.getActiveCards();
        return ResponseEntity.ok(activeCards);
    }

    @GetMapping("/account/{accountId}")
    //@PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<CardDTO>> getCardsByAccount(@PathVariable Long accountId) {
        List<CardDTO> cards = cardService.getCardsByAccount(accountId);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/type/{cardType}")
    //@PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<CardDTO>> getCardsByType(@PathVariable CardType cardType) {
        List<CardDTO> cards = cardService.getCardsByType(cardType);
        return ResponseEntity.ok(cards);
    }
    @GetMapping("/customer/{customerId}/count")
    //@PreAuthorize("hasAuthority('USER')")
    public int getcountCrdsByCustomer(@PathVariable Long customerId){
        return cardService.getcountcardByCustomer(customerId);
    }
}

