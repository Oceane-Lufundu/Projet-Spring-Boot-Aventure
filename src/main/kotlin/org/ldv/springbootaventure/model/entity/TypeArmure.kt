package org.ldv.springbootaventure.model.entity
import jakarta.persistence.*

@Entity
class TypeArmure constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id:Long?,
    var nom: String,
    var bonusType:Int,

    //Association entre TypeArmure et Armure
    //Un type d'armure peut avoir plusieurs armures
    @OneToMany(mappedBy = "typeArmure")
    var armure: MutableList<Armure> = mutableListOf(),

    //Association entre Personnage et TypeArmure
    //plusieurs utilisateurs peuvent être rataché à un TypeArmure
    @ManyToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "personnage_id")
    open var personnage: Personnage? = null,

){
}


