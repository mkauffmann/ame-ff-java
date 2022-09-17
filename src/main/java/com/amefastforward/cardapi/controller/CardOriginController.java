package com.amefastforward.cardapi.controller;


import com.amefastforward.cardapi.controller.request.CreateCardOriginRequest;
import com.amefastforward.cardapi.model.CardOrigin;
import com.amefastforward.cardapi.service.CardOriginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card-origin")
public class CardOriginController {

    private CardOriginService cardOriginService;
    private static final Logger LOG = LoggerFactory.getLogger(CardOriginController.class);

    @Autowired
    public CardOriginController(CardOriginService cardOriginService) {
        this.cardOriginService = cardOriginService;
    }

    @PostMapping
    public CardOrigin createCardOrigin(@RequestBody CreateCardOriginRequest cardOriginRequest){
        LOG.info("Inserindo o origin da carta: {}}", cardOriginRequest);
        return this.cardOriginService.createCardOrigin(cardOriginRequest);
    }

    @GetMapping("/{id}")
    public CardOrigin findCardOriginById(@PathVariable("id") long id){
        LOG.info("Buscando origem com id {}", id);
        return this.cardOriginService.findById(id);
    }
}
