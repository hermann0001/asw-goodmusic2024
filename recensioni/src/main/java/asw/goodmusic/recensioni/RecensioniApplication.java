package asw.goodmusic.recensioni;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableDiscoveryClient 
@EnableKafka
public class RecensioniApplication {

	public static void main(String[] args) {SpringApplication.run(RecensioniApplication.class, args);}
}

@Component
class ConfigTest implements CommandLineRunner {

    @Value("${spring.kafka.bootstrap-servers:NOT_FOUND}")
    private String dbUrl;

    @Override
    public void run(String... args) {
        System.out.println("Loaded kafka URL: " + dbUrl);
    }
}
