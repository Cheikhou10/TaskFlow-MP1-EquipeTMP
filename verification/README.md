# Filet de securite - TaskFlow

## 1. Objectif

Le filet de securite permet de verifier que les refactorings ne modifient pas le comportement observable de l'application TaskFlow.

## 2. Scenarios de verification

| Scenario | Fonctionnalite verifiee |
|---|---|
| 01 | Affichage d'une liste vide |
| 02 | Ajout et affichage d'une tache |
| 03 | Modification de la priorite |
| 04 | Ajout et retrait d'une echeance |
| 05 | Marquage d'une tache comme terminee |
| 06 | Sauvegarde et rechargement des donnees |
| 07 | Gestion des saisies invalides |

## 3. Organisation des fichiers

- `scenarios/` : fichiers contenant les saisies utilisateur.
- `references/` : resultats de reference obtenus lors de l'execution.
- `rejouer.sh` : script permettant de rejouer les scenarios et de comparer les resultats.

## 4. Version de reference

L'etiquette `v1.0.0` correspond a la version initiale du mini-projet 1. Cependant, cette version presente huit erreurs de compilation dans le fichier `TaskFlow.java`, notamment des traces de conflits de fusion.

Le commit `5825d5e8322012eb271a8897db46eb4834593042`, intitule "Corriger et restaurer le menu principal TaskFlow", a donc ete utilise comme base de reference provisoire.

Le fichier `TestUS03.java` a ete exclu de la compilation, car il appelle une methode inexistante.

Ce choix reste soumis a la validation de l'equipe et du professeur. L'etiquette originale `v1.0.0` n'a pas ete modifiee.

## 5. Execution des verifications

Sur la version de reference provisoire, compiler les classes principales :

`javac -encoding UTF-8 -d out src/TaskFlow.java src/GestionnaireTaches.java src/Tache.java`

Puis executer le script :

`bash verification/rejouer.sh`

Le script compare les resultats obtenus aux references a l'aide de la commande `diff`. Chaque verification reussie est signalee par `[OK]`.

Les scenarios sont executes dans un dossier temporaire afin de ne pas modifier les donnees reelles de l'application.

## 6. Critere de reussite

Les huit fichiers de resultats correspondant aux sept scenarios doivent etre identiques aux fichiers de reference. Toute difference est signalee comme un echec de verification.