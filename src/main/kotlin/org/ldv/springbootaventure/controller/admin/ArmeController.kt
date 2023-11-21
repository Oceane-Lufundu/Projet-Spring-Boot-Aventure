package org.ldv.springbootaventure.controller.admin


import org.ldv.springbootaventure.model.dao.ArmeDAO
import org.ldv.springbootaventure.model.entity.Arme
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
@Controller
class ArmeController (val armeDAO : ArmeDAO) {
    /**
     * Fonction qui affiche la liste de tous les armes.
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/admin/Arme")
    fun index(model: Model): String {
        // Récupère toutes les armes depuis la base de données
        val arme = this.armeDAO.findAll()
        // Ajoute la liste des armes au modèle pour affichage dans la vue
        model.addAttribute("arme", arme)
        // Retourne le nom de la vue à afficher
        return "admin/Arme/index"
    }

    /**
     * Affiche les détails d'une arme en particulier.
     *
     * @param id L'identifiant unique d'une arme à afficher.
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     * @throws NoSuchElementException si la qualité avec l'ID spécifié n'est pas trouvée.
     */
    @GetMapping("/admin/Arme/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère le type d'arme avec l'ID spécifié depuis la base de données
        val arme = this.armeDAO.findById(id).orElseThrow()
        // Ajoute le type d'arme au modèle pour affichage dans la vue
        model.addAttribute("arme", arme)
        // Retourne le nom de la vue à afficher
        return "admin/Arme/show"
    }


    /**
     * Affiche le formulaire de création d'une nouvelle arme.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher (le formulaire de création).
     */
    @GetMapping("/admin/Arme/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance d'Arme avec des valeurs par défaut
        val nouvelleArme = Arme(null, null, null, null,null,null)
        // Ajoute la nouvelle arme au modèle pour affichage dans le formulaire de création
        model.addAttribute("arme", nouvelleArme)
        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/Arme/create"
    }


    /**
     * Gère la soumission du formulaire d'ajout d'un type d'arme
     * @param nouvelleArme L'objet Arme créé à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des types d'accessoires après l'ajout réussi.
     */
    @PostMapping("/admin/Arme")
    fun store(@ModelAttribute nouvelleArme: Arme, redirectAttributes: RedirectAttributes): String {
        // Sauvegarde la nouvelle arme dans la base de données
        val savedArme = this.armeDAO.save(nouvelleArme)
        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedArme.nom} réussi")
        // Redirige vers la page d'administration des armes
        return "redirect:/admin/Arme"
    }

    @GetMapping("/admin/Arme/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        // Récupère l'arme avec l'ID spécifié depuis la base de données
        val uneArme = this.armeDAO.findById(id).orElseThrow()

        // Ajoute l'arme au modèle pour affichage dans la vue
        model.addAttribute("arme", uneArme)

        // Retourne le nom de la vue à afficher
        return "admin/Arme/update"
    }

 /**
     * Gère la soumission du formulaire de mise à jour de qualité.
     *
     * @param Arme L'objet Arme mis à jour à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des armes après la mise à jour réussie.
     * @throws NoSuchElementException si l'arme avec l'ID spécifié n'est pas trouvée dans la base de données.
     */

 @PostMapping("/admin/Arme/update")
    fun update(@ModelAttribute arme : Arme, redirectAttributes: RedirectAttributes): String {
        // Recherche l'arme existante dans la base de données
        val armeModifier:Arme = this.armeDAO.findById(arme.id ?: 0).orElseThrow()

        // Mise à jour des propriétés de l'arme avec les nouvelles valeurs du formulaire
        armeModifier.nom = arme.nom
        armeModifier.description = arme.description
        armeModifier.cheminImage = arme.cheminImage
        armeModifier.qualite = arme.qualite
        armeModifier.typeArme = arme.typeArme

        // Sauvegarde l'arme modifiée dans la base de données
        val savedArme = this.armeDAO.save(armeModifier)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedArme.nom} réussie")

        // Redirige vers la page d'administration des armes
        return "redirect:/admin/Arme"
    }


    /**Voici la méthode delete du contrôleur :
     * Gère la suppression d'une arme par son identifiant.
     *
     * @param id L'identifiant de l'arme à supprimer.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des armes après la suppression réussie.
     * @throws NoSuchElementException si l'arme avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/Arme/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        // Recherche le type d'arme à supprimer dans la base de données
        val arme: Arme = this.armeDAO.findById(id).orElseThrow()

        // Suppression de l'arme de la base de données
        this.armeDAO.delete(arme)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${arme.nom} réussie")

        // Redirige vers la page d'administration des types d'arme
        return "redirect:/admin/Arme"
    }
}

