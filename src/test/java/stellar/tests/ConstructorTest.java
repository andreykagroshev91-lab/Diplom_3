package stellar.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    public ConstructorTest(String browserType) {
        super(browserType);
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    public void switchToBunsSectionTest() {
        mainPage.open();
        assertTrue("Главная страница не загрузилась", mainPage.isPageLoaded());

        // Уходим на соусы
        mainPage.goToSaucesSection();
        assertTrue("Должны были перейти на Соусы", mainPage.isSaucesSectionActive());

        // Возвращаемся на булки
        mainPage.goToBunsSection();

        assertTrue("Вкладка «Булки» не активна", mainPage.isBunsSectionActive());
        assertTrue("Заголовок раздела 'Булки' не виден", mainPage.isSectionHeaderVisible("Булки"));
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    public void switchToSaucesSectionTest() {
        mainPage.open();
        assertTrue("Главная страница не загрузилась", mainPage.isPageLoaded());

        mainPage.goToSaucesSection();

        assertTrue("Вкладка «Соусы» не активна", mainPage.isSaucesSectionActive());
        assertTrue("Заголовок раздела 'Соусы' не виден", mainPage.isSectionHeaderVisible("Соусы"));
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    public void switchToFillingsSectionTest() {
        mainPage.open();
        assertTrue("Главная страница не загрузилась", mainPage.isPageLoaded());

        mainPage.goToFillingsSection();

        assertTrue("Вкладка «Начинки» не активна", mainPage.isFillingsSectionActive());
        assertTrue("Заголовок раздела 'Начинки' не виден", mainPage.isSectionHeaderVisible("Начинки"));
    }
}