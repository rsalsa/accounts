FROM mongo:3
ADD ./scripts /tmp/scripts

# Modify child mongo to use /data/db-accounts as dbpath (because /data/db wont persist the build because it is already a VOLUME)
RUN mkdir -p /data/db-accounts \
    && echo "dbpath = /data/db-accounts" > /etc/mongodb.conf \
    && chown -R mongodb:mongodb /data/db-accounts

RUN su - mongodb && mongod --fork --logpath /var/log/mongodb.log --dbpath /data/db-accounts  \
    && /tmp/scripts/mongo_create_insert.sh \
    && mongod --dbpath /data/db-accounts --shutdown \
    && chown -R mongodb /data/db-accounts

# Make the new dir a VOLUME to persist it
VOLUME /data/db-accounts

CMD ["mongod", "--config", "/etc/mongodb.conf", "--smallfiles"]
