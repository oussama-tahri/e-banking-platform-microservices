package com.adria.userservice.web;


import com.adria.userservice.dtos.AdminDTO;
import com.adria.userservice.dtos.CustomerDTO;
import com.adria.userservice.dtos.UserDTO;
import com.adria.userservice.exceptions.UserNotFoundException;
import com.adria.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
@Slf4j
public class CustomerController {
    private UserService userService;
    @GetMapping("/all")
    //@PreAuthorize("hasAuthority('USER')")
    public List<UserDTO> users(){
        return userService.listUsers();
    }
    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('USER')")
    public UserDTO getUser(@PathVariable(name = "id") Long userId) throws UserNotFoundException {
        return userService.getUser(userId);
    }

    @PostMapping("/customer")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return userService.saveCustomer(customerDTO);

    }
    @PostMapping("/admin")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public AdminDTO saveAdmin(@RequestBody AdminDTO adminDTO){
        return userService.saveAdmin(adminDTO);

    }

    @PutMapping("/update/customer/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id,@RequestBody CustomerDTO customerDTO){
        customerDTO.setId(id);
        return userService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/delete/customer/{id}")
    public void deleteCustomer(@PathVariable(name = "id") Long userId){
        userService.deletUser(userId);
    }


    @PutMapping("/update/admin/{adminId}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable Long adminId, @RequestBody AdminDTO adminDTO) {
        AdminDTO updatedAdmin = userService.updateAdmin(adminDTO);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

    @DeleteMapping("/delete/admin/{adminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long adminId) {
        userService.deleteAdmin(adminId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long adminId) throws UserNotFoundException {
        AdminDTO admin = userService.getAdmin(adminId);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping("/admin/list")
    public ResponseEntity<List<AdminDTO>> listAdmins() {
        List<AdminDTO> admins = userService.listAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
}
