package ru.netology.delivery.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting()  {
        var validUser = DataGenerator.Registration.generateUser();
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

//         TODO: добавить логику теста в рамках которого будет выполнено планирование и перепланирование встречи.
//         Для заполнения полей формы можно использовать пользователя validUser и строки с датами в переменных
//         firstMeetingDate и secondMeetingDate. Можно также вызывать методы generateCity(locale),
//         generateName(locale), generatePhone(locale) для генерации и получения в тесте соответственно города,
//         имени и номера телефона без создания пользователя в методе generateUser(String locale) в датагенераторе

        $("[data-test-id=\"city\"] input.input__control").setValue(validUser.getCity());
        $("[placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        $("[placeholder=\"Дата встречи\"]").sendKeys(Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").setValue(firstMeetingDate);
        $("[data-test-id=\"name\"] input.input__control").setValue(validUser.getName());
        $("[data-test-id=\"phone\"] input.input__control").setValue(validUser.getPhone());
        $("[data-test-id=\"agreement\"] span.checkbox__box").click();
        $("[type=\"button\"].button").click();

        $("[data-test-id=\"success-notification\"]").shouldBe(visible)
                .shouldBe(text("Успешно!\n" + "Встреча успешно запланирована на " + firstMeetingDate));

        $("[placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        $("[placeholder=\"Дата встречи\"]").sendKeys(Keys.BACK_SPACE);
        $("[placeholder=\"Дата встречи\"]").setValue(secondMeetingDate);
        $("[type=\"button\"].button").click();

        $("[data-test-id=\"replan-notification\"]").shouldBe(visible)
                .shouldBe(text("Необходимо подтверждение"));

        $(".button_size_s").click();

        $("[data-test-id=\"success-notification\"]").shouldBe(visible).shouldBe(text("Успешно!"));
        $("[data-test-id=\"success-notification\"]").shouldBe(visible)
                .shouldBe(text("Встреча успешно запланирована на " + secondMeetingDate));
    }
}

