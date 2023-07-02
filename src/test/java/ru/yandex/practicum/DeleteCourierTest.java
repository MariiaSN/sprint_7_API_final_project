package ru.yandex.practicum;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.client.courier.CourierClient;
import ru.yandex.practicum.model.courier.Courier;
import ru.yandex.practicum.model.courier.courierForAuth.CourierForAuth;
import ru.yandex.practicum.service.CourierGenerator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

//TODO допзадание, дописать

@Slf4j
public class DeleteCourierTest {
    private final CourierGenerator generator = new CourierGenerator();
    private final CourierClient courierClient = new CourierClient();
    private static final String FIELD_MESSAGE = "message";
    private static final String FIELD_OK = "ok";
    private static final String MESSAGE_NOT_FOUND = "Курьера с таким id нет.";
    private static final String RESPONSE = "Получен ответ от сервера: {}";
    private static final String DELETE = "Удаление курьера c id = {}";
    private Integer id = 0;

    @Test
    public void deleteCourier() {
        Courier courier = generator.getCourier();
        courierClient.create(courier);
        id = courierClient.login(generator.getCourierForAuth(courier)).body().path("id");
        log.info(DELETE, id);

        Response response = courierClient.deleteCourier(id);
        log.info(RESPONSE, response.body().asString());

        response.then().assertThat().body(FIELD_OK, equalTo(true))
                .and().statusCode(HttpStatus.SC_OK);
        log.info(DELETE + " произошло корректно\n", id);
    }

    @Test
    public void deleteNonExistent() {
        id = Integer.MIN_VALUE;
        log.info(DELETE, id);
        Response response = courierClient.deleteCourier(id);
        log.info(RESPONSE + "\n", response.body().asString());

        response.then().statusCode(HttpStatus.SC_NOT_FOUND)
                .and().assertThat().body(FIELD_MESSAGE, equalTo(MESSAGE_NOT_FOUND));
    }
}
