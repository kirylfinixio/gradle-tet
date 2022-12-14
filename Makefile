SHELL = /bin/bash

up:
	make build-app
	docker run -it -v "$(PWD):/home/gradle" --name="java-decrypt-test" java-decrypt-test

down:
	docker stop java-decrypt-test

build-app:
	# docker build docker --tag="java-decrypt-test"
	docker build -t java-decrypt-test ./docker

shell:
	docker exec -it -u 0 java-decrypt-test bash

kafka-test:
	docker exec -it -u 0 java-decrypt-test gradle bootRun