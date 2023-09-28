package com.adria.userservice;

import com.adria.userservice.dtos.AdminDTO;
import com.adria.userservice.dtos.CustomerDTO;
import com.adria.userservice.dtos.UserDTO;
import com.adria.userservice.exceptions.UserNotFoundException;
import com.adria.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner startService(UserService userService) {
        return args -> {
            // Test create and update customer
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setUsername("Mina_123");
            customerDTO.setPassword("1234");
            customerDTO.setEmail("mina@adria.com");
            customerDTO.setFirstname("Mi");
            customerDTO.setLastname("Na");

            CustomerDTO savedCustomer = userService.saveCustomer(customerDTO);
            savedCustomer.setFirstname("Mina");

            CustomerDTO updatedCustomer = userService.updateCustomer(savedCustomer);

            // Test create and update admin
            AdminDTO adminDTO = new AdminDTO();
            adminDTO.setUsername("ouss.ot");
            adminDTO.setPassword("1234");
            adminDTO.setEmail("ouss@adria.com");
            adminDTO.setFirstname("Ouss");
            adminDTO.setLastname("Tahri");

            AdminDTO savedAdmin = userService.saveAdmin(adminDTO);
            // Set updated properties in savedAdmin...
            savedAdmin.setFirstname("Oussama");

            AdminDTO updatedAdmin = userService.updateAdmin(savedAdmin);

            // Test list users
            System.out.println("List of Users:");
            for (UserDTO userDTO : userService.listUsers()) {
                System.out.println(userDTO);
            }

            // Test get user by ID
            try {
                Long userId = 1L; // Replace with an existing user ID
                UserDTO userById = userService.getUser(userId);
                System.out.println("User by ID: " + userById);
            } catch (UserNotFoundException e) {
                System.out.println("User not found.");
            }
            try {
                Long userId = 2L; // Replace with an existing user ID
                UserDTO userById2 = userService.getUser(userId);
                System.out.println("User by ID: " + userById2);
            } catch (UserNotFoundException e) {
                System.out.println("User not found.");
            }
            // Test delete user
//            Long userIdToDelete = 1L; // Replace with an existing user ID
//            userService.deletUser(userIdToDelete);
//            System.out.println("User deleted.");

            // Test list admins
            System.out.println("List of Admins:");
            for (AdminDTO admin : userService.listAdmins()) {
                System.out.println(admin);
            }

            // Test delete admin
//            Long userIdToDelete = 2L; // Replace with an existing user ID
//            userService.deletUser(userIdToDelete);
//            System.out.println("User deleted.");
        };
    }


}

