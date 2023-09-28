package com.adria.eerservice.mappers;

import com.adria.eerservice.dtos.PieceJointeDTO;
import com.adria.eerservice.entities.PieceJointe;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PieceJointeMapper {

    public PieceJointeDTO toDto(PieceJointe pieceJointe) {
        PieceJointeDTO pieceJointeDTO = new PieceJointeDTO();
        BeanUtils.copyProperties(pieceJointe, pieceJointeDTO);
        return pieceJointeDTO;
    }

    public PieceJointe toEntity(PieceJointeDTO dto) {
        PieceJointe entity = new PieceJointe();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
