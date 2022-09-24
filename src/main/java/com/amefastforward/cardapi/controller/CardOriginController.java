package com.amefastforward.cardapi.controller;


import com.amefastforward.cardapi.controller.request.CardOriginRequest;
import com.amefastforward.cardapi.model.CardOrigin;
import com.amefastforward.cardapi.service.CardOriginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card-origin")
public class CardOriginController {

    private CardOriginService cardOriginService;
    private static final Logger LOG = LoggerFactory.getLogger(CardOriginController.class);

    @Autowired
    public CardOriginController(CardOriginService cardOriginService) {
        this.cardOriginService = cardOriginService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CardOrigin createCardOrigin(@RequestBody CardOriginRequest cardOriginRequest){
        LOG.info("Inserindo o origin da carta: {}}", cardOriginRequest);
        return this.cardOriginService.createCardOrigin(cardOriginRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CardOrigin findCardOriginById(@PathVariable("id") long id){
        LOG.info("Buscando origem com id {}", id);
        return this.cardOriginService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<CardOrigin> findAllCardOrigin(){
        LOG.info("Listando todas as origens de cartas");
        return this.cardOriginService.listAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    public CardOrigin updateCardOrigin(@PathVariable("id") long id, @RequestBody CardOriginRequest cardOriginRequest){
        LOG.info("Atualizando card com id [{}]", id);
        return this.cardOriginService.update(id, cardOriginRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("{id}")
    public void deleteCardOrigin(@PathVariable("id") long id){
        LOG.info("Deletando cardOrigin com id [{}]", id);
        this.cardOriginService.delete(id);
    }
}
