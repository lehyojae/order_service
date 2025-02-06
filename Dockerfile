FROM gradle:8.11.1-jdk17 AS build
# 소스코드를 복사할 작업 디렉토리 생성
WORKDIR /myapp
# 호스트 머신의 소스코드를 이미지 작업 디렉토리로 복사
COPY . /myapp

#이전 빌드에서 생성된 모드 /buiid/ 디덱토리 내용을 삭제,새롭게 빌드
# 프로젝트를 빌드
# --no-daemon은 데몬을 이용하지 않고 빌드
#gradle은 설치되어 있는 gradel을 이용해서 빌드, gradlew는 프로젝트에 포함된 gradle을 이용
#-x test -> test를 제외하고 자겅ㅂ
RUN ./gradlew clean build --no-daemon

# 자바를 실행하기 위한 작업
FROM openjdk:17-alpine
WORKDIR /myapp
# 프로젝트 빌드 후 생성된 jar 파일을 런타임 이미지로 복사
COPY --from=build /myapp/build/libs/*.jar /myapp/orderservice.jar
EXPOSE 9200
ENTRYPOINT ["java", "-jar", "/myapp/orderservice.jar"]
