APP := $(shell basename $(shell git remote get-url origin | sed 's/\.git$$//'))
REGISTRY := devregistry20250126.azurecr.io
VERSION=$(shell git describe --tags --abbrev=0)-$(shell git rev-parse --short HEAD)

test:
	mvn test
image:
	docker build . -t ${REGISTRY}/${APP}:${VERSION}

push:
	docker push ${REGISTRY}/${APP}:${VERSION}

clean:
	docker rmi ${REGISTRY}/${APP}:${VERSION}