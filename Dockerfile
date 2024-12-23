# Используем официальный образ с JDK (например, OpenJDK 17)
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем файлы проекта в контейнер
COPY . /app

# Устанавливаем wget и unzip для загрузки Allure
RUN apt-get update && apt-get install -y wget unzip

# Устанавливаем версию Allure (можно изменить на актуальную версию)
ENV ALLURE_VERSION=2.14.0

# Скачиваем Allure командную строку и распаковываем
RUN wget --no-verbose -O /tmp/allure-$ALLURE_VERSION.zip https://github.com/allure-framework/allure2/releases/download/$ALLURE_VERSION/allure-commandline-$ALLURE_VERSION.zip \
    && unzip /tmp/allure-$ALLURE_VERSION.zip -d /opt/ \
    && rm -rf /tmp/*

# Добавляем Allure в PATH
ENV PATH="/opt/allure-$ALLURE_VERSION/bin:$PATH"

# Устанавливаем Gradle (если еще не установлен)
RUN apt-get install -y curl && curl -s https://get.sdkman.io | bash && \
    source "$HOME/.sdkman/bin/sdkman-init.sh" && \
    sdk install gradle

# Запускаем сборку проекта с Gradle
RUN ./gradlew build

# Открываем порт для отчета (если необходимо)
EXPOSE 8080

# Генерация отчета Allure после выполнения тестов
CMD allure serve build/allure-results
