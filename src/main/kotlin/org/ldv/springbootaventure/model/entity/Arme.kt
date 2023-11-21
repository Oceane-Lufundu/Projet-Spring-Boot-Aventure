package org.ldv.springbootaventure.model.entity

import jakarta.persistence.*



@Entity

@DiscriminatorValue("arme")
class Arme constructor(
    id:Long?,
    nom: String?,
    description:String?,
    cheminImage:String?,

//TODO Attributs spécifiques aux armures
    //Association entre Arme et Qualite
    //Plusieurs armes peuvent être rattaché a une qualité

    @ManyToOne
    @JoinColumn(name = "qualite_id")
    open var qualite: Qualite? = null,
    //TODO Faire l'association avec TypeArmure

//Association entre Armure et TypeArmure
//Plusieurs armures peuvent être rataché à un type d'armure
    @ManyToOne
    @JoinColumn(name = "typeArmure_id")
    var typeArme: TypeArme?= null,
) : Item(id, nom.toString(), description.toString(), cheminImage){

}
