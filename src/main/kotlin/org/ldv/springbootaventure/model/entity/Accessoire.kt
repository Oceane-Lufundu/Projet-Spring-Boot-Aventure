package org.ldv.springbootaventure.model.entity

import jakarta.persistence.*
import org.ldv.springbootaventure.model.entity.Item
import org.ldv.springbootaventure.model.entity.Qualite
import org.ldv.springbootaventure.model.entity.TypeAccessoire
@Entity
@DiscriminatorValue("accessoire")
class Accessoire constructor(
    id: Long?,
    nom: String,
    description: String,
    cheminImage: String?,
    //TODO Attributs spécifiques aux accessoires
    //Association entre Accessoire et Qualite
    //Plusieurs accessoires peuvent être rataché a une qualite
    @ManyToOne
    @JoinColumn(name = "qualite_id")
    var qualite: Qualite? = null,
    //TODO Faire l'association avec TypeAccessoire
    //Association entre Accessoire et TypeAccessoire
    //Plusieurs accessoires peuvent être ratachés à un type d'accessoire
    @ManyToOne
    @JoinColumn(name = "typeArme_id")
    var typeAccessoire: TypeAccessoire?= null,
    //Association entre Accessoire et Personnage
    //Un accessoire peut être rattaché à plusieurs personnages
    @OneToMany
    @JoinColumn(name = "personnage_id")
    open var personnage: MutableList<Personnage>? = null,
) : Item(id, nom, description, cheminImage) {

    /**
     * Équipe l'arme sur un personnage, permettant au personnage de l'utiliser pour attaquer.
     *
     * @param cible Le personnage sur lequel l'arme est équipée.
     */
    override fun utiliser(cible: Personnage):String {
        return cible.equipe(this)
    }

}