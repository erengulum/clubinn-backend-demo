package com.hacettepe.clubinn.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "subclubs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubClub {

    @Id
    @Column(name = "subclub_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "imageurl", length = 100)
    private String imageurl;

    @Column(name = "subClubName", length = 100, unique = true)
    private String subClubName;

    @Column(name = "description", length = 200)
    private String description;

    //many to one ilişkiyle club'a bağlanacak

    @ManyToOne
    @JoinTable(name = "SUBCLUB_CATEGORY", joinColumns = {
            @JoinColumn(name = "SUBCLUD_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "CLUBCATEGORY_ID") })
    private ClubCategory clubCategory;





}
