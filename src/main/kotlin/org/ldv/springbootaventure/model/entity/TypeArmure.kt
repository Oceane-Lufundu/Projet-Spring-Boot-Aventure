package org.ldv.springbootaventure.model.entity

import jakarta.persistence.*

@Entity
class TypeArmure (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id:Long,
    var nom: String,
    var bonusType:Int,
    ){
}
