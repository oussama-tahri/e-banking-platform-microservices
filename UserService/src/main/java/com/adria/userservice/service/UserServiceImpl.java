package com.adria.userservice.service;


import com.adria.userservice.dtos.AdminDTO;
import com.adria.userservice.dtos.CustomerDTO;
import com.adria.userservice.dtos.UserDTO;
import com.adria.userservice.entities.Admin;
import com.adria.userservice.entities.Customer;
import com.adria.userservice.entities.User;
import com.adria.userservice.exceptions.UserNotFoundException;
import com.adria.userservice.mapper.UserMapper;
import com.adria.userservice.repository.AdminRepository;
import com.adria.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private AdminRepository adminRepository;
    private UserMapper userMapper;

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        log.info("update  customer");
        Customer customer = userMapper.fromCustomerDto(customerDTO);
        Customer saveCustomer = userRepository.save(customer);
        return userMapper.fromCustomer(saveCustomer);
    }
    @Override
    public void deletUser(Long userId){
        userRepository.deleteById(userId);
    }

    @Override
    public UserDTO getUser(Long userId) throws  UserNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));
        if(user instanceof Admin){
            Admin admin= (Admin) user;
            return userMapper.fromAdmin(admin);
        }else{
            Customer customer= (Customer) user;
            return userMapper.fromCustomer(customer);
        }

    }



    @Override
    public List<UserDTO> listUsers() {

        List<User> users = userRepository.findAll();

        List<UserDTO> userDTOS=users.stream().map(user->{
            if (user instanceof Admin){
                Admin admin= (Admin) user;
                return userMapper.fromAdmin(admin);
            }
            else{
                Customer customer= (Customer) user;
                return userMapper.fromCustomer(customer);
            }
        }).collect(Collectors.toList());


        return userDTOS;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("saving new customer");
        Customer customer = userMapper.fromCustomerDto(customerDTO);
       Customer saveCustomer =  userRepository.save(customer);
        return userMapper.fromCustomer(saveCustomer);
    }

    @Override
    public AdminDTO saveAdmin(AdminDTO adminDTO) {
        log.info("saving new Admin");
        Admin admin = userMapper.fromAdminDTO(adminDTO);
        Admin saveAdmin = userRepository.save(admin);
        return userMapper.fromAdmin(saveAdmin);
    }

    @Override
    public AdminDTO getAdmin(Long adminId) throws UserNotFoundException {
        Admin admin = (Admin) userRepository.findByIdAndDiscriminator(adminId, "ADMIN")
                .orElseThrow(() -> new UserNotFoundException("Admin not found with ID: " + adminId));
        return userMapper.fromAdmin(admin);
    }
    @Override
    public AdminDTO updateAdmin(AdminDTO adminDTO) {
        log.info("update admin");
        Admin admin = userMapper.fromAdminDTO(adminDTO);
        Admin savedAdmin = userRepository.save(admin);
        return userMapper.fromAdmin(savedAdmin);
    }

    @Override
    public void deleteAdmin(Long adminId) {
        userRepository.deleteById(adminId);
    }

    @Override
    public List<AdminDTO> listAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream()
                .map(userMapper::fromAdmin)
                .collect(Collectors.toList());
    }
}
