package org.ldv.springbootaventure.model.entity


import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Qualite constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var nom: String,
    var couleur: String,
    var bonusQualite: Int,

    //Association entre Qualite et Arme
    //Une qualite peut avoir plusieurs armes
    @OneToMany(mappedBy = "qualite", cascade = [CascadeType.REMOVE])
    var arme: MutableList<Arme> = mutableListOf(),
//TODO Ajouter les  autres associations
    //Association entre Qualite et Armure
    @OneToMany(mappedBy = "qualite", cascade = [CascadeType.REMOVE])
    open var armures: MutableList<Armure> = mutableListOf(),
    //Association entre Qualite et Accessoire
    @OneToMany(mappedBy = "qualite", cascade = [CascadeType.REMOVE])
    var accessoire : MutableList<Accessoire> = mutableListOf(),
) {

}
