package com.amefastforward.cardapi.controller;

import com.amefastforward.cardapi.controller.request.CardRequest;
import com.amefastforward.cardapi.model.Card;
import com.amefastforward.cardapi.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;
    private static final Logger LOG = LoggerFactory.getLogger(CardController.class);

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Card findCardById(@PathVariable("id") long id){
        LOG.info("Buscando card com id {}", id);

        return cardService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Card> listAllCards(){
        LOG.info("Buscando todos os cards");
        return this.cardService.listAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Card createCard(@RequestBody CardRequest cardRequest){
        LOG.info("Criando card {}", cardRequest);
        return cardService.createCard(cardRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Card updateCard(@PathVariable("id") long id, @RequestBody CardRequest cardRequest){
        LOG.info("Atualizando card com id [{}]", id);
        return cardService.update(id, cardRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable("id") long id){
        LOG.info("Excluindo card com id [{}]", id);
        cardService.delete(id);
    }
}
