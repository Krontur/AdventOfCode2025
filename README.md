# AdventOfCode2025

Repositorio con soluciones para los ejercicios de Advent of Code 2025. Inspirado en https://adventofcode.com/ y organizado como proyecto Java/Maven para facilitar ejecución, pruebas y extensión.

## Estado
- Lenguaje: Java
- Build: Maven
- IDE recomendado: IntelliJ IDEA
- Estado: en progreso (cada día tiene su propio paquete y entrada)

## Enlace
- Sitio oficial: https://adventofcode.com/
- Repositorio: https://github.com/Krontur/AdventOfCode2025

## Requisitos
- JDK 17\+ (o la versión requerida por tu entorno)
- Maven 3.6\+ (o superior)
- IntelliJ IDEA (opcional, recomendado para desarrollo)

## Cómo ejecutar (línea de comandos)
Clona el repositorio:

```
   git clone https://github.com/Krontur/AdventOfCode2025.git 
   cd AdventOfCode2025
```

## Estructura del proyecto (resumen)
- `pom.xml` — configuración Maven.
- `src/main/java` — código fuente principal.
    - `com/krontur/adventofcode2025/` — paquete raíz.
    - `com/krontur/adventofcode2025/day1_secret_entrance/` — ejemplo de día con `SecretEntranceDecoder.java`, `input.txt`, `testinput.txt`.
- `src/main/resources` — recursos del proyecto (opcional).
- `src/test/java` — tests unitarios.
- `target/` — artefactos generados por Maven.

Archivos de entrada:
- Rutas de ejemplo:
    - `src/main/java/com/krontur/adventofcode2025/day1_secret_entrance/input.txt`
    - `src/main/java/com/krontur/adventofcode2025/day1_secret_entrance/testinput.txt`

## Convenciones
- Cada día en su propio paquete / clase.
- Entradas de ejemplo en `testinput.txt` y entradas reales en `input.txt`.
- Resolver el reto en una clase por día.
- Añadir tests unitarios que cubran los ejemplos.

## Contacto
- Autor / repositorio: `https://github.com/Krontur/AdventOfCode2025`
