package sg.edu.nus.iss.day_25_workshop_subscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import sg.edu.nus.iss.day_25_workshop_subscriber.component.MessagePoller;

@SpringBootApplication
@EnableAsync
public class Day25WorkshopSubscriberApplication implements CommandLineRunner{

	@Autowired
	private MessagePoller poller;

	public static void main(String[] args) {
		SpringApplication.run(Day25WorkshopSubscriberApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		poller.start();
	}

}
