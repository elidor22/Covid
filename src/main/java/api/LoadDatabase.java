package api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

	@Bean
    CommandLineRunner initDatabase() {
		return initDatabase();
	}

	@Bean
    CommandLineRunner initDatabase(UserRepository repository) {
		return args -> {
			//log.info("Preloading " + repository.save(new User("Bilbo Baggins", "burglar")));
			//log.info("Preloading " + repository.save(new User("Frodo Baggins", "thief")));
			log.info("Preloading"+repository.save(new Result("Elidor", "THe dude that is developing this stuff","fdfsf","sdfd","sfgdsf")));
		};
	}
}
