package com.flow.flowhomework.service;

import com.flow.flowhomework.dto.ExtensionDto;
import com.flow.flowhomework.model.Fix;
import com.flow.flowhomework.repository.FixRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FixService {

    private final FixRepository fixRepository;
    //고정 확장자 리스트
    public List<String> fixExtensionList = Arrays.asList("bat","cmd","com","cpl","exe", "scr","js");
    //고정 확장자 조회 시 한번만 리스트 저장되고 그 후엔 조회만 하게끔 하기위한 플래그
    private boolean flag = true;

    //고정확장자 리스트를 보여주는 함수
    public ResponseEntity<List<Fix>> showFixExtension() {
        //맨 처음 함수 호출시만 고정확장자를 저장시킬 수 있도록 함
        if(flag){
            try{
                fixExtensionList.stream().forEach(
                        extension -> fixRepository.save(new Fix(extension)));
            }catch(Exception e){
                e.printStackTrace();
            }
            flag = false;
        }
        return ResponseEntity.status(200).body(fixRepository.findAll());
    }

    //고정확장자의 체크 상태를 true 또는 false로 변경시켜주는 함수
    @Transactional
    public void changeExtensionStatus(Long extensionId) {

        Fix fix = fixRepository.findById(extensionId).orElse(null);
        if(fix != null){
            fix.changeCheckedStatus(fix);
        }

    }
}
