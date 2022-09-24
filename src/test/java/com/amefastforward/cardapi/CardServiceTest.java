package com.amefastforward.cardapi;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.amefastforward.cardapi.controller.request.CardRequest;
import com.amefastforward.cardapi.exception.EntityNotFoundException;
import com.amefastforward.cardapi.model.CardOrigin;
import com.amefastforward.cardapi.repository.CardOriginRepository;
import com.amefastforward.cardapi.repository.CardRepository;
import com.amefastforward.cardapi.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;


public class CardServiceTest {
    @Mock
    CardOriginRepository cardOriginRepository;

    @Mock
    CardRepository cardRepository;

    @InjectMocks
    CardService cardService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Retornar erro ao criar card sem origem")
    void shouldReturnErrorCreateCardWhenOriginNotFound(){
        when(cardOriginRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> cardService.createCard(new CardRequest()));
        verify(cardRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve criar o card")
    void shouldSaveCardOnCreate(){
        var origin = new CardOrigin();

        when(cardOriginRepository.findById(any())).thenReturn(Optional.of(origin));
        when(cardRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        var cardRequest = new CardRequest();
        cardRequest.setName(cardRequest.getName());
        cardRequest.setDescription(cardRequest.getDescription());
        cardRequest.setImageUrl(cardRequest.getImageUrl());
        cardRequest.setStrength(cardRequest.getStrength());
        cardRequest.setSpeed(cardRequest.getSpeed());
        cardRequest.setSkill(cardRequest.getSkill());
        cardRequest.setIntellect(cardRequest.getIntellect());
        cardRequest.setGear(cardRequest.getGear());

        var card = cardService.createCard(cardRequest);

        assertEquals(card.getName(), cardRequest.getName());

    }
}
