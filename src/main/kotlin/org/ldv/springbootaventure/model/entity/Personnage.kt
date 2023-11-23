package org.ldv.springbootaventure.model.entity

import jakarta.persistence.*


@Entity
//implémentation de la classe "personnage"
class Personnage constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false)
    var id : Long?,
    var nom: String?,
    var pointDeVie: Int?,
    var pointDeVieMax: Int?,
    var attaque : Int?,
    var defense: Int?,
    var endurance: Int?,
    var vitesse: Int?,
    var cheminImage: String?,
    @ManyToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "combat_id")
    open var combat: Combat? = null,

    //Association entre Campagne et Personnage
    //plusieurs utilisateurs peuvent être rattaché à une campagne
    @ManyToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "campagne_id")
    open var campagne: Campagne? = null,

    //association entre classe "Personnage" et "LigneInventaire"
    @OneToMany(mappedBy = "personnage")
    var ligneInventaire: List<LigneInventaire>? = mutableListOf(),

    //Association entre Personnage et Armure
    //Une armure peut être rataché à plusieurs utilisateurs
    @ManyToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "armure_id")
    open var armure: Armure? = null,

//a

    ) {





}

//création des méthodes