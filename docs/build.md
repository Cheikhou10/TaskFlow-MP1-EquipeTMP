# Documentation de la construction du projet TaskFlow

## D.1 - Configuration du JAR executable

### Objectif

Generer un fichier JAR executable contenant toutes les dependances necessaires au fonctionnement de TaskFlow.

### Plugin Maven Shade

Le plugin `maven-shade-plugin`, version 3.6.1, a ete ajoute dans le fichier `pom.xml`.

Ce plugin permet de :

- Regrouper les classes du projet et ses dependances.
- Definir la classe principale `ca.inf1763.equipetmp.TaskFlow`.
- Fusionner les ressources de services.
- Generer un JAR executable autonome.

### Construction du projet

La commande suivante permet de compiler et de construire le projet :

```bash
./mvnw clean package
```

Le plugin Maven Shade est execute pendant la phase `package` du cycle de vie Maven.

### Fichiers generes

Deux fichiers JAR sont generes dans le dossier `target/` :

- `taskflow-1.1.0-SNAPSHOT.jar` : JAR standard.
- `taskflow-1.1.0-SNAPSHOT-all.jar` : JAR executable contenant toutes les dependances.

### Execution du programme

```bash
java -jar target/taskflow-1.1.0-SNAPSHOT-all.jar
```

Java 21 est necessaire pour executer le programme. Une installation de Maven n'est pas necessaire pour lancer le JAR.

### Verification

Le lancement du programme et l'ajout d'une tache ont ete verifies avec succes.

La verification finale de l'exportation Excel et du fonctionnement dans un environnement isole reste a confirmer.

### Conclusion

La configuration de Maven Shade permet de produire un JAR executable autonome et facilite la distribution de TaskFlow.