package org.ldv.springbootaventure.model.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("potion")
class Potion constructor(
    id: Long?,
    nom: String,
    description: String,
    cheminImage: String?,
    val soin:Int
) : Item(id, nom, description, cheminImage) {

    /**
     * Utilise l'objet de type Potion de Soin sur un personnage cible, augmentant ses points de vie.
     *
     * @param cible Le personnage sur lequel appliquer la potion de soin.
     * @return Un message décrivant l'action effectuée, notamment le soin apporté.
     */
    override fun utiliser(cible: Personnage): String {
        // Stocker la valeur initiale des points de vie de la cible
        val pvInitiale = cible.pointDeVie

        // Ajouter le montant de soin aux points de vie de la cible
        cible.pointDeVie += soin

        // Ajouter une ligne d'inventaire pour enregistrer l'utilisation de la potion de soin
        cible.ajouterLigneInventaire(this, -1)
        // Construire et retourner un message décrivant l'action effectuée
        return "${this.nom} soigne ${cible.nom} pour ${cible.pointDeVie - pvInitiale} PV"
    }

}