package com.flow.flowhomework.controller;

import com.flow.flowhomework.dto.ExtensionDto;
import com.flow.flowhomework.model.Custom;
import com.flow.flowhomework.repository.CustomRepository;
import com.flow.flowhomework.service.CustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CustomController {

    private final CustomRepository customRepository;
    private final CustomService customService;

    @GetMapping("/custom-extension")
    public ResponseEntity<List<Custom>> showExtensions(){
        return customService.showExtensions();
    }
    @PostMapping("/custom-extension")
    public ResponseEntity<Object> saveExtension(@RequestBody ExtensionDto extensionDto){
        System.out.println(extensionDto.getExtension());
        return customService.saveExtension(extensionDto);
    }
    @DeleteMapping("/custom-extension/{extensionId}")
    public ResponseEntity<List<Custom>> deleteExtension(@PathVariable("extensionId") Long extensionId){
        return customService.deleteExtension(extensionId);
    }
}
