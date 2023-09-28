package com.adria.eerservice.web;

import com.adria.eerservice.dtos.PieceJointeDTO;
import com.adria.eerservice.services.IPieceJointeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("piece-jointe")
@AllArgsConstructor
public class PieceJointeController {
    private IPieceJointeService pieceJointeService;

    @PostMapping("/enregistrer")
    public PieceJointeDTO enregistrerPieceJointe(@RequestBody PieceJointeDTO pieceJointeDTO) {
        return pieceJointeService.enregistrerPieceJointe(pieceJointeDTO);
    }

    @PostMapping("/televerser")
    public ResponseEntity<String> televerserPieceJointe(
            @RequestParam("type") String type,
            @RequestParam("nom") String nom,
            @RequestParam("contenu") MultipartFile contenu) {

        if (contenu.isEmpty()) {
            return ResponseEntity.badRequest().body("Le fichier de pièce jointe est vide.");
        }

        try {
            PieceJointeDTO pieceJointeDTO = new PieceJointeDTO();
            pieceJointeDTO.setType(type);
            pieceJointeDTO.setNom(nom);
            pieceJointeDTO.setContenu(contenu.getBytes());

            pieceJointeService.enregistrerPieceJointe(pieceJointeDTO);

            return ResponseEntity.ok("Pièce jointe téléversée avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors du téléversement de la pièce jointe.");
        }
    }

    @GetMapping("/all")
    public List<PieceJointeDTO> getAll() {
        return pieceJointeService.getAll();
    }
}
