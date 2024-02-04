package ru.otus.pro.Yaremchuk.webserver.cashmachine.bank.dao;

import org.springframework.stereotype.Service;
import ru.otus.pro.Yaremchuk.webserver.cashmachine.bank.data.Card;
import ru.otus.pro.Yaremchuk.webserver.cashmachine.bank.db.Cards;

@Service
public class CardsDao {
    public Card getCardByNumber(String cardNumber) {
        return Cards.cards.get(cardNumber);
    }

    public Card saveCard(Card card) {
        if (card.getId() == 0) {
            return createCard(card.getNumber(), card.getAccountId(), card.getPinCode());
        }
        Cards.cards.put(card.getNumber(), card);
        return card;
    }

    public Card createCard(String number, Long accountId, String pinCode) {
        if (Cards.cards.containsKey(number)) {
            throw new IllegalStateException("Card already exists");
        }

        Long id = Cards.getNextId();
        Card newCard = new Card(id, number, accountId, pinCode);

        Cards.cards.put(number, newCard);
        return newCard;
    }
}