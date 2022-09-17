package com.amefastforward.cardapi.repository.impl;

import com.amefastforward.cardapi.exception.InvalidEntityAttributeException;
import com.amefastforward.cardapi.model.Card;
import com.amefastforward.cardapi.repository.CardRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CardRepositoryImpl implements CardRepository {
    private final List<Card> cards = new ArrayList<Card>();

    @Override
    public Optional<Card> findById(long id) {
        return cards.stream()
                .filter(card -> card.getId() == id)
                .findFirst();
    }

    @Override
    public Card save(Card card) {
        var cardFound = cards.stream()
                .filter(storedCard -> storedCard.getName() == card.getName())
                .findFirst();

        if(cardFound.isPresent()){
            throw new InvalidEntityAttributeException("Card name [" + card.getName() + "] already stored");
        }

        card.setId(cards.size() + 1);
        cards.add(card);

        return card;
    }
}
