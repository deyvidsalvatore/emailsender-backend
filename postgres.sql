-- Cria o usuário "postgres" ou atualiza a senha para "100senha"
DO
$$
BEGIN
    -- Verifica se o usuário "postgres" já existe
    IF EXISTS (SELECT FROM pg_user WHERE usename = 'postgres') THEN
        -- Atualiza a senha do usuário "postgres"
        ALTER USER postgres WITH PASSWORD '100senha';
    ELSE
        -- Cria o usuário "postgres" com a senha "100senha"
        CREATE USER postgres WITH SUPERUSER PASSWORD '100senha';
    END IF;
END
$$;

-- Cria o banco de dados "registration" e atribui a propriedade "OWNER" ao usuário "postgres"
CREATE DATABASE registration OWNER postgres;
