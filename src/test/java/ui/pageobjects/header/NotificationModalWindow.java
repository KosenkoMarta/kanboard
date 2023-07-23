package ui.pageobjects.header;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NotificationModalWindow extends HeaderSection{
    private final SelenideElement notificationModal = $("#modal-box");

    public SelenideElement getNotificationModal() {
        return notificationModal;
    }
}
