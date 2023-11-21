package org.ldv.springbootaventure.model.entity

import jakarta.persistence.*

@Entity
class TypeAccessoire constructor (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var nom : String,
    var typeBonus : String,

    //Association entre Qualite et Arme
    //Une qualite peut avoir plusieurs armes
    @OneToMany(mappedBy = "typeAccessoire", cascade = [CascadeType.REMOVE])
    var accessoire : MutableList<Accessoire> = mutableListOf(),){
}