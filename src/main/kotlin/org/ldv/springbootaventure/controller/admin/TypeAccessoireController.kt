package org.ldv.springbootaventure.controller.admin

import org.ldv.springbootaventure.model.dao.QualiteDAO
import org.ldv.springbootaventure.model.dao.TypeAccessoireDAO
import org.ldv.springbootaventure.model.entity.Qualite
import org.ldv.springbootaventure.model.entity.TypeAccessoire
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class TypeAccessoireController (val typeAccessoireDAO : TypeAccessoireDAO){
    /**
     * Affiche la liste de tous les accessoires.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/admin/TypeAccessoire")
    fun index(model: Model): String {
        // Récupère tous les types d'accessoires depuis la base de données
        val accessoires = this.typeAccessoireDAO.findAll()

        // Ajoute la liste des types d'accessoires au modèle pour affichage dans la vue
        model.addAttribute("accessoires", accessoires)

        // Retourne le nom de la vue à afficher
        return "admin/TypeAccessoire/index"
    }

    /**
     * Affiche les détails d'un type d'accessoire en particulier.
     *
     * @param id L'identifiant unique du type d'accessoire à afficher.
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée.
     */
    @GetMapping("/admin/TypeAccessoire/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère le type d'accessoire avec l'ID spécifié depuis la base de données
        val unTypeAccessoire = this.typeAccessoireDAO.findById(id).orElseThrow()

        // Ajoute le type d'accessoire au modèle pour affichage dans la vue
        model.addAttribute("accessoire", unTypeAccessoire)

        // Retourne le nom de la vue à afficher
        return "admin/TypeAccessoire/show"
    }
    /**
     * Affiche le formulaire de création d'un nouveau type d'accessoire.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher (le formulaire de création).
     */
    @GetMapping("/admin/TypeAccessoire/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance de TypeAccessoire avec des valeurs par défaut
        val nouveauTypeAccessoire = TypeAccessoire(null, "", "")

        // Ajoute le nouveau type d'accessoire au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouveauTypeAccessoire", nouveauTypeAccessoire)

        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/TypeAccessoire/create"
    }
    /**
     * Gère la soumission du formulaire d'ajout d'un type d'accessoire.
     *
     * @param nouveauTypeAccessoire L'objet TypeAccessoire créé à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des types d'accessoires après l'ajout réussi.
     */
    @PostMapping("/admin/TypeAccessoire")
    fun store(@ModelAttribute nouveauTypeAccessoire : TypeAccessoire, redirectAttributes: RedirectAttributes): String {
        // Sauvegarde le nouveau type d'arme dans la base de données
        val savedTypeAccessoire = this.typeAccessoireDAO.save(nouveauTypeAccessoire)
        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedTypeAccessoire.nom} réussi")
        // Redirige vers la page d'administration des types d'arme
        return "redirect:/admin/TypeAccessoire"
    }



    @GetMapping("/admin/TypeAccessoire/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        // Récupère le type d'accessoire avec l'ID spécifié depuis la base de données
        val unTypeAccessoire = this.typeAccessoireDAO.findById(id).orElseThrow()

        // Ajoute le type d'accessoire au modèle pour affichage dans la vue
        model.addAttribute("accessoire", unTypeAccessoire)

        // Retourne le nom de la vue à afficher
        return "admin/TypeAccessoire/update"
    }
    /**
     * Gère la soumission du formulaire de mise à jour de qualité.
     *
     * @param TypeAccessoire L'objet TypeAccessoire mis à jour à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des types d'accessoire après la mise à jour réussie.
     * @throws NoSuchElementException si le type d'accessoire avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/TypeAccessoire/update")
    fun update(@ModelAttribute typeAccessoire: TypeAccessoire, redirectAttributes: RedirectAttributes): String {
        // Recherche le type d'accessoire existante dans la base de données
        val typeAccessoireModifier = this.typeAccessoireDAO.findById(typeAccessoire.id ?: 0).orElseThrow()

        // Mise à jour des propriétés de type d'accessoire avec les nouvelles valeurs du formulaire
        typeAccessoireModifier.nom = typeAccessoire.nom
        typeAccessoireModifier.typeBonus = typeAccessoire.typeBonus

        // Sauvegarde le type d'accessoire modifiée dans la base de données
        val savedTypeAccessoire = this.typeAccessoireDAO.save(typeAccessoireModifier)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedTypeAccessoire.nom} réussie")

        // Redirige vers la page d'administration des types d'accessoire
        return "redirect:/admin/TypeAccessoire"
    }

    /**Voici la méthode delete du contrôleur :
     * Gère la suppression d'une qualité par son identifiant.
     *
     * @param id L'identifiant du type accessoire à supprimer.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des types d'accessoire après la suppression réussie.
     * @throws NoSuchElementException si le type d'accessoire avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/TypeAccessoire/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        // Recherche le type d'accessoire à supprimer dans la base de données
        val typeAccessoire: TypeAccessoire = this.typeAccessoireDAO.findById(id).orElseThrow()

        // Suppression du type d'accessoire de la base de données
        this.typeAccessoireDAO.delete(typeAccessoire)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${typeAccessoire.nom} réussie")

        // Redirige vers la page d'administration des types d'accessoire
        return "redirect:/admin/TypeAccessoire"
    }
}