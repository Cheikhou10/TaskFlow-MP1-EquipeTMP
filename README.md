# TaskFlow — Mini-projet 1

Application Java en ligne de commande pour gérer une liste de tâches.

## Équipe
- Aminata Samb
- Mame Faty Gueye
- Ndiaye Fatimata
- Fatou Diakhaté
- Cheikh Omar Diouf

## Fonctionnalités implémentées
- Ajouter une tâche avec un titre et une priorité.
- Marquer une tâche comme terminée.
- Signaler un identifiant inconnu lors du marquage ou du changement de priorité.
- Modifier la priorité d'une tâche.

## Fonctionnalités à compléter
- Afficher la liste des tâches.
- Attribuer, modifier et retirer une échéance.
- Sauvegarder et recharger les données.

## Prérequis
- JDK 17 ou une version compatible avec le projet.
- Git.

## Compilation

```bash
javac -encoding UTF-8 src/*.java
```

## Exécution

```bash
java -cp src TaskFlow
```

## Structure du projet
- `src/` : code source Java.
- `docs/` : documentation sur l'organisation et les vérifications.
- `CONTRIBUTING.md` : règles de collaboration.

## Limites connues
L'affichage des tâches, la gestion des échéances, la sauvegarde et le rechargement des données restent à implémenter.