package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class TestCard {
    @BeforeEach
    public void openForm() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldValid() {
        $("[data-test-id=name] input").setValue("Ливанов Василий");
        $("[data-test-id=phone] input").setValue("+79111234567");
        $("[data-test-id=agreement]").click();//Чекбокс
        $("[type=button]").click();           //Кнопка
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldInvalidName1() {
        $("[data-test-id=name] input").setValue("Livanov Василий");
        $("[data-test-id=phone] input").setValue("+79111234567");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
        //issues тест не проходит, хотя должен. Причина буква Ё
    void shouldInvalidName2() {
        $("[data-test-id=name] input").setValue("Куравлёв Леонид");
        $("[data-test-id=phone] input").setValue("+79111234567");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        //$("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldInvalidName3() {
        $("[data-test-id=name] input").setValue("Куравлев Леонид");
        $("[data-test-id=phone] input").setValue("+79021234567");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldInvalidName4() {
        $("[data-test-id=name] input").setValue("Акунья Перреро Себастиан");
        $("[data-test-id=phone] input").setValue("+79111234567");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
        //issues Тест проходит, хотя не должен, т.к. не указана фамилия
    void shouldInvalidName5() {
        $("[data-test-id=name] input").setValue("Негоро");
        $("[data-test-id=phone] input").setValue("+79111234567");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        //$("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldWhenInvalidTel10() {
        $("[data-test-id=name] input").setValue("Гафт Валентин");
        $("[data-test-id=phone] input").setValue("+7911123456");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldWhenInvalidTel12() {
        $("[data-test-id=name] input").setValue("Гафт Валентин");
        $("[data-test-id=phone] input").setValue("+791112345678");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldWhenInvalidTelLetter1() {
        $("[data-test-id=name] input").setValue("Гафт Валентин");
        $("[data-test-id=phone] input").setValue("+7911123m567");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldWhenInvalidTelLetter2() {
        $("[data-test-id=name] input").setValue("Гафт Валентин");
        $("[data-test-id=phone] input").setValue("-79111234567");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldWhenInvalidTelLetter3() {
        $("[data-test-id=name] input").setValue("Гафт Валентин");
        $("[data-test-id=phone] input").setValue("79+111234567");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldWhenInvalidTelLetter4() {
        $("[data-test-id=name] input").setValue("Гафт Валентин");
        $("[data-test-id=phone] input").setValue("89111234567");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldInvalidField2() {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldInvalidField11() {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79111234567");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldInvalidField12() {
        $("[data-test-id=name] input").setValue("Гафт Валентин");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNoAgree() {
        $("[data-test-id=name] input").setValue("Азбука Морзе");
        $("[data-test-id=phone] input").setValue("+79021234567");
        $("[type=button]").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}