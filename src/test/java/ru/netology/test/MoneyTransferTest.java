package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoneyTransferTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationInfo = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationInfo);
    }

    @Test
    void shouldTransferMoneyFromFirst() {

        val dashboardPage = new DashboardPage();
        int amount = 500;
        val balanceFirstResult = dashboardPage.getFirstCardBalance() - amount;
        val balanceSecondResult = dashboardPage.getSecondCardBalance() + amount;
        val transferPage = dashboardPage.secondCardBalance();
        transferPage.actionTransfer(DataHelper.getFirstCardNumber(), amount);
        val actualFirstResult = dashboardPage.getFirstCardBalance();
        val actualSecondResult = dashboardPage.getSecondCardBalance();
        assertEquals(balanceFirstResult, actualFirstResult);
        assertEquals(balanceSecondResult, actualSecondResult);
    }

    @Test
    void shouldTransferMoneyFromSecondNegativeAmount() {

        val dashboardPage = new DashboardPage();
        int amount = -800;
        val balanceSecondResult = dashboardPage.getSecondCardBalance() - amount;
        val balanceFirstResult = dashboardPage.getFirstCardBalance() + amount;
        val transferPage = dashboardPage.firstCardBalance();
        transferPage.actionTransfer(DataHelper.getSecondCardNumber(), amount);
        val actualFirstResult = dashboardPage.getFirstCardBalance();
        val actualSecondResult = dashboardPage.getSecondCardBalance();
        assertNotEquals(balanceFirstResult, actualFirstResult);
        assertNotEquals(balanceSecondResult, actualSecondResult);

    }

    @Test
    void shouldTransferMoneyFromSecondZeroAmount() {

        val dashboardPage = new DashboardPage();
        int amount = 0;
        val balanceSecondResult = dashboardPage.getSecondCardBalance() - amount;
        val balanceFirstResult = dashboardPage.getFirstCardBalance() + amount;
        val transferPage = dashboardPage.firstCardBalance();
        transferPage.actionTransfer(DataHelper.getSecondCardNumber(), amount);
        val actualFirstResult = dashboardPage.getFirstCardBalance();
        val actualSecondResult = dashboardPage.getSecondCardBalance();
        assertEquals(balanceFirstResult, actualFirstResult);
        assertEquals(balanceSecondResult, actualSecondResult);

    }

}
