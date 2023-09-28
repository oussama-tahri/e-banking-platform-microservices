package com.adria.eerservice.entities;

import com.adria.eerservice.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ENTREE_EN_RELATION")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EER {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "VERSION")
    private String version;

    @Column(name = "CODE_PAYS_ASSOCIE")
    private String codePaysAssocie;

    @Column(name = "CODE_BANQUE_ASSOCIE")
    private String codeBanqueAssocie;

    @Column(name = "CREATEDBYUSER")
    private String createdByUser;

    @Column(name = "CREATEDON")
    private Date createdOn;

    @Column(name = "UPDATEDBYUSER")
    private String updatedByUser;

    @Column(name = "UPDATEDON")
    private Date updatedOn;

    @Column(name = "DATE_EMISSION_PIECE_IDENTITE")
    private Date dateEmissionPieceIdentite;

    @Column(name = "DATE_EXPIRATION_PIECE_IDENTITE")
    private Date dateExpirationPieceIdentite;

    @Column(name = "DATE_NAISSANCE")
    private Date dateNaissance;

    @Column(name = "TYPE_PIECE_IDENTITE")
    private String typePieceIdentite;

    @Column(name = "NUMERO_PIECE_IDENTITE")
    private String numeroPieceIdentite;

    @Column(name = "CIVILITE")
    private String civilite;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "NUMERO_TELEPHONE")
    private String numeroTelephone;

    @Column(name = "NOM_EMPLOYEUR")
    private String nomEmployeur;

    @Column(name = "REVENUE")
    private Double revenue;

    @Column(name = "PROFESSION")
    private String profession;

    @Column(name = "DOMAIN_ACTIVITE")
    private String domainActivite;

    @Column(name = "SITUATION_MATRIMONIALE")
    private String situationMatrimoniale;

    @Column(name = "NOMBRE_ENFANT")
    private Integer nombreEnfant;

    @Column(name = "NOM_MERE")
    private String nomMere;

    @Column(name = "LIEU_NAISSANCE")
    private String lieuNaissance;

    @Column(name = "LIEU_RESIDENCE")
    private String lieuResidence;

    @Column(name = "ADRESSE_SIEGE_SOCIAL")
    private String adresseSiegeSocial;

    @Column(name = "SIGNATURE_NUMERIQUE")
    private String signatureNumerique;

    @Column(name = "STATUT")
    private String statut;

    @Column(name = "SEGMENT")
    private String segment;

    @Column(name = "MAIL_PRINCIPAL")
    private String mailPrincipal;

    @Column(name = "IDENTIFIANT_CONTRAT")
    private String identifiantContrat;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "PATH_PIECE_IDENTITE")
    private String pathPieceIdentite;

    @Column(name = "SIGNATURE_NUMERIQUE_PATH")
    private String signatureNumeriquePath;

    @Column(name = "NIVEAU_WALLET")
    private String niveauWallet;

    @Column(name = "DATE_ENTREE_EN_RELATION")
    private Date dateEntreeEnRelation;

    @Column(name = "UPGRADE_STATUS")
    private String upgradeStatus;

    @Column(name = "MOTIF_REJET")
    private String motifRejet;

    @Column(name = "SALARIE")
    private Boolean salarie;

    @Column(name = "KYC_VALID")
    private Boolean kycValid;

    @Column(name = "ABONNEMENT_EBANKING")
    private Boolean abonnementEbanking;

    @Column(name = "ABONNEMENT_WALLET")
    private Boolean abonnementWallet;

    @Column(name = "EBANKING_ACTIVATED")
    private Boolean ebankingActivated;
    @Transient
    private User user;
    @OneToMany(mappedBy = "eer", fetch = FetchType.LAZY)
    private List<PieceJointe> pieceJointe;

}

