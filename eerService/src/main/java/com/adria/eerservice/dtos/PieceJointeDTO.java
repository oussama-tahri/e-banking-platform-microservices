package com.adria.eerservice.dtos;

import lombok.Data;

@Data
public class PieceJointeDTO {
    private String type;
    private String nom;
    private byte[] contenu;
}
