FROM mysql:5.7

# 设置免密登录
ENV MYSQL_ALLOW_EMPTY_PASSWORD yes

# 将所需的sql文件放到容器中
COPY ./init_schema.sql /docker-entrypoint-initdb.d/1_schema.sql
COPY ./init_data.sql /docker-entrypoint-initdb.d/2_data.sql
