package dem.es.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author ：Lkh
 * @date ：Created in 2023/5/24 4:45 PM
 */
@Configuration
public class ElasticsearchConfig {
/*
    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient(){

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }*/
}
