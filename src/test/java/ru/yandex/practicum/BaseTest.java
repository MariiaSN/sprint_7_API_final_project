package ru.yandex.practicum;

import org.junit.After;
import ru.yandex.practicum.model.courier.Courier;

public class BaseTest {
    protected Courier courier;
    private final UtillMetods util = new UtillMetods();

    @After
    public void deleteCourier() {
        if (courier != null) {
            util.deleteCourier(courier);
        }
    }
}
