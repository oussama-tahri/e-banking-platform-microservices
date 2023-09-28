package com.adria.userservice.service;


import com.adria.userservice.dtos.AdminDTO;
import com.adria.userservice.dtos.CustomerDTO;
import com.adria.userservice.dtos.UserDTO;
import com.adria.userservice.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService
{


    CustomerDTO updateCustomer(CustomerDTO customerDTO);


    void deletUser(Long userId);


    UserDTO getUser(Long userId) throws  UserNotFoundException;



    List<UserDTO> listUsers();

    public CustomerDTO saveCustomer(CustomerDTO customerDTO);

    AdminDTO saveAdmin(AdminDTO adminDTO);

    AdminDTO getAdmin(Long adminId) throws UserNotFoundException;

    AdminDTO updateAdmin(AdminDTO adminDTO);

    void deleteAdmin(Long adminId);

    List<AdminDTO> listAdmins();
}
