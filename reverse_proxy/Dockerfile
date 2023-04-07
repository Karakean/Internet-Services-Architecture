FROM nginx:1.23.0-alpine

WORKDIR /etc/nginx/conf.d

COPY reverse_proxy/nginx.conf .

RUN mv nginx.conf default.conf
