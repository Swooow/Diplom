# Инструкция к запуску 
1. Клонировать репозиторий проекта в папку на компьютере через _GIT_ командой:

  `git clone git@github.com:Swooow/Diplom.git`

2. Запустить проект в _IntelliJ IDEA_.
3. Запустить _Docker Desktop_.
4. Запустить контейнеры _MySQL_, _PostgreSQL_ и _NodeJS_, прописав в терминале:

  `docker-compose up -d`

5. Открыв новый терминал, запустить приложение, используя одну из баз данных, командой:

- `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar ./artifacts/aqa-shop.jar` для _MySQL_;

- `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar ./artifacts/aqa-shop.jar` для _PostgreSQL_.

*Примечание:* для смены БД необходимо остановить приложение комбинацией клавиш: `Ctrl + C`, затем ввести нужную команду для запуска приложения (п.5).

6. Для запуска тестов в новой вкладке терминала ввести команду: 

  `.\gradlew clean test`

7. Для формирования отчета AllureReport по результатам тестирования в новой вкладке терминала или, нажав двойной Ctrl, ввести команду:

  `./gradlew allureServe`

8. Сгенерированный отчет откроется в браузере автоматически. 
