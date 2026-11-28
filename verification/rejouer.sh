#!/usr/bin/env bash
set -euo pipefail

BASE="$(cd "$(dirname "$0")" && pwd)"
PROJET="$(dirname "$BASE")"

CLASSPATH="$(cygpath -w "$PROJET/target/classes");$(cygpath -w "$PROJET/target/dependency")/*"

EXECUTION="$(mktemp -d)"

trap 'rm -rf "$EXECUTION"' EXIT

cd "$EXECUTION"

ECHECS=0

for SCENARIO in "$BASE"/scenarios-maven/*.txt; do
    NOM="$(basename "$SCENARIO")"

    if [[ "$NOM" == "06-rechargement.txt" ]]; then
        continue
    fi

    rm -f data/taches.csv

    java -Dstdout.encoding=UTF-8 -cp "$CLASSPATH" ca.inf1763.equipetmp.TaskFlow \
        < "$SCENARIO" > "$NOM"

    if diff -u "$BASE/references/$NOM" "$NOM"; then
        echo "[OK] $NOM"
    else
        echo "[ECHEC] $NOM"
        ECHECS=$((ECHECS + 1))
    fi

    if [[ "$NOM" == "06-sauvegarde.txt" ]]; then
        NOM="06-rechargement.txt"

        java -Dstdout.encoding=UTF-8 -cp "$CLASSPATH" ca.inf1763.equipetmp.TaskFlow \
            < "$BASE/scenarios-maven/$NOM" > "$NOM"

        if diff -u "$BASE/references/$NOM" "$NOM"; then
            echo "[OK] $NOM"
        else
            echo "[ECHEC] $NOM"
            ECHECS=$((ECHECS + 1))
        fi
    fi
done

echo "------------------------------"

if [[ "$ECHECS" -eq 0 ]]; then
    echo "Tous les scenarios sont valides."
else
    echo "$ECHECS verification(s) en echec."
    exit 1
fi