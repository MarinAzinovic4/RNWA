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
@Table(name = "department")
@Data
@Getter
@Setter
public class Department {
    @Id
    @Column(name = "DepartmentID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "Name")
    private String name;

    @Column(name = "GroupName")
    private String groupName;
    
    @Column(name = "ModifiedDate")
    private LocalDateTime modifiedDate;

    public Department(String name, String groupName, LocalDateTime modifieDate) {
        this.name = name;
        this.groupName = groupName;
        this.modifiedDate = modifiedDate;
    }
}
