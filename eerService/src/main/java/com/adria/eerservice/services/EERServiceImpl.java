package com.adria.eerservice.services;

import com.adria.eerservice.dtos.EERDto;
import com.adria.eerservice.entities.EER;
import com.adria.eerservice.exceptions.EERNotFoundException;
import com.adria.eerservice.exceptions.EERServiceException;
import com.adria.eerservice.feign.UserFeignClient;
import com.adria.eerservice.mappers.EERMapper;
import com.adria.eerservice.models.User;
import com.adria.eerservice.repositories.EERRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class EERServiceImpl implements IEERService {

    private final EERRepository eerRepository;
    private final EERMapper eerMapper;
    private final UserFeignClient userFeignClient;


    @Override
    public EERDto createEER(EERDto eerDto) throws EERServiceException {
        EER eer = eerMapper.dtoToEntity(eerDto);
        if (eerDto.getUserId() != null) {
            User user = userFeignClient.getUserById(eerDto.getUserId());
            eer.setUser(user);
        }
        eer = eerRepository.save(eer);
        return eerMapper.entityToDto(eer);
    }


    @Override
    public EERDto getEERById(Long eerId) throws EERNotFoundException, EERServiceException {
//        try {
//            EER eer = eerRepository.findById(id)
//                    .orElseThrow(() -> new EERNotFoundException("EER not found with ID: " + id));
//            return eerMapper.entityToDto(eer);
//        } catch (EERNotFoundException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new EERServiceException("Failed to get EER by ID: " + e.getMessage());
//        }

        EER eer = eerRepository.findById(eerId)
                .orElseThrow(() -> new EERNotFoundException("EER not found"));

        EERDto eerDto = eerMapper.entityToDto(eer);
        //
        if (eer.getUserId() != null) {
            User user = userFeignClient.getUserById(eer.getUserId());
            eerDto.setUser(user);
        }

        return eerDto;
    }

    @Override
    public List<EERDto> getAllEERs() throws EERServiceException {
//        try {
//            List<EER> eerList = eerRepository.findAll();
//            return eerMapper.eerEntityListToDtoList(eerList);
//        } catch (Exception e) {
//            throw new EERServiceException("Failed to get all EERs: " + e.getMessage());
//        }
        try {
            List<EER> eers = eerRepository.findAll();
            return eers.stream().map(eer -> {
                EERDto eerDto = eerMapper.entityToDto(eer);
                if (eer.getUserId() != null) {
                    User user = userFeignClient.getUserById(eer.getUserId());
                    eerDto.setUser(user);
                }
                return eerDto;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new EERServiceException("Failed to get all EERs: " + e.getMessage());
        }
    }

    @Override
    public EERDto updateEER(Long eerId, EERDto eerDto) throws EERNotFoundException, EERServiceException {
//        try {
//            EER existingEER = eerRepository.findById(id)
//                    .orElseThrow(() -> new EERNotFoundException("EER not found with ID: " + id));
//            EER updatedEER = eerMapper.dtoToEntity(eerDto);
//            updatedEER.setId(existingEER.getId());
//            eerRepository.save(updatedEER);
//        } catch (EERNotFoundException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new EERServiceException("Failed to update EER: " + e.getMessage());
//        }

        EER existingEER = eerRepository.findById(eerId)
                .orElseThrow(() -> new EERNotFoundException("EER not found with ID: " + eerId));

        eerMapper.updateEERFromDTO(eerDto, existingEER);
        // Update the properties of the existing EER with the values from eerDto
//        existingEER.setVersion(eerDto.getVersion());
//        existingEER.setCodePaysAssocie(eerDto.getCodePaysAssocie());
//        existingEER.setCodeBanqueAssocie(eerDto.getCodeBanqueAssocie());
//        existingEER.setCreatedByUser(eerDto.getCreatedByUser());
//        existingEER.setCreatedOn(eerDto.getCreatedOn());
//        existingEER.setUpdatedByUser(eerDto.getUpdatedByUser());
//        existingEER.setUpdatedOn(eerDto.getUpdatedOn());
//        existingEER.setDateEmissionPieceIdentite(eerDto.getDateEmissionPieceIdentite());
//        existingEER.setDateExpirationPieceIdentite(eerDto.getDateExpirationPieceIdentite());
//        existingEER.setDateNaissance(eerDto.getDateNaissance());
//        existingEER.setTypePieceIdentite(eerDto.getTypePieceIdentite());
//        existingEER.setNumeroPieceIdentite(eerDto.getNumeroPieceIdentite());
//        existingEER.setCivilite(eerDto.getCivilite());
//        existingEER.setLastName(eerDto.getLastName());
//        existingEER.setFirstName(eerDto.getFirstName());
//        existingEER.setNumeroTelephone(eerDto.getNumeroTelephone());
//        existingEER.setNomEmployeur(eerDto.getNomEmployeur());
//        existingEER.setRevenue(eerDto.getRevenue());
//        existingEER.setProfession(eerDto.getProfession());
//        existingEER.setDomainActivite(eerDto.getDomainActivite());
//        existingEER.setSituationMatrimoniale(eerDto.getSituationMatrimoniale());
//        existingEER.setNombreEnfant(eerDto.getNombreEnfant());
//        existingEER.setNomMere(eerDto.getNomMere());
//        existingEER.setLieuNaissance(eerDto.getLieuNaissance());
//        existingEER.setLieuResidence(eerDto.getLieuResidence());
//        existingEER.setAdresseSiegeSocial(eerDto.getAdresseSiegeSocial());
//        existingEER.setSignatureNumerique(eerDto.getSignatureNumerique());
//        existingEER.setStatut(eerDto.getStatut());
//        existingEER.setSegment(eerDto.getSegment());
//        existingEER.setMailPrincipal(eerDto.getMailPrincipal());
//        existingEER.setIdentifiantContrat(eerDto.getIdentifiantContrat());
//        existingEER.setUserId(eerDto.getUserId());
//        existingEER.setPathPieceIdentite(eerDto.getPathPieceIdentite());
//        existingEER.setSignatureNumeriquePath(eerDto.getSignatureNumeriquePath());
//        existingEER.setNiveauWallet(eerDto.getNiveauWallet());
//        existingEER.setDateEntreeEnRelation(eerDto.getDateEntreeEnRelation());
//        existingEER.setUpgradeStatus(eerDto.getUpgradeStatus());
//        existingEER.setMotifRejet(eerDto.getMotifRejet());
//        existingEER.setSalarie(eerDto.getSalarie());
//        existingEER.setKycValid(eerDto.getKycValid());
//        existingEER.setAbonnementEbanking(eerDto.getAbonnementEbanking());
//        existingEER.setAbonnementWallet(eerDto.getAbonnementWallet());
//        existingEER.setEbankingActivated(eerDto.getEbankingActivated());

        if (eerDto.getUserId() != null) {
            User user = userFeignClient.getUserById(eerDto.getUserId());
            existingEER.setUser(user);
        }

        existingEER = eerRepository.save(existingEER);
        return eerMapper.entityToDto(existingEER);
    }


//    @Override
//    public void updateEER(Long id, EERDto eerDto) throws EERNotFoundException, EERServiceException {
//        try {
//            EER existingEER = eerRepository.findById(id)
//                    .orElseThrow(() -> new EERNotFoundException("EER not found with ID: " + id));
//
//            // Update the properties of the existing EER with the values from eerDto
//            existingEER.setFirstName(eerDto.getFirstName());
//            // Update other properties as needed...
//
//            eerRepository.save(existingEER);
//        } catch (EERNotFoundException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new EERServiceException("Failed to update EER: " + e.getMessage());
//        }
//    }

    @Override
    public void deleteEER(Long id) throws EERNotFoundException {
            EER existingEER = eerRepository.findById(id)
                    .orElseThrow(() -> new EERNotFoundException("EER not found with ID: " + id));
            eerRepository.delete(existingEER);
    }

    @Override
    public List<EERDto> getByUser(Long userId) {
        List<EER> users = eerRepository.findByUserId(userId);
        return users.stream().map(eerMapper::entityToDto).collect(Collectors.toList());
    }
}

