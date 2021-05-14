package com.hacettepe.clubinn.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "clubcategoryrequests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubCategoryRequest {

    @Id
    @Column(name = "req_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long req_id;

    @Column(name = "name", length = 100)
    private String clubCategoryName;

    @Column(name = "reason", length = 200)
    private String reason;

    @Column(name = "descr", length = 200)
    private String description;

    @Column(name = "votes", updatable = true)
    private int numberOfVotes;

    @Column(name = "requestStatus")
    private Boolean status;  //eğer talep kabul edildiyse True; edilmediyse ya da beklemede ise False

    @Column(name = "headerimageurl", length = 200)
    private String imageurl;

}
