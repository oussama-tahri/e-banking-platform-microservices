package com.adria.eerservice.services;

import com.adria.eerservice.dtos.EERDto;
import com.adria.eerservice.exceptions.EERNotFoundException;
import com.adria.eerservice.exceptions.EERServiceException;

import java.util.List;

public interface IEERService {
    EERDto createEER(EERDto eerDto) throws EERServiceException;

    EERDto getEERById(Long id) throws EERNotFoundException, EERServiceException;

    List<EERDto> getAllEERs() throws EERServiceException;

    EERDto updateEER(Long id, EERDto eerDto) throws EERNotFoundException, EERServiceException;

    void deleteEER(Long id) throws EERNotFoundException, EERServiceException;
    List<EERDto> getByUser(Long userId);
}
