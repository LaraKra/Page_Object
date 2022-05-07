package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class TransferPage {
    private SelenideElement amount = $("[data-test-id='amount'] input");
    private SelenideElement amountFrom = $("[data-test-id='from'] input");
    private SelenideElement actionButton = $("[data-test-id='action-transfer'].button");

    public TransferPage() {
        amount.shouldBe(visible);
    }

    public DashboardPage actionTransfer(DataHelper.CardInfo cardInfo, int amountTransfer) {
        amount.setValue(String.valueOf(amountTransfer));
        amountFrom.setValue(cardInfo.getCardNumber());
        actionButton.click();
        return new DashboardPage();

    }
}
