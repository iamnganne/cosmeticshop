package hcmute.fit.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hcmute.fit.demo.config.DBConfig;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		DBConfig.ConnectDatabase();

        SpringApplication.run(DemoApplication.class, args);
//		SpringApplication.run(DemoApplication.class, args);
	}

}
