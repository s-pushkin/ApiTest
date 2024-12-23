FROM openjdk:11-jdk-slim

ENV ALLURE_VERSION=2.13.8

RUN apt-get update && apt-get install -y wget unzip && rm -rf /var/lib/apt/lists/*

RUN wget --no-verbose -O /tmp/allure-2.13.8.zip \
    https://github.com/allure-framework/allure2/releases/download/2.13.8/allure-2.13.8.zip && \
unzip /tmp/allure-2.13.8.zip -d /opt/ \
    && rm -rf /tmp/*

ENV PATH="/opt/allure-2.13.8/bin:${PATH}"

RUN apt-get update && apt-get install -y \
    curl \
    git \
    ca-certificates \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY . .

RUN wget https://services.gradle.org/distributions/gradle-7.3.3-bin.zip -P /tmp \
    && unzip /tmp/gradle-7.3.3-bin.zip -d /opt \
    && rm -f /tmp/gradle-7.3.3-bin.zip

ENV GRADLE_HOME=/opt/gradle-7.3.3
ENV PATH=${GRADLE_HOME}/bin:${PATH}

CMD ["gradle", "test"]
