package space.aiyo.util;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

public class WebClientVerticle extends AbstractVerticle{
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        super.start(startFuture);

        WebClient client = WebClient.create(vertx);

        client.get(8080, "localhost", "/").send(ar -> {
            if (ar.succeeded()) {
                HttpResponse<Buffer> response = ar.result();
                System.out.println("Got HTTP response with status " + response.statusCode() + " with data " +
                        response.body().toString("ISO-8859-1"));
            } else {
                ar.cause().printStackTrace();
            }
        });


    }

}
