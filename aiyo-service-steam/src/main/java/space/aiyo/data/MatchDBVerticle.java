package space.aiyo.data;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.component.MongoDBCRUDVerticle;
import space.aiyo.var.Route;

/**
 * CREATE BY Yo ON 2018/1/13 20:45
 */
public class MatchDBVerticle extends MongoDBCRUDVerticle {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void start(){
        documentName = "dotaMatch";
        //提取出方法便于AOP实现,但异步没有返回值，不适合做
        insertManager();
        findManager();
        findWithOptionsManager();
    }

    //添加match
    private void insertManager() {
// todo insertbatch
        vertx.eventBus().consumer(Route.DB_MATCH_INSERT.getAddress()).handler(message -> {
                    JsonArray array = (JsonArray) message.body();
                    array.forEach(jsonObject -> {
                        JsonObject json = (JsonObject) jsonObject;
                        insert(json, result -> {
                            if (result.succeeded()) {
                                logger.info("insert match to db succeeded: {}", result.result());
                            } else {
                                logger.error("insert match to db failed", result.cause());
                            }
                        });
                    });
                }
        );
    }

    //查询match
    private void findManager() {
        vertx.eventBus().consumer(Route.DB_MATCH_FIND.getAddress()).handler(message -> {
                    JsonObject query = (JsonObject) message.body();
                    find(query, result -> {
                        if (result.succeeded()) {
                            message.reply(result.result());
                        } else {
                            logger.error("find match from db failed", result.cause());
                        }
                    });
                }
        );
    }

    //查询match
    private void findWithOptionsManager() {
        vertx.eventBus().consumer(Route.DB_MATCH_FINDWITHOPTIONS.getAddress()).handler(message -> {
                    JsonObject object = (JsonObject) message.body();
                    findWithOptions(object.getJsonObject("query"), (FindOptions) object.getValue("options"), result -> {
                        if (result.succeeded()) {
                            message.reply(result.result());
                        } else {
                            logger.error("find match from db failed", result.cause());
                        }
                    });
                }
        );
    }
}