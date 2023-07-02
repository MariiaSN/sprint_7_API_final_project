package ru.yandex.practicum.client.courier;

import io.restassured.response.Response;
import ru.yandex.practicum.client.Client;
import ru.yandex.practicum.model.courier.Courier;
import ru.yandex.practicum.model.courier.CourierWithoutLogin;
import ru.yandex.practicum.model.courier.CourierWithoutPassword;
import ru.yandex.practicum.model.courier.courierForAuth.CourierForAuth;
import ru.yandex.practicum.model.courier.courierForAuth.CourierForAuthWithoutLogin;
import ru.yandex.practicum.model.courier.courierForAuth.CourierForAuthWithoutPassword;

public class CourierClient extends Client {
    private final static String ROOT = "/courier";
    private static final String LOGIN = "/login";

    public Response create(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(ROOT);
    }

    public Response createWithoutPassword(CourierWithoutPassword courierWithoutPassword) {
        return spec()
                .body(courierWithoutPassword)
                .when()
                .post(ROOT);
    }

    public Response createWithoutLogin(CourierWithoutLogin courierWithoutLogin) {
        return spec()
                .body(courierWithoutLogin)
                .when()
                .post(ROOT);
    }

    public Response login(CourierForAuth courierForAuth) {
        return spec()
                .body(courierForAuth)
                .when()
                .post(ROOT + LOGIN);
    }

    public Response loginWithoutLogin(CourierForAuthWithoutLogin courierForAuthWithoutLogin) {
        return spec()
                .body(courierForAuthWithoutLogin)
                .when()
                .post(ROOT + LOGIN);
    }

    public Response loginWithoutPassword(CourierForAuthWithoutPassword courierForAuthWithoutPassword) {
        return spec()
                .body(courierForAuthWithoutPassword)
                .when()
                .post(ROOT + LOGIN);
    }

    public Response deleteCourier(Integer courierId) {
        return spec()
                .delete(ROOT + String.format("/%d", courierId));
    }
}
