package tourism.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import tourism.constant.HttpReqConstant;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

public class HttpUtils {

    private static String BSID = StrUtil.EMPTY;
    private static String USER_AGENT = StrUtil.EMPTY;
    private static String account = null;
    private static String password = null;

    private static final Random RADOM = new Random();


    public static String sendGet(String url, Map<String, Object> params, Map<String, String> headers) throws RuntimeException {
        HttpClient httpClient = HttpClients.createDefault();
        if (params != null && !params.isEmpty()) {
            url += "?" + StrUtil.join("&", params.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .toArray(String[]::new));
        }

        HttpGet request = new HttpGet(url);

        if (CollUtil.isNotEmpty(headers)) {
            headers.forEach(request::addHeader);
        }

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.setHeader(HttpHeaders.USER_AGENT, USER_AGENT);
        request.addHeader(getCookie());

        try {
            HttpResponse response = httpClient.execute(request);
            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String sendPost(String url, Map<String, Object> params, String body, Map<String, String> headers) throws RuntimeException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(url);

        if (params != null && !params.isEmpty()) {
            String paramString = StrUtil.join("&", params.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .toArray(String[]::new));
            try {
                request.setEntity(new StringEntity(paramString));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (StrUtil.isNotBlank(body)) {
            try {
                request.setEntity(new StringEntity(body));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (headers != null && !headers.isEmpty()) {
            headers.forEach(request::addHeader);
        }

        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        request.addHeader(getCookie());
        request.setHeader(HttpHeaders.USER_AGENT, USER_AGENT);
//        request.addHeader(new BasicHeader("Cookie", "BSID=" + "2o4EE2PutlpUla3Nqsga_mY_IkneBQCL3UWctsxbCRHsOoZYne0hctClueqLrjFjKuZ_wn3Kk5oyAOcVs0nyUQ" + ";"));
//        request.setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36 Core/1.94.197.400 QQBrowser/11.6.5265.400");

        // 执行HTTP请求
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 检查响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            // 从响应实体中获取JSON字符串
            String jsonResponse = null;
            try {
                jsonResponse = EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // 输出JSON字符串
            System.out.println("返回的JSON数据：");
            System.out.println(jsonResponse);

            // 是否登录
            if (jsonResponse.startsWith("{\"code\":5,")) {
                throw new RuntimeException(jsonResponse);
            }

            return jsonResponse;

        } else {
            // 处理HTTP请求失败的情况
            System.err.println("HTTP请求失败: " + response.getStatusLine());
        }
        throw new RuntimeException("请求错误:" + response.getStatusLine().getReasonPhrase());
    }

    private static BasicHeader getCookie() {
//        String[] cookies = BSID.split("; ");
//        for (String cookie : cookies) {
//            String[] parts = cookie.split("=", 2);
//            BasicClientCookie basicCookie = new BasicClientCookie(parts[0], parts[1]);
//            cookieStore.addCookie(basicCookie);
//        }
        return new BasicHeader("Cookie", BSID);
    }

    public static String sendGet(String url) throws Exception {
        return sendGet(url, null, null);
    }

    public static String sendPost(String url) throws Exception {
        return sendPost(url, null, null, null);
    }

    public static String getUrl(String url) {
        if (!url.startsWith("http")) {
            url =  HttpReqConstant.PROTOCOL + HttpReqConstant.DOMAIN + (url.startsWith("/") ? "" : "/") + url;
        }
        return url;
    }

    public static boolean login(String account, String password) {
//        String loginUrl = getUrl();
        return false;
    }

    public static void initBSID(String bsid) {
        BSID = bsid;
    }

    public static void initUserAgent(String userAgent) {
        USER_AGENT = userAgent;
    }

    public static void initUserInfo(String a, String p) {
        account = a;
        password = p;
    }
}
