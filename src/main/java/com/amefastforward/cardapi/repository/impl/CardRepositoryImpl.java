package com.amefastforward.cardapi.repository.impl;

import com.amefastforward.cardapi.model.Card;
import com.amefastforward.cardapi.repository.CardRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CardRepositoryImpl implements CardRepository {
    private final List<Card> cards;

    public CardRepositoryImpl() {
        this.cards = new ArrayList<Card>();

        var card = new Card();
        card.setId(1);
        card.setName("Iron Man");
        card.setImageUrl("imgUrl");
        card.setStrength(5);
        card.setSpeed(5);
        card.setGear(5);
        card.setIntellect(8);
        card.setSkill(6);
        card.setCreatedAt(LocalDateTime.now());
        card.setUpdateAt(LocalDateTime.now());

        cards.add(card);

        card = new Card();
        card.setId(2);
        card.setName("Spider Man");
        card.setImageUrl("imgUrl");
        card.setStrength(5);
        card.setSpeed(7);
        card.setGear(4);
        card.setIntellect(7);
        card.setSkill(6);
        card.setCreatedAt(LocalDateTime.now());
        card.setUpdateAt(LocalDateTime.now());

        cards.add(card);


    }

    @Override
    public Optional<Card> findById(long id) {
        return cards.stream()
                .filter(card -> card.getId() == id)
                .findFirst();
    }
}
