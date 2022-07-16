package com.flow.flowhomework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Fix {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String fixExtension;

    private boolean isChecked;

    public Fix(String extension){
        this.fixExtension = extension;
        this.isChecked = false;
    }


    public void changeCheckedStatus(Fix fix){

        if(fix.isChecked()){
            this.isChecked = false;
        }else{
            this.isChecked = true;
        }
    }

}
