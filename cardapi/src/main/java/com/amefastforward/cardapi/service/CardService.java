package com.amefastforward.cardapi.service;

import com.amefastforward.cardapi.model.Card;
import com.amefastforward.cardapi.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Optional<Card> findById(long id){
        return this.cardRepository.findById(id);
    }
}