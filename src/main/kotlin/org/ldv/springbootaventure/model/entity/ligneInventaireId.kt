package org.ldv.springbootaventure.model.entity

import jakarta.persistence.Embeddable
import java.io.Serializable

//La classe ligneInventaireId  peut-être « embarqué »
@Embeddable
class ligneInventaireId constructor(
    val personnageId: Long?,
    val itemId : Long?,
): Serializable{

}