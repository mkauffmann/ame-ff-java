package com.amefastforward.cardapi.service;

import com.amefastforward.cardapi.controller.request.CardOriginRequest;
import com.amefastforward.cardapi.exception.EntityNotFoundException;
import com.amefastforward.cardapi.model.CardOrigin;
import com.amefastforward.cardapi.repository.CardOriginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CardOriginService {

    private final CardOriginRepository cardOriginRepository;

    @Autowired
    public CardOriginService(CardOriginRepository cardOriginRepository) {
        this.cardOriginRepository = cardOriginRepository;
    }

    public CardOrigin findById(long id){
        return this.cardOriginRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card origin id [" + id + "] not found"));

    }

    public List<CardOrigin> listAll(){
        return this.cardOriginRepository.findAll();
    }

    public CardOrigin createCardOrigin(CardOriginRequest cardOriginRequest){
        var cardOrigin = new CardOrigin();

        cardOrigin.setName(cardOriginRequest.getName());
        cardOrigin.setDescription(cardOriginRequest.getDescription());
        cardOrigin.setCreator(cardOriginRequest.getCreator());
        cardOrigin.setCreateAt(LocalDateTime.now());
        cardOrigin.setUpdateAt(LocalDateTime.now());

        return this.cardOriginRepository.save(cardOrigin);
    }

    public CardOrigin update(long id, CardOriginRequest cardOriginRequest){
        var cardOrigin = this.findById(id);

        cardOrigin.setName(cardOriginRequest.getName());
        cardOrigin.setDescription(cardOriginRequest.getDescription());
        cardOrigin.setCreator(cardOriginRequest.getCreator());
        cardOrigin.setUpdateAt(LocalDateTime.now());

        return this.cardOriginRepository.save(cardOrigin);

    }

    public void delete(long id){
        this.cardOriginRepository.deleteById(id);
    }
}
