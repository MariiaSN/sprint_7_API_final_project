package ru.yandex.practicum.client.order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.practicum.client.Client;
import ru.yandex.practicum.model.order.Order;

public class OrderClient extends Client {
    private final static String ROOT = "/orders";
    private final static String CANCEL = "/cancel";

    @Step("Создание заказа")
    public Response createOrder(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ROOT);
    }

    @Step("Завершение заказа")
    public Response cancelOrder(Integer id) {
        return spec()
                .queryParam("track", id)
                .put(ROOT + CANCEL);
    }

    @Step("Получение списка заказов")
    public Response getOrders() {
        return spec()
                .get(ROOT);
    }
}
