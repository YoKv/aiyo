package space.aiyo.util;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;

public class AiyoHttpClient extends AbstractVerticle {
    @Override
    public void start() throws Exception {

        WebClient client = WebClient.create(vertx);

        client.get(8080, "localhost", "/")
                .as(BodyCodec.jsonObject())
                .send(ar -> {
                    if (ar.succeeded()) {
                        HttpResponse<JsonObject> response = ar.result();
                        System.out.println("Got HTTP response body");
                        System.out.println(response.body().encodePrettily());
                    } else {
                        ar.cause().printStackTrace();
                    }
                });
    }
}
