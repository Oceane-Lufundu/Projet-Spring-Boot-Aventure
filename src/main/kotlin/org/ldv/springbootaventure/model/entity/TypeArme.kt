package org.ldv.springbootaventure.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class TypeArme constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false)
    var id:Long?,
    var nom: String?,
    var nombreDes: Int?,
    var valeurDeMax: Int?,
    var multiplicateurCritique: Int?,
    var activationCritique: Int?,
) {
}