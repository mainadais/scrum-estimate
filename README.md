### Define entities

- Vote
- Scrum
- Team
- Player
- Account

### Create database
``` 
    psql -U postgres
    Password for user postgres:
    psql (13.0, server 12.3)
    Type "help" for help.

    postgres=# create database db_scrum_estimate
    postgres-# \l
    \q
```

### Add connection properties
```
see /src/main/resources/application-local.properties
```

### Define database schema
```
see src/main/resources/schema.sql
```

### Insert test data
```
see src/test/resources/data.sql
```

