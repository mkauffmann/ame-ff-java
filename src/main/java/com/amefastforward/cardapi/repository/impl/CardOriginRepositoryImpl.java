package com.amefastforward.cardapi.repository.impl;

import com.amefastforward.cardapi.exception.InvalidEntityAttributeException;
import com.amefastforward.cardapi.model.CardOrigin;
import com.amefastforward.cardapi.repository.CardOriginRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CardOriginRepositoryImpl implements CardOriginRepository {

    private final List<CardOrigin> cardOrigins = new ArrayList<CardOrigin>();
    @Override
    public CardOrigin save(CardOrigin cardOrigin) {
        var cardOriginFound = cardOrigins.stream()
                .filter(storedCard -> storedCard.getName().equalsIgnoreCase(cardOrigin.getName()))
                .findFirst();

        if(cardOriginFound.isPresent()){
            throw new InvalidEntityAttributeException("Card origin name [" + cardOrigin.getName() + "] already stored");
        }

        cardOrigin.setId(cardOrigins.size() + 1);
        cardOrigins.add(cardOrigin);

        return cardOrigin;
    }

    @Override
    public Optional<CardOrigin> findById(long id) {
        return cardOrigins.stream()
                .filter(storedCard -> storedCard.getId() == id)
                .findFirst();
    }
}
