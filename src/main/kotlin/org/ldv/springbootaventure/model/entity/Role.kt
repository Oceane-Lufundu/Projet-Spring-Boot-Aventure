package org.ldv.springbootaventure.model.entity

import jakarta.persistence.*

@Entity
@DiscriminatorValue("role")
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var nom: String,
    //Association entre Role et Utilisateur
    //Plusieurs roles peuvent être rataché à plusieurs utilisateurs
    @ManyToMany
    @JoinColumn(name = "utilisateur_id")
    var utilisateurs: MutableList<Utilisateur>?= null){
}