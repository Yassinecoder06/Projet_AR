package com.smartreservationapi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.smartreservationapi.entity.Room;
import com.smartreservationapi.entity.User;
import com.smartreservationapi.repository.RoomRepository;
import com.smartreservationapi.repository.UserRepository;

@Configuration
public class DataLoader {
    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, RoomRepository roomRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                userRepository.save(User.builder().name("Alice Johnson").email("alice@example.com").build());
                userRepository.save(User.builder().name("David Kim").email("david@example.com").build());
            }

            if (roomRepository.count() == 0) {
                roomRepository.save(Room.builder().name("Ocean Room").capacity(6).build());
                roomRepository.save(Room.builder().name("Sky Room").capacity(12).build());
            }
        };
    }
}
