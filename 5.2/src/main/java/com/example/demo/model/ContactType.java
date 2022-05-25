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
@Table(name = "contacttype")
@Data
@Getter
@Setter
public class ContactType {
    @Id
    @Column(name = "ContactTypeID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long contactTypeID;
    
    @Column(name = "Name")
    private String name;
    
    @Column(name = "ModifiedDate")
    private LocalDateTime modifiedDate;

    public ContactType(String name, String groupName, LocalDateTime modifieDate) {
        this.name = name;
        this.modifiedDate = modifiedDate;
    }
}