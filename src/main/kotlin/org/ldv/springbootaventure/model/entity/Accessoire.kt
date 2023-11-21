package org.ldv.springbootaventure.model.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.ldv.springbootaventure.model.entity.Item
import org.ldv.springbootaventure.model.entity.Qualite
import org.ldv.springbootaventure.model.entity.TypeAccessoire
@Entity
@DiscriminatorValue("accessoire")
class Accessoire constructor(
    id: Long,
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
) : Item(id, nom, description, cheminImage) {


}