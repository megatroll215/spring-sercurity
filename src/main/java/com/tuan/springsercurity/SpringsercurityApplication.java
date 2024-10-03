package com.tuan.springsercurity;

import com.tuan.springsercurity.exception.InternalException;
import com.tuan.springsercurity.model.User;
import com.tuan.springsercurity.repository.UserRepository;
import com.tuan.springsercurity.util.constant.CommonConstant;
import com.tuan.springsercurity.util.constant.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class SpringsercurityApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringsercurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User check = userRepository.findFirstByRole(Role.ADMIN);
		if(check == null)
		{
			User user = User.builder()
					.email("admin@admin.com")
					.firstname("admin")
					.secondname("mr")
					.password(new BCryptPasswordEncoder().encode("admin"))
					.role(Role.ADMIN)
					.build();
			try
			{
			userRepository.save(user);
			}
			catch (Exception exception)
			{
				throw new InternalException(CommonConstant.ERROR);
			}
		}
	}
}
