package top.pcat.study.config;

import io.minio.MinioClient;
import io.rong.RongCloud;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RongCloudConfig {


    @Value("${rong.appKey}")
    private static final String appKey = "pvxdm17jpdhyr";

    @Value("${rong.appSecret}")
    private static final String appSecret = "hblyRTXJjrkUEw";

    @Value("${rong.api}")
    private static final String api = "http://api-cn.ronghub.com";

    @Bean
    public RongCloud rongCloud()  {

        return RongCloud.getInstance(appKey, appSecret);

    }

}
