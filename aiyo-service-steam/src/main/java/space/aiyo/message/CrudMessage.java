package space.aiyo.message;


import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import space.aiyo.util.AbstractCodec;

public class CrudMessage extends AbstractCodec<CrudMessage, CrudMessage> {

  private String documentName;
  private JsonObject jsonData;
  private JsonArray arrayData;
  private JsonObject query;
  private JsonObject update;
  private FindOptions findOptions;

  @Override
  public CrudMessage transform(CrudMessage crudMessage) {
    CrudMessage message = new CrudMessage();
    message.setDocumentName(crudMessage.getDocumentName());
    message.setArrayData(crudMessage.getArrayData());
    message.setFindOptions(crudMessage.getFindOptions());
    message.setJsonData(crudMessage.getJsonData());
    message.setQuery(crudMessage.getQuery());
    message.setUpdate(crudMessage.getUpdate());
    return message;
  }

  @Override
  protected Class<CrudMessage> getInstanceClass() {
    return CrudMessage.class;
  }

  @Override
  public String toString() {
    return "{\"CrudMessage\":{"
        + "\"documentName\":\"" + documentName + "\""
        + ",\"jsonData\":" + jsonData
        + ",\"arrayData\":" + arrayData
        + ",\"query\":" + query
        + ",\"update\":" + update
        + ",\"findOptions\":" + findOptions
        + "}}";
  }

  public String getDocumentName() {
    return documentName;
  }

  public void setDocumentName(String documentName) {
    this.documentName = documentName;
  }

  public JsonObject getJsonData() {
    return jsonData;
  }

  public void setJsonData(JsonObject jsonData) {
    this.jsonData = jsonData;
  }

  public JsonArray getArrayData() {
    return arrayData;
  }

  public void setArrayData(JsonArray arrayData) {
    this.arrayData = arrayData;
  }

  public JsonObject getQuery() {
    return query;
  }

  public void setQuery(JsonObject query) {
    this.query = query;
  }

  public JsonObject getUpdate() {
    return update;
  }

  public void setUpdate(JsonObject update) {
    this.update = update;
  }

  public FindOptions getFindOptions() {
    return findOptions;
  }

  public void setFindOptions(FindOptions findOptions) {
    this.findOptions = findOptions;
  }
}