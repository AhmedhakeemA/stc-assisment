package com.stc.system;

import com.stc.system.models.ItemType;
import com.stc.system.repo.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SystemApplication  {
	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}
}