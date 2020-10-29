FROM openjdk:8
EXPOSE 9004
ADD target/zuul-server-jwt.jar zuul-server-jwt.jar
ENTRYPOINT ["java","-jar","/zuul-server-jwt.jar"]