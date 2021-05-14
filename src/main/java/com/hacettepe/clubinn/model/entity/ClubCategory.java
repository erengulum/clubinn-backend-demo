package com.hacettepe.clubinn.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "clubcategory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubCategory {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "imageurl", length = 200)
    private String imageurl;


    @Column(name = "clubname", length = 100, unique = true)
    private String clubCategoryName;

    @Column(name = "description", length = 200)
    private String description;
    //One to many subclub tutacak

    @OneToMany(mappedBy = "clubCategory", cascade = CascadeType.ALL)
    private Collection<SubClub> subClubs;

}
