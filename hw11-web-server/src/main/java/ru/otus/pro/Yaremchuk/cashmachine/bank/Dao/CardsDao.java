package ru.otus.pro.Yaremchuk.cashmachine.bank.Dao;

import org.springframework.stereotype.Service;
import ru.otus.pro.Yaremchuk.cashmachine.bank.data.Card;
import ru.otus.pro.Yaremchuk.cashmachine.bank.db.Cards;

@Service
public class CardsDao {
    public Card getCardByNumber(String cardNumber) {
        return Cards.cards.get(cardNumber);
    }

    public Card saveCard(Card card) {
        if (card.getId() == 0) {
            return createCard(card.getNumber(), card.getAccountId(), card.getPinCode());
        }
        return Cards.cards.put(card.getNumber(), card);
    }

    public Card createCard(String number, Long accountId, String pinCode) {
        if (Cards.cards.get(number) != null) {
            throw new IllegalStateException("Card already exists");
        }

        Long id = Cards.getNextId();
        Card newCard = new Card(id, number, accountId, pinCode);

        Cards.cards.put(number, newCard);
        return newCard;
    }
}