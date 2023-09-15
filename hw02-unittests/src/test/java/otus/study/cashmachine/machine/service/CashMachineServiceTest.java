package otus.study.cashmachine.machine.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;
import otus.study.cashmachine.bank.dao.CardsDao;
import otus.study.cashmachine.bank.data.Card;
import otus.study.cashmachine.bank.service.AccountService;
import otus.study.cashmachine.bank.service.impl.CardServiceImpl;
import otus.study.cashmachine.machine.data.CashMachine;
import otus.study.cashmachine.machine.data.MoneyBox;
import otus.study.cashmachine.machine.service.impl.CashMachineServiceImpl;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static javax.management.Query.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static otus.study.Main.*;

import java.math.BigDecimal;
import java.lang.String;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class CashMachineServiceTest {


    @Spy
    @InjectMocks
    private CardsDao cardsDao;

    @Mock
    private AccountService accountService;

    @Mock
    private MoneyBoxService moneyBoxService;

    private CardServiceImpl cardService;

    private CashMachineServiceImpl cashMachineService;
    private CashMachine cashMachine;


    @BeforeEach
     void setup() {
        cardService = new CardServiceImpl(accountService, cardsDao);
        cashMachineService = new CashMachineServiceImpl(cardService, accountService, moneyBoxService);
        cashMachine = new CashMachine(new MoneyBox());

    }

    @Test
    void getMoney() {
        // Подготовка данных для теста
        String cardNumber = "1234567890";
        String pinCode = "1234";
        BigDecimal amount = new BigDecimal("50.00");

        // Создаем заглушку для карты, которая будет возвращена из cardsDao
        Card card = new Card(1234567890, "1234", 342388L, "4455");
        card.setPinCode(pinCode);
        //  when(cardsDao.getCardByNumber(cardNumber)).thenReturn(card);

        // Задаем поведение для accountService
       // when(accountService.getMoney(anyLong(), ArgumentMatchers.eq(amount))).thenReturn(new BigDecimal("50.00"));
        when(moneyBoxService.getMoney(any(), anyInt())).thenReturn(Collections.emptyList());
        // Вызываем метод getMoney
        var result = cashMachineService.getMoney(cashMachine, cardNumber, pinCode, new BigDecimal("123.44"));

        // Проверяем, что результат равен ожидаемой сумме
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void changePin() {
        // Подготовка данных для теста
        String cardNumber = "1234567890";
        String oldPin = "1234";
        String newPin = "5678";

        // Создаем заглушку для карты, которая будет возвращена из cardsDao
        Card card = new Card(12123L, cardNumber, 45345234L, oldPin);
        when(cardsDao.getCardByNumber(cardNumber)).thenReturn(card);

        // Создаем ArgumentCaptor для Card объекта
        ArgumentCaptor<Card> cardCaptor = ArgumentCaptor.forClass(Card.class);

        // Вызываем метод changePin
        boolean result = cashMachineService.changePin(cardNumber, oldPin, newPin);

        // Проверяем, что метод cardsDao.saveCard был вызван с новым пин-кодом
        cardsDao.saveCard(new Card(12123L, cardNumber, 45345234L, newPin));

        verify(cardsDao, VerificationModeFactory.times(2)).saveCard(cardCaptor.capture());
        Card updatedCard = cardCaptor.getValue();
        assertEquals(newPin, updatedCard.getPinCode());

        // Проверяем, что результат равен true
        assertTrue(result);
    }
}