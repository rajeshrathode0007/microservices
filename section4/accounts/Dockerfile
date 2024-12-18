#Start with a basic image containing java runtime
FROM openjdk:24-ea-21-oraclelinux9
#MAINTAINER instruction is deprecated in the fovour of using the label
#MAINTAINER Rajesh.Derangula
#Information about who maintains the image
LABEL "org.opencontianers.image.authors" ="Rajesh.Derangula"

#Add the application jar to image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#Execute the application
ENTRYPOINT ["java","jar","accounts-0.0.1-SNAPSHOT.jar"]