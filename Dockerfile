FROM openjdk:11-jdk-slim


# Устанавливаем переменную для версии Allure
ENV ALLURE_VERSION=2.13.8

# Устанавливаем curl для скачивания Allure
RUN apt-get update && apt-get install -y wget unzip && rm -rf /var/lib/apt/lists/*

# Скачиваем Allure
RUN wget --no-verbose -O /tmp/allure-$ALLURE_VERSION.zip \
    https://github.com/allure-framework/allure2/releases/download/$ALLURE_VERSION/allure-commandline-$ALLURE_VERSION.zip \
    && unzip /tmp/allure-$ALLURE_VERSION.zip -d /opt/ \
    && rm -rf /tmp/*

# Устанавливаем Allure в PATH
ENV PATH="/opt/allure-${ALLURE_VERSION}/bin:${PATH}"

# Устанавливаем зависимости для работы с Java и Gradle
RUN apt-get update && apt-get install -y \
    curl \
    git \
    ca-certificates \
    && rm -rf /var/lib/apt/lists/*

# Копируем исходный код и устанавливаем зависимости
WORKDIR /app
COPY . .

# Устанавливаем Gradle
RUN wget https://services.gradle.org/distributions/gradle-7.3.3-bin.zip -P /tmp \
    && unzip /tmp/gradle-7.3.3-bin.zip -d /opt \
    && rm -f /tmp/gradle-7.3.3-bin.zip

# Устанавливаем путь к Gradle
ENV GRADLE_HOME=/opt/gradle-7.3.3
ENV PATH=${GRADLE_HOME}/bin:${PATH}

# По умолчанию запускаем Gradle с тестами
CMD ["gradle", "test"]