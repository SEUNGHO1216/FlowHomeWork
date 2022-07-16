package com.flow.flowhomework.service;


import com.flow.flowhomework.dto.ExtensionDto;
import com.flow.flowhomework.model.Custom;
import com.flow.flowhomework.repository.CustomRepository;
import com.flow.flowhomework.repository.FixRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomService {

    private final CustomRepository customRepository;
    private final FixRepository fixRepository;

    //커스텀 확장자를 추가하고 삭제할 때마다 변동되는 결과를 비동기적으로 반환하고 싶음
    //따라서 get방식 뿐 아니라 post, delete방식에서도 변동된 커스텀 확장자들을 반환할 계획
    //반환 시 매번 findAll()을 하게 되면 모든 정보를 db에서 다 읽어 와서 비효율적 -> 리스트로 자바 단에서 관리하고 리스트로 반환함
    private List<Custom> customList = new ArrayList<>();

    //리스트를 통해
    private boolean flag = true;

    //get 방식으로 커스텀 확장자 전부 보여줌
    public ResponseEntity<List<Custom>> showExtensions() {
        if(flag){
            customList = customRepository.findAll();
            flag = false;
        }
        return ResponseEntity.status(200).body(customList);
    }

    @Transactional
    public ResponseEntity<Object> saveExtension(ExtensionDto extensionDto) {
        String extension = extensionDto.getExtension();
        //예외처리
        if(exception(extension).getStatusCodeValue()==400){
            return exception(extension);
        };

        Custom custom = new Custom(extension);
        customRepository.save(custom);
        customList.add(custom);

        return ResponseEntity.status(200).body(customList);
    }
    @Transactional
    public ResponseEntity<List<Custom>> deleteExtension(Long extensionId) {
        Custom custom = customRepository.findById(extensionId).orElse(null);
        if(custom != null){
            //db에서 추출한 확장자 이름과 같은 이름의 리스트 요소를 지움
            for(int i =0; i<customList.size(); i++){
                if(customList.get(i).getCustomExtension().equals(custom.getCustomExtension())){
                    customList.remove(i);
                }
            }
            customRepository.deleteById(extensionId);
        }

        return ResponseEntity.status(200).body(customList);
    }

    //예외처리 메소드
    private ResponseEntity<Object> exception(String extension){
        if(fixRepository.existsByFixExtension(extension) || customRepository.existsByCustomExtension(extension)){
            return ResponseEntity.status(400).body("이미 입력된 확장자입니다");
        }
        //예외처리
        if(extension.length()>20){
            return ResponseEntity.status(400).body("확장자는 20자 이하로 작성해야합니다");
        }
        if(customList.size()>=200){
            return ResponseEntity.status(400).body("추가한 확장자는 200개까지 저장 가능합니다");
        }
        return ResponseEntity.status(200).body("");
    }
}
