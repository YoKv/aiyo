package net;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class MultipartHttpUtil {
    private static final Logger LOG = LogManager
            .getLogger(MultipartHttpUtil.class);

    public static JSONObject post(String postUrl,
                                  LinkedHashMap<String, String> textParams, FileItem[] fileItems,
                                  boolean useStaticFileName) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(postUrl);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        for (Entry<String, String> entry : textParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            builder.addTextBody(key, value, ContentType.TEXT_PLAIN);
        }

        for (FileItem fileItem : fileItems) {
            if (useStaticFileName) {
                builder.addBinaryBody("file", fileItem.getInputStream(), ContentType.DEFAULT_BINARY, fileItem.getName());
            } else {
                builder.addBinaryBody(fileItem.getFieldName(), fileItem.getInputStream(),
                        ContentType.MULTIPART_FORM_DATA, fileItem.getName());
            }

        }

        HttpEntity multipart = builder.build();

        uploadFile.setEntity(multipart);

        CloseableHttpResponse responseRedirect;

        JSONObject retJosn = new JSONObject();
        try {
            responseRedirect = httpClient.execute(uploadFile);

            HttpEntity responseEntity = responseRedirect.getEntity();

            String body = IOUtils
                    .toString(responseEntity.getContent(), "utf-8");

            System.out.println("body : " + body);

            retJosn = JSONObject.parseObject(body);

            return retJosn;
        } catch (ClientProtocolException e) {
            LOG.error(e);
            retJosn.put("errorcode", 100);
            retJosn.put("msg", "ClientProtocolException 网络错误！");
            return retJosn;
        } catch (IOException e) {
            LOG.error(e);
            retJosn.put("errorcode", 100);
            retJosn.put("msg", "IOException 网络错误！");
            return retJosn;
        } catch (JSONException e) {
            LOG.error(e);
            retJosn.put("errorcode", 3);
            retJosn.put("msg", "返回数据json解析错误！");
            return retJosn;
        }
    }

    public static JSONObject post(String postUrl, String fieldName,
                                  String fieldValue, FileItem fileItem) throws IOException {
        LinkedHashMap<String, String> textParams = new LinkedHashMap<String, String>();
        textParams.put(fieldName, fieldValue);

        FileItem[] fileItems = {fileItem};

        return post(postUrl, textParams, fileItems, false);
    }

    public static JSONObject post(String postUrl, String fieldName,
                                  LinkedHashMap<String, String> textParams, InputStream inputStream) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(postUrl);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        for (Entry<String, String> entry : textParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            builder.addTextBody(key, value, ContentType.TEXT_PLAIN);
        }

        builder.addBinaryBody("file", inputStream, ContentType.DEFAULT_BINARY, fieldName);

        HttpEntity multipart = builder.build();

        uploadFile.setEntity(multipart);

        CloseableHttpResponse responseRedirect;

        JSONObject retJosn = new JSONObject();
        try {
            responseRedirect = httpClient.execute(uploadFile);

            HttpEntity responseEntity = responseRedirect.getEntity();

            String body = IOUtils
                    .toString(responseEntity.getContent(), "utf-8");

            System.out.println("body : " + body);

            retJosn = JSONObject.parseObject(body);

            return retJosn;
        } catch (ClientProtocolException e) {
            LOG.error(e);
            retJosn.put("errorcode", 100);
            retJosn.put("msg", "ClientProtocolException 网络错误！");
            return retJosn;
        } catch (IOException e) {
            LOG.error(e);
            retJosn.put("errorcode", 100);
            retJosn.put("msg", "IOException 网络错误！");
            return retJosn;
        } catch (JSONException e) {
            LOG.error(e);
            retJosn.put("errorcode", 3);
            retJosn.put("msg", "返回数据json解析错误！");
            return retJosn;
        }
    }

    public static JSONObject post(String postUrl, LinkedHashMap<String, String> textParams, InputStream is, boolean useStaticFileName) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(postUrl);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        for (Entry<String, String> entry : textParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            builder.addTextBody(key, value, ContentType.TEXT_PLAIN);
        }

        builder.addBinaryBody("file", is, ContentType.DEFAULT_BINARY, "QRCode");


        HttpEntity multipart = builder.build();

        uploadFile.setEntity(multipart);

        CloseableHttpResponse responseRedirect;

        JSONObject retJosn = new JSONObject();
        try {
            responseRedirect = httpClient.execute(uploadFile);

            HttpEntity responseEntity = responseRedirect.getEntity();

            String body = IOUtils.toString(responseEntity.getContent(), "utf-8");

            retJosn = JSONObject.parseObject(body);

            return retJosn;
        } catch (ClientProtocolException e) {
            LOG.error(e);
            retJosn.put("errorcode", 100);
            retJosn.put("msg", "ClientProtocolException 网络错误！");
            return retJosn;
        } catch (IOException e) {
            LOG.error(e);
            retJosn.put("errorcode", 100);
            retJosn.put("msg", "IOException 网络错误！");
            return retJosn;
        } catch (JSONException e) {
            LOG.error(e);
            retJosn.put("errorcode", 3);
            retJosn.put("msg", "返回数据json解析错误！");
            return retJosn;
        }
    }

}