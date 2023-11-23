package org.ldv.springbootaventure.controller.admin

import org.ldv.springbootaventure.model.dao.AccessoireDAO
import org.ldv.springbootaventure.model.dao.QualiteDAO
import org.ldv.springbootaventure.model.dao.TypeAccessoireDAO
import org.ldv.springbootaventure.model.entity.Accessoire
import org.ldv.springbootaventure.model.entity.Qualite
import org.ldv.springbootaventure.model.entity.TypeAccessoire
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class AccessoireController (val accessoireDAO : AccessoireDAO, val qualiteDAO: QualiteDAO, val typeAccessoireDAO: TypeAccessoireDAO){
    /**
     * Affiche la liste de tous les accessoires.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/admin/Accessoire")
    fun index(model: Model): String {
        // Récupère tous les accessoires depuis la base de données
        val accessoires = this.accessoireDAO.findAll()

        // Ajoute la liste des accessoires au modèle pour affichage dans la vue
        model.addAttribute("accessoires", accessoires)

        // Retourne le nom de la vue à afficher
        return "admin/Accessoire/index"
    }

    /**
     * Affiche les détails d'un accessoire en particulier.
     *
     * @param id L'identifiant unique de l'accessoire à afficher.
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher.
     * @throws NoSuchElementException si l'accessoire avec l'ID spécifié n'est pas trouvée.
     */
    @GetMapping("/admin/Accessoire/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère l'accessoire avec l'ID spécifié depuis la base de données
        val unAccessoire = this.accessoireDAO.findById(id).orElseThrow()

        // Ajoute l'accessoire au modèle pour affichage dans la vue
        model.addAttribute("accessoires", unAccessoire)

        // Retourne le nom de la vue à afficher
        return "admin/Accessoire/show"
    }
    /**
     * Affiche le formulaire de création d'un nouvel accessoire.
     *
     * @param model Le modèle utilisé pour transmettre les données à la vue.
     * @return Le nom de la vue à afficher (le formulaire de création).
     */
    @GetMapping("/admin/Accessoire/create")
    fun create(model: Model): String {
        // Crée une nouvelle instance de Accessoire avec des valeurs par défaut
        val nouvelAccessoire = Accessoire(0,"","","",null,null)
        // Récupère les valeurs de Qualite
        val lesQualites = qualiteDAO.findAll()
        // Récupère les valeurs de TypeAccessoire
        val lesTypeAccessoire = typeAccessoireDAO.findAll()
        // Ajoute le nouvel accessoire au modèle pour affichage dans le formulaire de création
        model.addAttribute("nouvelAccessoire", nouvelAccessoire)
        // Ajoute les valeurs de Qualite au nouvel accessoire
        model.addAttribute("qualites",lesQualites)
        // Ajoute les valeurs de TypeAccessoire au nouvel accessoire
        model.addAttribute("typeAccessoires",lesTypeAccessoire)
        // Retourne le nom de la vue à afficher (le formulaire de création)
        return "admin/Accessoire/create"
    }
    /**
     * Gère la soumission du formulaire d'ajout de l'accessoire.
     *
     * @param nouvelAccessoire L'objet Accessoire créé à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des accessoires après l'ajout réussi.
     */
    @PostMapping("/admin/Accessoire")
    fun store(@ModelAttribute nouvelAccessoire : Accessoire, redirectAttributes: RedirectAttributes): String {
        // Sauvegarde le nouveau type d'arme dans la base de données
        val savedAccessoire = this.accessoireDAO.save(nouvelAccessoire)
        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Enregistrement de ${savedAccessoire.nom} réussi")
        // Redirige vers la page d'administration des types d'arme
        return "redirect:/admin/Accessoire"
    }



    @GetMapping("/admin/Accessoire/{id}/edit")
    fun edit(@PathVariable id: Long, model: Model): String {
        // Récupère l'accessoire avec l'ID spécifié depuis la base de données
        val unAccessoire = this.accessoireDAO.findById(id).orElseThrow()

        // Ajoute l'accessoire au modèle pour affichage dans la vue
        model.addAttribute("accessoires", unAccessoire)

        // Retourne le nom de la vue à afficher
        return "admin/Accessoire/update"
    }
    /**
     * Gère la soumission du formulaire de mise à jour de qualité.
     *
     * @param Accessoire L'objet Accessoire mis à jour à partir des données du formulaire.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des accessoires après la mise à jour réussie.
     * @throws NoSuchElementException si l'accessoire avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/Accessoire/update")
    fun update(@ModelAttribute accessoire: Accessoire, model : Model, redirectAttributes: RedirectAttributes): String {
        // Recherche l'accessoire existante dans la base de données
        val accessoireModifier = this.accessoireDAO.findById(accessoire.id ?: 0).orElseThrow()

        // Mise à jour des propriétés d'accessoire avec les nouvelles valeurs du formulaire
        accessoireModifier.nom = accessoire.nom
        accessoireModifier.description = accessoire.description
        accessoireModifier.cheminImage = accessoire.cheminImage

        // Récupère le qualite d'un objet "accessoire" depuis la base de données et le met à jour dans un autre objet "accessoireModifier"
        // Autre possibilité => accessoireModifier.qualite=accessoire.qualite
        val laQualite = qualiteDAO.findById(accessoire.qualite!!.id ?: 0).orElseThrow()
        // Ajoute les valeurs de Qualite au nouvel accessoire
        model.addAttribute("qualites",laQualite)
        accessoireModifier.qualite = laQualite

        // Récupère le type d'accessoire d'un objet "accessoire" depuis la base de données et le met à jour dans un autre objet "accessoireModifier"
        // Autre possibilité => accessoireModifier.qualite=accessoire.qualite
        val leTypeAccessoire = typeAccessoireDAO.findById(accessoire.typeAccessoire!!.id ?: 0).orElseThrow()
        accessoireModifier.typeAccessoire = leTypeAccessoire
        // Ajoute les valeurs de TypeAccessoire au nouvel accessoire
        model.addAttribute("typeAccessoires",leTypeAccessoire)

        // Sauvegarde l'accessoire modifiée dans la base de données
        val savedAccessoire = this.accessoireDAO.save(accessoireModifier)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Modification de ${savedAccessoire.nom} réussie")

        // Redirige vers la page d'administration des accessoires
        return "redirect:/admin/Accessoire"
    }

    /**Voici la méthode delete du contrôleur :
     * Gère la suppression d'un accessoire par son identifiant.
     *
     * @param id L'identifiant d'accessoire à supprimer.
     * @param redirectAttributes Les attributs de redirection pour transmettre des messages à la vue suivante.
     * @return La redirection vers la page d'administration des accessoires après la suppression réussie.
     * @throws NoSuchElementException si l'accessoire avec l'ID spécifié n'est pas trouvée dans la base de données.
     */
    @PostMapping("/admin/Accessoire/delete")
    fun delete(@RequestParam id: Long, redirectAttributes: RedirectAttributes): String {
        // Recherche l'accessoire à supprimer dans la base de données
        val accessoire: Accessoire = this.accessoireDAO.findById(id).orElseThrow()

        // Suppression d'accessoire de la base de données
        this.accessoireDAO.delete(accessoire)

        // Ajoute un message de succès pour être affiché à la vue suivante
        redirectAttributes.addFlashAttribute("msgSuccess", "Suppression de ${accessoire.nom} réussie")

        // Redirige vers la page d'administration des types d'accessoire
        return "redirect:/admin/Accessoire"
    }
}