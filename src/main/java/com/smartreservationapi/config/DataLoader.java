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
                userRepository.save(new User(null, "Alice Johnson", "alice@example.com"));
                userRepository.save(new User(null, "David Kim", "david@example.com"));
            }

            if (roomRepository.count() == 0) {
                roomRepository.save(new Room(null, "Ocean Room", 6));
                roomRepository.save(new Room(null, "Sky Room", 12));
            }
        };
    }
}
