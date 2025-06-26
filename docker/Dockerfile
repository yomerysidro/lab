FROM eclipse-temurin:17-jdk-jammy

ENV APP_HOME=/lab-system
RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME

EXPOSE 8085

COPY target/*.jar app.jar

RUN useradd -m appuser && chown -R appuser:appuser $APP_HOME
USER appuser

HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8085/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
