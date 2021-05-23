package com.hacettepe.clubinn.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "forms")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Form {

    @Id
    @Column(name = "form_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long formId;

    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "baglioldugugrup")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SubClub subClub;

    @OneToMany( cascade = CascadeType.ALL)
    private Collection<Question> questionList;


}