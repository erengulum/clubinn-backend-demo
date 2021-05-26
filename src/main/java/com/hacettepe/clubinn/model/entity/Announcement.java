package com.hacettepe.clubinn.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "announcements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {

    @Id
    @Column(name = "announcement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "headline", length = 100)
    private String headline;

    @Column(name = "content", length = 500)
    private String content;

    @ManyToOne
    @JoinTable(name = "ANNOUNCEMENT_SUBCLUB", joinColumns = {
            @JoinColumn(name = "ANNOUNCEMENT_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "SUBCLUD_ID")})
    private SubClub subClub;

}
