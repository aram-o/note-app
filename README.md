# note-app

#PSQL DB initialization

1. Create .env file under root directory.
2. Fill needed ENV variables like below:
    PSQL_URI=jdbc:postgresql://postgres:5432/noteapp_db
    PSQL_DB_USER=noteappuser
    PSQL_DB_PASSWORD=password
    PSQL_DB_DATABASE=noteapp_db
    POSTGRES_PASSWORD=password
    POSTGRES_USER=postgres
3. Run docker-compose up.


