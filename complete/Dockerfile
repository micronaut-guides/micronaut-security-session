FROM oracle/graalvm-ce:20.1.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/complete
WORKDIR /home/app/complete

RUN native-image --no-server -cp build/libs/complete-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/complete/micronautguide /app/complete
ENTRYPOINT ["/app/complete"]
