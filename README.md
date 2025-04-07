## Порядок запуска Дипломного проекта профессии "Тестировщик". 
### Необходимое программное обеспечение
* Операционная система: Windows 11 23H2
* Браузер Google Chrome Версия 134.0.6998.178 (Официальная сборка), (64 бит)
* IntelliJ IDEA 2024.3.5 (Community Edition)
* Java: OpenJDK 11
* Docker Desktop version: 4.39.0 (184744)

# Запуск контейнера

**1. Запустить Docker Desktop**

**2. Запустить контейнеры СУБД MySQl, PostgerSQL и Node.js командой в терминале:**

```
docker compose up
```
**3. Запустить  SUT командой в терминале:**

**MySQL:**

```
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar ./artifacts/aqa-shop.jar
```

**PostgreSQL:**

```
java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar ./artifacts/aqa-shop.jar
```
После этого приложение должно быть доступно по адресу:
  http://localhost:8080/

**Запуск автотестов. Генерация отчета Allure**
* Запустить автотесты командой в терминале:

**MySQL:**

```
./gradlew clean test
```
**PostgreSQL:**

```
./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
```
* Генерацию отчета Allure и их автоматического открытия в браузере командой в терминале:

```
./gradlew allureServe
```
После завершения выполнения автотестов и формирования отчёта:

Завершите просмотр отчёта, нажав Ctrl + C, затем подтвердите выход, нажав Y и Enter в терминале.

Остановите работу приложения, используя сочетание клавиш Ctrl + C в терминале, где оно было запущено.

Завершите работу контейнеров с помощью команды:
```
docker compose down
```
