package com.adria.transactionservice.feign;

import com.adria.transactionservice.models.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "card-service")
public interface CardService {
    @GetMapping("/cards/{cardId}")
    public Card getCard(@PathVariable Long cardId);
}
