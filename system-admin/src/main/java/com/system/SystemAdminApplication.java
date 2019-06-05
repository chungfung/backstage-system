package com.system;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.system.service.mapper")
@EncryptablePropertySource(name = "spring.datasource.password", value = "classpath:application-druid.yml")
public class SystemAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemAdminApplication.class, args);
	}
}
