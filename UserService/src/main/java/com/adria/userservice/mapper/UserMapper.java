package com.adria.userservice.mapper;


import com.adria.userservice.dtos.AdminDTO;
import com.adria.userservice.dtos.CustomerDTO;
import com.adria.userservice.entities.Admin;
import com.adria.userservice.entities.Customer;

import com.adria.userservice.models.Account;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserMapper {

    public CustomerDTO fromCustomer(Customer customer){

        CustomerDTO customerDTO=new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        customerDTO.setType(customer.getClass().getSimpleName());

        return  customerDTO;
    }

    public Customer fromCustomerDto(CustomerDTO customerDTO){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerDTO,customer);

        return  customer;
    }
    public AdminDTO fromAdmin (Admin admin){
        AdminDTO adminDTO=new AdminDTO();
        BeanUtils.copyProperties(admin,adminDTO);
        adminDTO.setType(admin.getClass().getSimpleName());
        return adminDTO;
    }
    public Admin fromAdminDTO (AdminDTO adminDTO){
        Admin admin=new Admin();
        BeanUtils.copyProperties(adminDTO,admin);
        return admin;
    }
}
