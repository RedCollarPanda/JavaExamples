FROM rabbitmq:3.6.6-management
ENV RABBITMQ_USER test_user
ENV RABBITMQ_PASSWORD test_user_password
ADD init.sh /init.sh
EXPOSE 15672
RUN ["chmod", "+x", "/init.sh"]
CMD ["/init.sh"]
