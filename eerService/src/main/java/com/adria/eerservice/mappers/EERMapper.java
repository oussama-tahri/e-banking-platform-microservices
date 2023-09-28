package com.adria.eerservice.mappers;

import com.adria.eerservice.dtos.EERDto;
import com.adria.eerservice.entities.EER;
import com.adria.eerservice.feign.UserFeignClient;
import com.adria.eerservice.models.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EERMapper {

    private UserFeignClient userFeignClient;

    public EERMapper(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    public EERDto entityToDto(EER eer) {
        EERDto eerDto = new EERDto();
        try {
            BeanUtils.copyProperties(eer, eerDto);
            if (eer.getUserId() != null) {
                User user = userFeignClient.getUserById(eer.getUserId());

                // Populate user details if user information is retrieved
                if (user != null) {
                    User userWithoutNulls = new User();
                    userWithoutNulls.setId(user.getId());
                    userWithoutNulls.setUsername(user.getUsername());
                    userWithoutNulls.setFirstname(user.getFirstname());
                    userWithoutNulls.setLastname(user.getLastname());
                    userWithoutNulls.setEmail(user.getEmail());
                    userWithoutNulls.setPassword(user.getPassword());
                    eerDto.setUser(userWithoutNulls);
                }
            }
        } catch (Exception e) {
            System.out.println("you can't map from EER entity to EERDto");
        }
        return eerDto;
    }

    public EER dtoToEntity(EERDto eerDto) {
        EER eer = new EER();
        try {
            BeanUtils.copyProperties(eerDto, eer);
        } catch (Exception e) {
            System.out.println("you can't map from EERDto to EER entity");
        }
        return eer;
    }

    public void updateEERFromDTO(EERDto eerDto, EER eer) {
        BeanUtils.copyProperties(eerDto, eer, "id");
        eer.setUserId(eerDto.getUserId()); // Update userId
    }
}

