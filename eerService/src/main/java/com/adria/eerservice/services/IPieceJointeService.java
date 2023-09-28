package com.adria.eerservice.services;

import com.adria.eerservice.dtos.PieceJointeDTO;

import java.util.List;

public interface IPieceJointeService {
    PieceJointeDTO enregistrerPieceJointe(PieceJointeDTO pieceJointeDTO);

    List<PieceJointeDTO> getAll();
}
