package org.ldv.springbootaventure.model.entity

import jakarta.persistence.*

@Entity
class Utilisateur (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long?,
    var email: String,
    var mdp: String,
    //Association entre Utilisateur et Personnage
    //Un utilisateur peut être rataché à plusieurs personnages
    @OneToMany
    @JoinColumn(name = "personnage_id")
    var personnage: Personnage? = null,
    //Association entre Utilisateur et Campagne
    //Un utilisateur peut être rataché à plusieurs campagnes
    @OneToMany
    @JoinColumn(name = "campagne_id")
    var campagne: Campagne?= null,
    //Association entre Utilisateur et Role
    //Plusieurs utilisateurs peuvent être rataché à plusieurs roles
    @ManyToMany
    @JoinColumn(name = "role_id")
    var role: MutableList<Role>?= null,
){
}