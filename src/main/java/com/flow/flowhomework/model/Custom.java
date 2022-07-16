package com.flow.flowhomework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Custom {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customExtension;

    public Custom(String extension){
        this.customExtension = extension;
    }
}
