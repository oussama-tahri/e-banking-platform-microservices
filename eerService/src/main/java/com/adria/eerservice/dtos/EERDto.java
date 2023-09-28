package com.adria.eerservice.dtos;

import com.adria.eerservice.models.User;
import lombok.Data;

import java.util.Date;

@Data
public class EERDto {

    private Long id;
    private String version;
    private String codePaysAssocie;
    private String codeBanqueAssocie;
    private String createdByUser;
    private Date createdOn;
    private String updatedByUser;
    private Date updatedOn;
    private Date dateEmissionPieceIdentite;
    private Date dateExpirationPieceIdentite;
    private Date dateNaissance;
    private String typePieceIdentite;
    private String numeroPieceIdentite;
    private String civilite;
    private String lastName;
    private String firstName;
    private String numeroTelephone;
    private String nomEmployeur;
    private Double revenue;
    private String profession;
    private String domainActivite;
    private String situationMatrimoniale;
    private Integer nombreEnfant;
    private String nomMere;
    private String lieuNaissance;
    private String lieuResidence;
    private String adresseSiegeSocial;
    private String signatureNumerique;
    private String statut;
    private String segment;
    private String mailPrincipal;
    private String identifiantContrat;
    private Long userId;
    private String pathPieceIdentite;
    private String signatureNumeriquePath;
    private String niveauWallet;
    private Date dateEntreeEnRelation;
    private String upgradeStatus;
    private String motifRejet;
    private Boolean salarie;
    private Boolean kycValid;
    private Boolean abonnementEbanking;
    private Boolean abonnementWallet;
    private Boolean ebankingActivated;
    private User user;
}

