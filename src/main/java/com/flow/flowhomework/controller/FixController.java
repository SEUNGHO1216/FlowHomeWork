package com.flow.flowhomework.controller;

import com.flow.flowhomework.dto.ExtensionDto;
import com.flow.flowhomework.model.Fix;
import com.flow.flowhomework.repository.FixRepository;
import com.flow.flowhomework.service.FixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FixController {

    private final FixService fixService;

    @GetMapping("/fix-extension")
    public ResponseEntity<List<Fix>> showFixExtensions(){
        return fixService.showFixExtension();
    }

    @PutMapping("/fix-extension/{extensionId}")
    public void changeExtensionStatus(@PathVariable("extensionId") Long extensionId){
        fixService.changeExtensionStatus(extensionId);
    }

}
