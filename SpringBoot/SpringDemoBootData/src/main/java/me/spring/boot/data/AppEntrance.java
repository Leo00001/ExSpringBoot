package me.spring.boot.data;


import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppEntrance {


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AppEntrance.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
