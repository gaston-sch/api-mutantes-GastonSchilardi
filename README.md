# 🧬 ApiMutantes | Detector de Anomalías Genéticas

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Coverage](https://img.shields.io/badge/Coverage-87%25-brightgreen?style=for-the-badge)
## 📖 Sobre el Proyecto

Este proyecto es una **API REST de alto rendimiento** desarrollada para detectar si una secuencia de ADN pertenece a un mutante o a un humano. El algoritmo analiza una matriz de `NxN` caracteres buscando secuencias idénticas de 4 letras en direcciones **horizontal, vertical y oblicua**.

El sistema está optimizado para manejar altos volúmenes de tráfico mediante:
* **Early Termination:** El algoritmo se detiene inmediatamente al encontrar la condición de mutante.
* **Caché & Hash:** Se almacena el hash del ADN para evitar re-analizar secuencias ya verificadas (Búsqueda O(1)).

---

## 🔗 Enlaces del Proyecto

* ☁️ **API en Producción (Render):** [Render](https://api-mutantes-gastonschilardi.onrender.com) [Consola h2](https://api-mutantes-gastonschilardi.onrender.com/h2-console)
* 📄 **Documentación Swagger:** [Swagger](https://api-mutantes-gastonschilardi.onrender.com/swagger-ui.html)
* 🐙 **Repositorio GitHub:** [Git](https://github.com/gaston-sch/api-mutantes-GastonSchilardi.git)

---

## 🚀 Tecnologías Utilizadas

* **Java 17** (LTS)
* **Spring Boot 3.2.0**
* **Docker** (Containerización y despliegue)
* **H2 Database** (Base de datos en memoria optimizada)
* **Gradle** (Gestor de construcción)
* **JUnit 5 & Mockito** (Testing unitario y de integración)
* **Lombok** (Reducción de código repetitivo)

---

## 🛠️ Instrucciones de Ejecución

### Prerrequisitos
* Tener instalado **Java 17** o superior.
* Tener **Docker Desktop** instalado (opcional, para ejecución en contenedor).

### 🧱 Opción A: Ejecución con Docker (Recomendada)
Esta opción asegura que el entorno sea idéntico al de producción.

1. **Construir la imagen:**
   ```bash
   docker build -t apimutantes .
   
2. **Ejecutar el contenedor**

Se mapea el puerto 8081 de tu PC al 9090 del contenedor.

docker run -p 8081:9090 -e SPRING_H2_CONSOLE_SETTINGS_WEB_ALLOW_OTHERS=true apimutantes
Acceso

🌐 API: http://localhost:8081

📘 Swagger: http://localhost:8081/swagger-ui.html

🗄️ H2 Console: http://localhost:8081/h2-console

💻 Opción B: Ejecución Local

Abrir una terminal en la raíz del proyecto y ejecutar:

# Windows
./gradlew.bat bootRun

# Mac/Linux
./gradlew bootRun

Acceso

API: http://localhost:9090

Swagger: http://localhost:9090/swagger-ui.html

H2 Console: http://localhost:9090/h2-console

📡 Uso de la API (Endpoints)

Puedes probar visualmente usando Swagger UI según tu método de ejecución.

1️⃣ Detectar Mutante

URL: /mutant
Método: POST

Response Codes

200 OK → Es Mutante

403 Forbidden → Es Humano

Ejemplo de Body
{
  "dna": [
    "ATGCGA",
    "CAGTGC",
    "TTATGT",
    "AGAAGG",
    "CCCCTA",
    "TCACTG"
  ]
}


2️⃣ Estadísticas

URL: /stats
Método: GET

Ejemplo de respuesta
{
  "count_mutant_dna": 40,
  "count_human_dna": 100,
  "ratio": 0.4
}


3️⃣ Health Check

URL: /actuator/health
Método: GET

{"status": "UP"}


💾 Base de Datos (H2 Console)

URL de acceso

Docker → http://localhost:8081/h2-console

Local → http://localhost:9090/h2-console

Configuración

JDBC URL: jdbc:h2:mem:testdb
User: SA
Password: (vacío)

Tabla principal

DNA_RECORD

Consulta recomendada
SELECT * FROM DNA_RECORD;


🧪 Testing y Cobertura

El proyecto supera el 80% de cobertura con pruebas unitarias e integración.

Ejecutar tests
./gradlew test

Generar reporte de cobertura (JaCoCo)
./gradlew jacocoTestReport

Reporte HTML
build/reports/jacoco/test/html/index.html