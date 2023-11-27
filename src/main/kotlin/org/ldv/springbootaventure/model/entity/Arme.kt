package org.ldv.springbootaventure.model.entity

import jakarta.persistence.*



@Entity

@DiscriminatorValue("arme")
class Arme constructor(
    id:Long?,
    nom: String?,
    description:String?,
    cheminImage:String?,

//TODO Attributs spécifiques aux armes
    //Association entre Arme et Qualite
    //Plusieurs armes peuvent être rattaché a une qualité

    @ManyToOne
    @JoinColumn(name = "qualite_id")
    open var qualite: Qualite? = null,

    //TODO Faire l'association avec TypeArme
    //Association entre Arme et TypeArme
    //Plusieurs armes peuvent être rattaché à un type d'arme
    @ManyToOne
    @JoinColumn(name = "typeArme_id")
    var typeArme: TypeArme?= null,
) : Item(id, nom.toString(), description.toString(), cheminImage){

    //Association entre Personnage et Arme
    //plusieurs utilisateurs peuvent être rattaché à une arme
    @OneToMany(mappedBy = "arme", cascade = [CascadeType.REMOVE])
    open var personnages: MutableList<Personnage> = mutableListOf()

    //méthode calculerDegat()
    open fun calculerDegat():Int{//Ne prends pas de paramètres, retourne un entier.
        var desDegat = TirageDeDes(this.typeArme!!.nombreDes!!, this.typeArme!!.valeurDeMax!!).lance()
        var result = desDegat
        val desCritique = TirageDeDes(1, 20).lance()

        if(desCritique>=this.typeArme!!.activationCritique!!){

            var critique = result * this.typeArme!!.multiplicateurCritique!!
            var degatsTotal = critique + this.qualite!!.bonusQualite
            return degatsTotal

        } else {
            var degatsTotal = result+this.qualite!!.bonusQualite //on ajoute le bonusRarete de la qualité de l’arme.
            return degatsTotal
        }

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
