#docker ps -a | grep neo4j | awk '{print }' | xargs docker rm
dev-db:
	docker-compose -f docker-compose.dev.yml up --remove-orphans --force-recreate &

dev-boot:
	./gradlew bootRun &

dev: dev-db dev-boot
