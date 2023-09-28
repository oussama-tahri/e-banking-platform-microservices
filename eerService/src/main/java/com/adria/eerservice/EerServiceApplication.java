package com.adria.eerservice;

import com.adria.eerservice.dtos.EERDto;
import com.adria.eerservice.dtos.PieceJointeDTO;
import com.adria.eerservice.exceptions.EERNotFoundException;
import com.adria.eerservice.exceptions.EERServiceException;
import com.adria.eerservice.feign.UserFeignClient;
import com.adria.eerservice.models.User;
import com.adria.eerservice.services.IEERService;
import com.adria.eerservice.services.IPieceJointeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class EerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(IEERService eerService,
                                        UserFeignClient userFeignClient,
                                        IPieceJointeService pieceJointeService) {
        return args -> {
            // Test cases for EER services

            // Create EER
            EERDto eerDto = new EERDto();
            eerDto.setFirstName("Oussama");
            eerDto.setLastName("Tahri");

            eerDto.setUserId(2L); // Set the userId here

            EERDto createdEER = eerService.createEER(eerDto);
            System.out.println("Created EER: " + createdEER);

            // Create another EER
            EERDto eerDto2 = new EERDto();
            eerDto.setFirstName("Mi");
            eerDto.setLastName("Na");

            eerDto.setUserId(1L); // Set the userId here

            EERDto createdEER2 = eerService.createEER(eerDto);
            System.out.println("Created EER: " + createdEER2);

            EERDto retrievedEER = eerService.getEERById(createdEER.getId());
            Long userId = retrievedEER.getUserId();

            if (userId != null) {
                User user = userFeignClient.getUserById(userId);
                System.out.println("Retrieved EER by ID: " + retrievedEER);
                System.out.println("Associated User: " + user);
            }

            // Get all EERs
            List<EERDto> allEERs = eerService.getAllEERs();
            for (EERDto eerDto1 : allEERs) {
                if (eerDto1.getUserId() != null) {
                    User user = userFeignClient.getUserById(eerDto1.getUserId());
                    eerDto1.setUser(user);
                }
            }

            // Update EER
            eerDto.setFirstName("AdriaOussama");
            EERDto updatedEER = eerService.updateEER(createdEER.getId(), retrievedEER);
            System.out.println("Updated EER" +updatedEER);

            // Créez un objet PieceJointeDTO pour tester le service
            PieceJointeDTO pieceJointeDTO = new PieceJointeDTO();
            pieceJointeDTO.setType("Carte Nationale");
            pieceJointeDTO.setNom("CIN");
            pieceJointeDTO.setContenu("Contenu de la CIN".getBytes());

            // Appelez le service pour enregistrer la pièce jointe
            PieceJointeDTO savedPieceJointeDTO = pieceJointeService.enregistrerPieceJointe(pieceJointeDTO);

            // Affichez la pièce jointe enregistrée
            System.out.println("Pièce jointe enregistrée : " + savedPieceJointeDTO);

            //            try {
//                eerService.updateEER(eerId, eerDto);
//                System.out.println("Updated EER with ID " + eerId);
//            } catch (EERNotFoundException e) {
//                System.err.println("EER not found with ID: " + eerId);
//            } catch (EERServiceException e) {
//                System.err.println("Failed to update EER: " + e.getMessage());
//            }


            // Delete EER
//            try {
//                eerService.deleteEER(eerId);
//                System.out.println("Deleted EER with ID " + eerId);
//            } catch (EERNotFoundException e) {
//                System.err.println("EER not found with ID: " + eerId);
//            } catch (EERServiceException e) {
//                System.err.println("Failed to delete EER: " + e.getMessage());
//            }

            // Get EERs by User ID
//            Long userId2 = 2L; // Replace with an actual user ID
//            List<EERDto> eerListByUser = eerService.getByUser(userId);
//            System.out.println("EERs by User ID " + userId + ": " + eerListByUser);

        };
    }

}
