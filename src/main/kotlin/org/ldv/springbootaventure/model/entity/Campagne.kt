package org.ldv.springbootaventure.model.entity
import jakarta.persistence.*

@Entity
class Campagne constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false)
    var id: Long,
    var nom: String,
    var score: Int,
    var etat: String,
    var dernierScore: Int,
    var meilleurScore: Int,

    //Association entre Campagne et Personnage
    //Un utilisateur peut être rataché à plusieurs campagnes
        @OneToMany(mappedBy = "campagne")
        open var personnages: MutableList<Personnage> = mutableListOf(),

    //Association entre Campagne et Combat
    //Plusieurs campagnes peuvent être rataché à plusieurs combats
        @ManyToMany
        @JoinColumn(name = "combat_id")
        var combat: MutableList<Combat>?= null,

    //Association entre Campagne et Utilisateur
    //plusieurs Campagnes peuvent être rataché à un Utilisateur
        @ManyToOne(cascade = [CascadeType.REMOVE])
        @JoinColumn(name = "utilisateur_id")
        open var utilisateur: Utilisateur? = null,

){
}

