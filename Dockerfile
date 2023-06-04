# Docker Imageとしてadoptopenjdk11を使用
FROM adoptopenjdk:11-jdk-hotspot

# git などのインストール
RUN apt-get update && apt-get install -y \
       wget tar iproute2 git

# mavenのインストール
RUN apt-get install -y maven

# PJのコピー
RUN git clone https://github.com/MisakiFujishiro/msk_consumer.git

# プロジェクトのビルド
RUN mvn install -DskipTests=true -f /msk_consumer/pom.xml

# タイムゾーンの変更
RUN ln -sf  /usr/share/zoneinfo/Asia/Tokyo /etc/localtime

# コンテナのポート解放
EXPOSE 8081

# Javaの実行
CMD java -jar msk_consumer/target/msk_consumer-0.0.1-SNAPSHOT.jar
