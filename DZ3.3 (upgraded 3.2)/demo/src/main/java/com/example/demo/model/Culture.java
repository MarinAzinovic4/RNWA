package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "culture")
@Data
@Getter
@Setter
public class Culture {
    @Id
    @Column(name = "CultureID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;
    
    @Column(name = "Name")
    private String name;
    
    @Column(name = "ModifiedDate")
    private LocalDateTime modifiedDate;

    public Culture(String name, LocalDateTime modifieDate) {
        this.name = name;
        this.modifiedDate = modifiedDate;
    }
}
