package org.ldv.springbootaventure.model.entity

import jakarta.persistence.*

@Entity

@DiscriminatorValue("armure")
class Armure constructor(
    id:Long?,
    nom: String,
    description:String,
    cheminImage:String?,

//TODO Attributs spécifiques aux armures
        //Association entre Armure et Qualite
        //Plusieurs armures peuvent être rataché a une qualite
    @ManyToOne
    @JoinColumn(name = "qualite_id")
    open var qualite: Qualite? = null,
        //TODO Faire l'association avec TypeArmure

    //Association entre Personnage et Armure
    //plusieurs utilisateurs peuvent être rattaché à une armure
    @OneToMany
    @JoinColumn(name = "personnage_id")
    var personnage: MutableList<Personnage> = mutableListOf(),

    //Association entre Armure et TypeArmure
    //Plusieurs armures peuvent être rataché à un type d'armure
    @ManyToOne
    @JoinColumn(name = "typeArmure_id")
    var typeArmure: TypeArmure?= null,

) : Item(id, nom, description, cheminImage) {

    //methode calculProtection
    fun calculProtection(): Int {
        var protection = this.typeArmure!!.bonusType + this.qualite!!.bonusQualite // = Bonus de protection
        return protection
    }

    /**
     * Équipe l'arme sur un personnage, permettant au personnage de l'utiliser pour attaquer.
     *
     * @param cible Le personnage sur lequel l'arme est équipée.
     */
    override fun utiliser(cible: Personnage):String {
        return cible.equipe(this)
    }

}
