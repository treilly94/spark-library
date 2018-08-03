FROM python:3.6
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk maven

RUN mkdir /code
WORKDIR /code
ADD . /code/
RUN pip install -r ./python/requirements.txt