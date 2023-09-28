package com.adria.eerservice.services;

import com.adria.eerservice.dtos.PieceJointeDTO;
import com.adria.eerservice.entities.PieceJointe;
import com.adria.eerservice.mappers.PieceJointeMapper;
import com.adria.eerservice.repositories.PieceJointeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PieceJointeServiceImpl implements IPieceJointeService {
    private final PieceJointeRepository pieceJointeRepository;
    private final PieceJointeMapper pieceJointeMapper;
    @Override
    public PieceJointeDTO enregistrerPieceJointe(PieceJointeDTO pieceJointeDTO) {
        PieceJointe pieceJointe = pieceJointeMapper.toEntity(pieceJointeDTO);
        pieceJointe.setType(pieceJointeDTO.getType());
        pieceJointe.setNom(pieceJointeDTO.getNom());
        pieceJointe.setContenu(pieceJointeDTO.getContenu());
        pieceJointeRepository.save(pieceJointe);
        return pieceJointeMapper.toDto(pieceJointe);
    }

    @Override
    public List<PieceJointeDTO> getAll() {
        List<PieceJointe> pieceJointes = pieceJointeRepository.findAll();
        List<PieceJointeDTO> pieceJointeDTOS = pieceJointes.stream().map(pieceJointe -> {
            PieceJointeDTO pieceJointeDTO = pieceJointeMapper.toDto(pieceJointe);
            return pieceJointeDTO;
        }).collect(Collectors.toList());
        return pieceJointeDTOS;
    }
}
