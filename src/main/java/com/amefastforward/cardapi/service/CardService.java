package com.amefastforward.cardapi.service;

import com.amefastforward.cardapi.controller.request.CreateCardRequest;
import com.amefastforward.cardapi.exception.EntityNotFoundException;
import com.amefastforward.cardapi.model.Card;
import com.amefastforward.cardapi.model.CardOrigin;
import com.amefastforward.cardapi.repository.CardOriginRepository;
import com.amefastforward.cardapi.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final CardOriginRepository cardOriginRepository;

    @Autowired
    public CardService(CardRepository cardRepository, CardOriginRepository cardOriginRepository) {
        this.cardRepository = cardRepository;
        this.cardOriginRepository = cardOriginRepository;
    }

    public Card findById(long id){
        return this.cardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card id [" + id + "] not found"));
    }

    public Card createCard(CreateCardRequest createCardRequest){
        //var cardOrigin = cardOriginRepository.findById(createCardRequest.getOriginId())
        //        .orElseThrow(() -> new EntityNotFoundException("Card origin id [" + createCardRequest.getOriginId() + "] not found"));
        var card = new Card();

        card.setName(createCardRequest.getName());
        card.setDescription(createCardRequest.getDescription());
        card.setImageUrl(createCardRequest.getImageUrl());
        card.setStrength(createCardRequest.getStrength());
        card.setSpeed(createCardRequest.getSpeed());
        card.setSkill(createCardRequest.getSkill());
        card.setIntellect(createCardRequest.getIntellect());
        card.setGear(createCardRequest.getGear());
       // card.setOrigin(cardOrigin);
        card.setCreatedAt(LocalDateTime.now());
        card.setUpdateAt(LocalDateTime.now());

        return cardRepository.save(card);

    }
}
