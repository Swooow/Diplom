# Инструкция к запуску 
1. Клонировать репозиторий проекта в папку на компьютере через GIT командой:
` git clone git@github.com:Swooow/Diplom.git `
2. Запустить проект в IntelliJ IDEA.
3. Запустить Docker Desktop.
4. Запустить контейнеры MySQL, PostgreSQL и NodeJS, прописав в терминале:
`docker-compose up -d`
5. Открыв новый терминал, запустить приложение, используя одну из баз данных, командой:

5.1. `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar ./artifacts/aqa-shop.jar` для MySQL

5.2. `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar ./artifacts/aqa-shop.jar` для PostgreSQL

*Примечание:* для смены БД необходимо остановить приложение комбинацией клавиш: `Ctrl + C`, затем ввести нужную команду для запуска приложения (п.5).

