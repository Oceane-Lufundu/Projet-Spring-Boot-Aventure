package org.ldv.springbootaventure.model.entity

import jakarta.persistence.*



@Entity

@DiscriminatorValue("armure")
class Armure constructor(
    id:Long?,
    nom: String?,
    description:String?,
    cheminImage:String?,
    bonusType:Int?,
//TODO Attributs spécifiques aux armures
        //Association entre Armure et Qualite
        //Plusieurs armures peuvent être rataché a une qualite

    @ManyToOne
    @JoinColumn(name = "qualite_id")
    open var qualite: Qualite? = null,
        //TODO Faire l'association avec TypeArmure

//Association entre Armure et TypeArmure
//Plusieurs armures peuvent être rataché à un type d'armure
@ManyToOne
@JoinColumn(name = "typeArmure_id")
var typeArmure: TypeArmure?= null,
) : Item(id, nom.toString(), description.toString(), cheminImage){

}
