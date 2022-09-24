package com.amefastforward.cardapi.repository.impl;

import com.amefastforward.cardapi.config.DatabaseConfig;
import com.amefastforward.cardapi.exception.ApplicationException;
import com.amefastforward.cardapi.exception.InvalidEntityAttributeException;
import com.amefastforward.cardapi.model.Card;
import com.amefastforward.cardapi.model.CardOrigin;
import com.amefastforward.cardapi.repository.CardRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;

@Repository
public class CardRepositoryImpl implements CardRepository {
    private static final Logger LOG = LogManager.getLogger(CardRepositoryImpl.class);
    private final ConnectionFactory connectionFactory;

    @Autowired
    public CardRepositoryImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Optional<Card> findById(long id) {
        String query = "SELECT * FROM card WHERE id = ?";

        try(Connection connection = connectionFactory.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setLong(1, id);
                statement.execute();

                ResultSet resultSet = statement.getResultSet();

                if(resultSet.next()){
                    Card card = new Card();
                    CardOrigin origin = new CardOrigin();

                    card.setId(resultSet.getLong("id"));
                    card.setName(resultSet.getString("name"));
                    card.setDescription(resultSet.getString("description"));
                    card.setImageUrl(resultSet.getString("image_url"));
                    card.setStrength(resultSet.getInt("strength"));
                    card.setSpeed(resultSet.getInt("speed"));
                    card.setSkill(resultSet.getInt("skill"));
                    card.setGear(resultSet.getInt("gear"));
                    card.setIntellect(resultSet.getInt("intellect"));
                    card.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                    card.setUpdateAt(resultSet.getTimestamp("update_at").toLocalDateTime());

                    origin.setId(resultSet.getLong("origin_id"));
                    card.setOrigin(origin);

                    return Optional.of(card);

                }
            }

        }catch (SQLException e){
            LOG.error("{}", e.getMessage());
            throw new ApplicationException(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Card save(Card card) {
        String insertQuery = "INSERT INTO card (name, description, image_url, strength, speed, skill, gear, intellect, created_at, update_at, origin_id "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        try (Connection connection = connectionFactory.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, card.getName());
                statement.setString(2, card.getDescription());
                statement.setString(3, card.getImageUrl());
                statement.setInt(4, card.getStrength());
                statement.setInt(5, card.getSpeed());
                statement.setInt(6, card.getSkill());
                statement.setInt(7, card.getGear());
                statement.setInt(8, card.getIntellect());
                statement.setTimestamp(9, Timestamp.valueOf(card.getCreatedAt()));
                statement.setTimestamp(10, Timestamp.valueOf(card.getUpdateAt()));
                statement.setNull(11, Types.INTEGER);

                statement.execute();

                ResultSet resultSet = statement.getGeneratedKeys();

                if (resultSet.next()) {
                    card.setId(resultSet.getInt(1));
                }

                return card;
            }
        } catch (SQLException e) {
            LOG.error("{}", e.getMessage());
            throw new ApplicationException(e.getMessage());
        }
    }}

