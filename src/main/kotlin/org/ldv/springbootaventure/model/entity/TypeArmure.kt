package org.ldv.springbootaventure.model.entity

import jakarta.persistence.*

@Entity
class TypeArmure (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var name: String,
    val bonusType:Int,

    ){



}
