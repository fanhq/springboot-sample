package com.fanhq.example.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author fanhaiqiu
 * @date 2019/6/25
 */
public class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static PoolingHttpClientConnectionManager connMgr;
    private static SSLConnectionSocketFactory sslsf;
    private static HttpRequestRetryHandler retry;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 3000;
    private static final int MAX_TIMEOUT_CUSTOM = 5000;
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_CONTENT_TYPE = "application/json";
    private static final String REQ_LOG_FMT = "url=【{}】response http code is {}";
    private static final int RETRY_TIMES = 3;

    private HttpUtils() {
    }

    static {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            }).build();

            sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null, NoopHostnameVerifier.INSTANCE);

            Registry registry = RegistryBuilder
                    .create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", sslsf).build();

            // 设置连接池
            connMgr = new PoolingHttpClientConnectionManager(registry);
            // 设置连接池大小
            connMgr.setMaxTotal(128);
            connMgr.setDefaultMaxPerRoute(128);
        } catch (GeneralSecurityException e) {
            logger.error("创建https连接失败", e);
        }
        //请求设置
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        configBuilder.setCookieSpec(CookieSpecs.IGNORE_COOKIES);
        requestConfig = configBuilder.build();

        //重试机制配置，如果直接放回false,不重试
        retry = (exception, executionCount, context) -> {
            // 如果已经重试了3次，就放弃
            if (executionCount >= RETRY_TIMES) {
                return false;
            }
            // 如果服务器丢掉了连接，那么就重试
            if (exception instanceof NoHttpResponseException) {
                return true;
            }
            // 不要重试SSL握手异常
            if (exception instanceof SSLHandshakeException) {
                return false;
            }
            // 超时
            if (exception instanceof InterruptedIOException) {
                return true;
            }
            // 目标服务器不可达
            if (exception instanceof UnknownHostException) {
                return false;
            }
            // 连接被拒绝
            if (exception instanceof ConnectTimeoutException) {
                return false;
            }
            // ssl握手异常
            if (exception instanceof SSLException) {
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            // 如果请求是幂等的，就再次尝试
            if (!(request instanceof HttpEntityEnclosingRequest)) {
                return true;
            }
            return false;
        };
    }

    /**
     * 获取Http客户端连接对象
     *
     * @return Http客户端连接对象
     */
    public static CloseableHttpClient getHttpClient() {

        // 创建httpClient
        return HttpClients.custom()
                // 把请求相关的超时信息设置到连接客户端
                .setDefaultRequestConfig(requestConfig)
                // 把请求重试设置到连接客户端
                .setRetryHandler(retry)
                // 配置连接池管理对象
                .setConnectionManager(connMgr)
                .build();
    }

    /**
     * 获取Http客户端连接对象
     *
     * @return Http客户端连接对象
     */
    public static CloseableHttpClient getCustomHttpClient() {
        //请求设置
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT_CUSTOM);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        configBuilder.setCookieSpec(CookieSpecs.IGNORE_COOKIES);
        // 创建httpClient
        return HttpClients.custom()
                // 把请求相关的超时信息设置到连接客户端
                .setDefaultRequestConfig(configBuilder.build())
                // 配置连接池管理对象
                .setConnectionManager(connMgr)
                .build();
    }

    /**
     * get
     *
     * @param apiUrl
     * @param params
     * @return
     */
    public static String httpGet(String apiUrl, Map<String, Object> params) {
        StringBuilder param = new StringBuilder();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl = apiUrl.concat(param.toString());
        return httpGet(apiUrl);
    }

    /**
     * get
     *
     * @param apiUrl
     * @return
     */
    public static String httpGet(String apiUrl) {
        // 获取客户端连接对象
        CloseableHttpClient httpClient = getHttpClient();
        // 创建GET请求对象
        HttpGet httpGet = new HttpGet(apiUrl);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            logger.debug(REQ_LOG_FMT, apiUrl, response.getStatusLine().getStatusCode());
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            // 获取响应信息
            return EntityUtils.toString(entity, DEFAULT_CHARSET);
        } catch (ClientProtocolException e) {
            logger.error("协议错误", e);
        } catch (ParseException e) {
            logger.error("协议错误", e);
        } catch (IOException e) {
            logger.error("IO错误", e);
        } finally {
            if (null != response) {
                try {
                    EntityUtils.consume(response.getEntity());
                    response.close();
                } catch (IOException e) {
                    logger.error("释放链接错误", e);
                }
            }
        }
        return StringUtils.EMPTY;
    }


    /**
     * 发送 POST 请求（HTTP），JSON形式
     *
     * @param apiUrl
     * @param json   json对象
     * @return
     */
    public static String httpPost(String apiUrl, String json) {
        CloseableHttpClient httpClient = getHttpClient();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        try {
            //解决中文乱码问题
            StringEntity stringEntity = new StringEntity(json, DEFAULT_CHARSET);
            stringEntity.setContentType(DEFAULT_CONTENT_TYPE);
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            logger.debug(REQ_LOG_FMT, apiUrl, statusCode);
            response = temporarilyMoved302(response, stringEntity, httpClient, DEFAULT_CHARSET, DEFAULT_CONTENT_TYPE);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, DEFAULT_CHARSET);
        } catch (IOException e) {
            logger.error("post request failed， url=" + apiUrl, e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    logger.error("EntityUtils.consume(response.getEntity()) failed!", e);
                }
            }
        }
        return httpStr;
    }

    /**
     * 302 跳转
     *
     * @param response
     * @param stringEntity
     * @param httpClient
     * @param charset
     * @param contentType
     * @throws IOException
     */
    private static CloseableHttpResponse temporarilyMoved302(CloseableHttpResponse response, StringEntity stringEntity,
                                                             CloseableHttpClient httpClient, String charset, String contentType) throws IOException {
        List<Integer> allowStatusCodes = new ArrayList<>();
        allowStatusCodes.add(HttpStatus.SC_OK);
        allowStatusCodes.add(HttpStatus.SC_MOVED_TEMPORARILY);
        int statusCode = response.getStatusLine().getStatusCode();
        if (!allowStatusCodes.contains(statusCode)) {
            logger.warn("HTTP STATUS CODE 不在允许返回的列表中，返回的HTTP代码为【{}】,允许的HTTP代码列表为【{}】", statusCode, allowStatusCodes);
            return response;
        }
        if (statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
            // 跳转的目标地址是在 HTTP-HEAD 中的
            Header header = response.getFirstHeader("location");
            String relocationUrl = header.getValue();
            logger.debug("原请求地址被对方服务器进行了302重定向，重定向后的地址为【{}】", relocationUrl);
            if (relocationUrl.startsWith("https://")) {
                httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
            }
            HttpPost httpPost = new HttpPost(relocationUrl);
            httpPost.setHeader("Content-Type", contentType + ";" + charset);
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            statusCode = response.getStatusLine().getStatusCode();
            logger.debug("重定向后返回的的HTTP代码为{}", statusCode);
        }
        return response;
    }

}
