package com.amefastforward.cardapi.service;

import com.amefastforward.cardapi.controller.request.CardRequest;
import com.amefastforward.cardapi.exception.EntityNotFoundException;
import com.amefastforward.cardapi.model.Card;
import com.amefastforward.cardapi.repository.CardOriginRepository;
import com.amefastforward.cardapi.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public Card createCard(CardRequest cardRequest){
        var cardOrigin = cardOriginRepository.findById(cardRequest.getOriginId())
               .orElseThrow(() -> new EntityNotFoundException("Card origin id [" + cardRequest.getOriginId() + "] not found"));
        var card = new Card();

        card.setName(cardRequest.getName());
        card.setDescription(cardRequest.getDescription());
        card.setImageUrl(cardRequest.getImageUrl());
        card.setStrength(cardRequest.getStrength());
        card.setSpeed(cardRequest.getSpeed());
        card.setSkill(cardRequest.getSkill());
        card.setIntellect(cardRequest.getIntellect());
        card.setGear(cardRequest.getGear());
        card.setOrigin(cardOrigin);
        card.setCreatedAt(LocalDateTime.now());
        card.setUpdateAt(LocalDateTime.now());

        return cardRepository.save(card);

    }

    public void delete(long id){
        var card = this.findById(id);

        cardRepository.delete(card);
    }

    public List<Card> listAll(){
        return this.cardRepository.findAll();
    }

    public Card update(long id, CardRequest cardRequest){
        var card = this.findById(id);

        card.setName(cardRequest.getName());
        card.setDescription(cardRequest.getDescription());
        card.setImageUrl(cardRequest.getImageUrl());
        card.setStrength(cardRequest.getStrength());
        card.setSpeed(cardRequest.getSpeed());
        card.setSkill(cardRequest.getSkill());
        card.setIntellect(cardRequest.getIntellect());
        card.setGear(cardRequest.getGear());
        card.setUpdateAt(LocalDateTime.now());

        if(card.getOrigin() == null || card.getOrigin().getId() != cardRequest.getOriginId()){
            var origin = cardOriginRepository.findById(cardRequest.getOriginId())
                    .orElseThrow(() -> new EntityNotFoundException("Card origin id [" + cardRequest.getOriginId() + "]"));

            card.setOrigin(origin);
        }

        return cardRepository.save(card);

    }
}
