package org.ldv.springbootaventure.model.entity

import jakarta.persistence.*
@Entity
//implémentation de la classe associative "LigneInventaire"
class LigneInventaire constructor(

    //association entre classe "LigneInventaire" et "LigneInventaireId"
    @EmbeddedId
var ligneInventaireId: ligneInventaireId,

    //association entre classe "LigneInventaire et "Personnage"
    @MapsId("personnageId")
    @ManyToOne
    @JoinColumn(name = "personnage_id")
    var personnage: Personnage? = null,

    //association entre classe "LigneInventaire et "Item"
    @MapsId("itemId")
    @ManyToOne
    @JoinColumn(name = "item_id")
    var item: Item? = null,


    var quantite:Int,){
}