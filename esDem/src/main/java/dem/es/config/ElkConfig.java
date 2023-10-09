package dem.es.config;


import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：Lkh
 * @date ：Created in 2023/5/29 8:57 PM
 */
@Configuration
public class ElkConfig {

    /**
     *     连接路由,隔开
     */
    @Value("${stukk.elasticsearch.host}")
    private String host;

    /**
     *     用户名
     */
    @Value("${stukk.elasticsearch.username}")
    private String userName;

    /**
     *     密码
     */
    @Value("${stukk.elasticsearch.password}")
    private String password;


    /**
     * 连接超时时间
     */
    @Value("${stukk.elasticsearch.connectTimeout}")
    private int connectTimeout;

    /**
     * Socket 连接超时时间
     */
    @Value("${stukk.elasticsearch.socketTimeout}")
    private int socketTimeout;

    /**
     * 获取连接的超时时间
     */
    @Value("${stukk.elasticsearch.connectionRequestTimeout}")
    private int connectionRequestTimeout;

    /**
     * 最大连接数
     */
    @Value("${stukk.elasticsearch.maxConnectNum}")
    private int maxConnectNum;

    /**
     * 最大路由连接数
     */
    @Value("${stukk.elasticsearch.maxConnectPerRoute}")
    private int maxConnectPerRoute;


    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient() {
        //如果有多个的话，按照,分割开来
        String[] split = host.split(",");
        HttpHost[] httpHosts = new HttpHost[split.length];
        for (int i = 0; i < split.length; i++) {
//new HttpHost("x.x.x.x",9200,"http");
            httpHosts[i] = new HttpHost(split[i].split(":")[0], Integer.parseInt(split[i].split(":")[1]), "http");
        }

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        //在查询中加入用户名和密码
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));

        RestClientBuilder builder = RestClient.builder(httpHosts);
        // 异步连接延时配置
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeout);
            requestConfigBuilder.setSocketTimeout(socketTimeout);
            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeout);
            return requestConfigBuilder;
        });
        // 异步连接数配置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
        });
        return new RestHighLevelClient(builder);
    }
}