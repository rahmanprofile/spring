package com.rahman.learning.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "notes")
public class Notes {
    @Id
    private Long id;
    private String title;
    private String content;
}
