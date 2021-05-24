package com.hacettepe.clubinn.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Question {

    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "question_content", length = 200)
    private String questionContent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id")
    private Form form;



}