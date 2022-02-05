package com.aldeamo.app.project.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "arrays")
public class Array {

    @Id
    private Long id;
    private String inputArray;

}
