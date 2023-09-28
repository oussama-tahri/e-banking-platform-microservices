package com.adria.eerservice.web;

import com.adria.eerservice.dtos.EERDto;
import com.adria.eerservice.exceptions.EERNotFoundException;
import com.adria.eerservice.exceptions.EERServiceException;
import com.adria.eerservice.services.IEERService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eer")
@AllArgsConstructor
public class EERController {

    private final IEERService eerService;

    @PostMapping("/create")
    public ResponseEntity<EERDto> createEER(@RequestBody EERDto eerDto) {
        try {
            EERDto createdEER = eerService.createEER(eerDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEER);
        } catch (EERServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EERDto> getEERById(@PathVariable Long id) {
        try {
            EERDto eer = eerService.getEERById(id);
            return ResponseEntity.status(HttpStatus.OK).body(eer);
        } catch (EERNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (EERServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<EERDto>> getAllEERs() {
        try {
            List<EERDto> eerList = eerService.getAllEERs();
            return ResponseEntity.status(HttpStatus.OK).body(eerList);
        } catch (EERServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEER(@PathVariable Long id, @RequestBody EERDto eerDto) {
        try {
            eerService.updateEER(id, eerDto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EERNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (EERServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEER(@PathVariable Long id) {
        try {
            eerService.deleteEER(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EERNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (EERServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<EERDto>> getByUser(@PathVariable Long userId) {
        List<EERDto> eerList = eerService.getByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(eerList);
    }
}

