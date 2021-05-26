package com.hacettepe.clubinn.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "feedbacks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @Column(name = "feedback_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinTable(name = "feedbacks_subclub", joinColumns = {
            @JoinColumn(name = "feedback_id")}, inverseJoinColumns = {
            @JoinColumn(name = "subclub_id")})
    private SubClub ownerSubClub;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "owner")
    private User owner;

    @Column(name = "feedbacktype")
    private String feedbackType;

    @Column(name = "comment", length = 255)
    private String comment;

}
