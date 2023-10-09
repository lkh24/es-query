package dem.es.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：Lkh
 * @date ：Created in 2023/9/28 9:15 AM
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 是否允许发送 Cookie
        registry.addMapping("/**")
                // 允许访问的前端地址
                .allowedOrigins("*")
                // 允许的请求方法，例如GET、POST
                .allowedMethods("*")
                // 允许的请求头
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}

