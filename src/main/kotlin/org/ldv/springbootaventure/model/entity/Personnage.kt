package org.ldv.springbootaventure.model.entity
import jakarta.persistence.*
@Entity
//implémentation de la classe "personnage"
class Personnage constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", nullable = false)
    var id : Long?,
    var nom: String,
    var attaque : Int,
    var defense: Int,
    var endurance: Int,
    var vitesse: Int,
    var cheminImage: String,


    //Association entre Combat et Personnage
    //plusieurs utilisateurs peuvent être rattaché à un combat
    @ManyToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "combat_id")
    open var combat: Combat? = null,

    //Association entre Campagne et Personnage
    //plusieurs utilisateurs peuvent être rattaché à une campagne
    @ManyToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "campagne_id")
    open var campagne: Campagne? = null,

    //association entre classe "Personnage" et "LigneInventaire"
    @OneToMany(mappedBy = "personnage")
    var ligneInventaire: MutableList<LigneInventaire>? = mutableListOf(),

    //Association entre Personnage et Arme
    //Une arme peut être rattaché à plusieurs utilisateurs
    @ManyToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "arme_id")
    open var arme: Arme? = null,

    //Association entre Personnage et Armure
    //Une armure peut être rattaché à plusieurs utilisateurs
    @ManyToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "armure_id")
    open var armure: Armure? = null,

    //Association entre Personnage et Accessoire
    //Plusieurs personnages peuvent être rattaché à un accessoire
    @ManyToOne
    @JoinColumn(name = "accessoire_id")
    open var accessoire: Accessoire? = null,

    //Association entre Personnage et Utilisateur
    //Plusieurs personnages peuvent être rataché à un utilisateur
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    var utilisateur: Utilisateur? = null,

    ) {
    //attribut point de vie Max
    val pointDeVieMax: Int
        //getter pour cet attribut
        get() = 50 + (10 * (this.endurance))

    //attribut point de vie
    var pointDeVie: Int = this.pointDeVieMax
        //setter pour cet attribut
    set(value) {
        field = minOf(value, this.pointDeVieMax)
    }

    //méthode attaque()
    open fun attaque(adversaire: Personnage):String {
        // Vérifier si le personnage a une arme équipée
        var degats = this.attaque / 2
        if (arme!= null) {
            // Calculer les dégâts en utilisant les attributs du personnage et la méthode calculerDegat de l'arme
            degats += this.arme!!.calculerDegat()
        }
        // Appliquer la défense de l'adversaire (au minimum au moins 1 de dégat)
        val degatsInfliges = maxOf(1, degats - adversaire.calculeDefense())

        // Appliquer les dégâts à l'adversaire
        adversaire.pointDeVie = adversaire.pointDeVie - degatsInfliges

        return("$nom attaque ${adversaire.nom} avec ${arme?.nom ?: "une attaque de base"} et inflige $degatsInfliges points de dégâts.")
    }

    //methode calculeDefense
    open fun calculeDefense(): Int {
        val result = this.defense / 2
        // Calculer les dégâts en utilisant les attributs du personnage et la méthode calculerDegat de l'armure
        if (this.armure != null){
            defense += armure!!.calculProtection()//ajout bonus de l’armure en utilisant la méthode calculProtection()
        }
        return result;   // retourne resultat
    }

    /**
     * Ajoute une ligne d'inventaire pour l'item spécifié avec la quantité donnée.
     * Si une ligne d'inventaire pour cet item existe déjà, met à jour la quantité.
     * Si la quantité résultante est inférieure ou égale à zéro, la ligne d'inventaire est supprimée.
     *
     * @param unItem L'item pour lequel ajouter ou mettre à jour la ligne d'inventaire.
     * @param uneQuantite La quantité à ajouter à la ligne d'inventaire existante ou à la nouvelle ligne.
     */
    fun ajouterLigneInventaire(unItem: Item, uneQuantite: Int) {
        // Chercher une ligne d'inventaire existante pour l'item spécifié
        val ligneItem = this.ligneInventaire!!.find { ligneInventaire -> ligneInventaire.item == unItem }

        // Si aucune ligne d'inventaire n'est trouvée, en créer une nouvelle
        if (ligneItem == null) {
            // Créer un nouvel identifiant pour la ligne d'inventaire
            val ligneInventaireId = ligneInventaireId(this.id!!, unItem.id!!)

            // Ajouter une nouvelle ligne d'inventaire à la liste
            this.ligneInventaire!!.add(LigneInventaire(ligneInventaireId, this, unItem, uneQuantite))
        } else {
            // Si une ligne d'inventaire existante est trouvée, mettre à jour la quantité
            ligneItem.quantite += uneQuantite

            // Si la quantité résultante est inférieure ou égale à zéro, supprimer la ligne d'inventaire
            if (ligneItem.quantite <= 0) {
                this.ligneInventaire!!.remove(ligneItem)
            }
        }
    }
    /**
     * Loot l'inventaire de la cible en transférant les items et équipements dans l'inventaire du looteur.
     *
     * @param cible Le personnage dont l'inventaire sera looted.
     * @return Un message décrivant les items looted et les actions effectuées.
     */
    fun loot(cible: Personnage): String {
        // Déséquiper l'arme et l'armure de la cible
        cible.arme = null
        cible.armure = null
        // Variable pour stocker les messages générés pendant le loot
        var msg = ""
        // Utiliser forEach pour parcourir chaque ligne d'inventaire de la cible
        cible.ligneInventaire!!.forEach { uneLigne ->
            // Ajouter les items et quantités lootés à l'inventaire du looteur
            this.ajouterLigneInventaire(uneLigne.item!!, uneLigne.quantite)

            // Construire un message décrivant l'action pour chaque item looté
            msg += "${this.nom} récupère ${uneLigne.quantite} ${uneLigne.item}\n"
        }
        // Retourner le message global décrivant l'action de loot
        return msg
    }

    /**
     * Vérification si le personnage a une potion dans son inventaire.
     *
     * @return true si le personnage a une potion, false sinon.
     */
    fun aUnePotion(): Boolean {
        // Utiliser la méthode any pour vérifier si une ligne d'inventaire contient une Potion
        return this.ligneInventaire!!.any { ligneInventaire -> ligneInventaire.item is Potion }
    }


    // Methode pour équiper le personnage d'une arme
    open fun equipe(arme: Arme) { //prend en paramètre une arme.
        val premierArme = ligneInventaire?.find {}
        if (premierArme) { //vérifie si l’arme (paramètre) est dans l’inventaire du personnage
            this.arme = arme //alors elle devient l’arme principale

        }
        return("${this.nom} equipe ${this.arme}")

    }
    // Methode pour équiper le personnage d'une armure
    fun equipe(armure: Armure) {
        if(armure in ligneInventaire?.find {}){
            this.armure = armure
        }
//        for (item in ligneInventaire) {
//            if (item is Armure) {
//                if (armure == item) {
//                    this.armure = armure
//                }
//
//            }
//        }
        println("${this.nom} equipe ${this.armure}")
    }
    // Methode pour équiper le personnage d'un accessoire
    open fun equipe(accessoire: Accessoire) { //prend en paramètre une arme.
        if (accessoire in ligneInventaire!!.any {Accessoire}) { //vérifie si l’arme (paramètre) est dans l’inventaire du personnage
            armePrincipal == arme //alors elle devient l’arme principale
        }
        println("${this.nom} equipe ${this.armePrincipal}")

    }
}

