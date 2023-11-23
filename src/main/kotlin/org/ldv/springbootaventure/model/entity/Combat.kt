package org.ldv.springbootaventure.model.entity

import jakarta.persistence.*

@Entity
class Combat constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false)

    var id: Long,
    var nombreTours: Int,

    @OneToMany(mappedBy = "combat")
    open var personnages: MutableList<Personnage> = mutableListOf(),

    //Association entre Combat et Campagne
    //Plusieurs Combats peuvent être rataché à plusieurs Campagnes
    @ManyToMany
    @JoinColumn(name = "campagne_id")
    var campagne: MutableList<Campagne>?= null,

    // Méthode du tour de combat du monstre
   // fun tourMonstre()

){
}