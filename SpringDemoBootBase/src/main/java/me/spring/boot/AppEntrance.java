package me.spring.boot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author baiyu
 * <p>
 * 启动入口
 * \@ImportResource引入xml配置的文件
 */
@SpringBootApplication
public class AppEntrance {

    public static void main(String[] args) {
        //方式一
//        SpringApplication.run(AppEntrance.class, args);
        // 方式二 可以设置更多配置
        SpringApplication application = new SpringApplication(AppEntrance.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);

    }
}
