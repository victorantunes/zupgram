test:
	./gradlew bootJar
	docker-compose -f docker-compose.test.yml up --build --abort-on-container-exit --exit-code-from zupgram-test --force-recreate --remove-orphans

dev-db:
	docker-compose -f docker-compose.dev.yml up --remove-orphans --force-recreate &

dev-boot:
	./gradlew bootRun &

dev: dev-db dev-boot
