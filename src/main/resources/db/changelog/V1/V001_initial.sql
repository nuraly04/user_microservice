CREATE TABLE IF NOT EXISTS ref_common_reference_type
(
    id   BIGSERIAL PRIMARY KEY,
    code VARCHAR(64) UNIQUE NOT NULL,
    name VARCHAR(64)        NOT NULL
);

CREATE TABLE IF NOT EXISTS ref_common_reference
(
    id        BIGSERIAL PRIMARY KEY,
    code      VARCHAR(64) UNIQUE NOT NULL,
    type_id   BIGINT             NOT NULL,
    name      VARCHAR(128)       NOT NULL,
    enabled   BOOLEAN            NOT NULL DEFAULT TRUE,
    parent_id BIGINT,

    CONSTRAINT fk_type_id FOREIGN KEY (type_id) REFERENCES ref_common_reference_type (id)
);

CREATE TABLE IF NOT EXISTS users
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(64)  NOT NULL,
    surname    VARCHAR(64)  NOT NULL,
    patronymic VARCHAR(64),
    city       BIGINT,
    email      VARCHAR(128) NOT NULL UNIQUE,
    phone      VARCHAR(64) UNIQUE,
    active     BOOLEAN      NOT NULL DEFAULT true,
    created_at TIMESTAMP             DEFAULT current_timestamp,
    updated_at TIMESTAMP             DEFAULT current_timestamp,

    CONSTRAINT fk_city FOREIGN KEY (city) REFERENCES ref_common_reference (id)
);

CREATE TABLE IF NOT EXISTS skill
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(64) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT current_timestamp,
    updated_at TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS user_skill
(
    id         BIGSERIAL PRIMARY KEY,
    user_id    BIGINT NOT NULL,
    skill_id   BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT current_timestamp,
    updated_at TIMESTAMP DEFAULT current_timestamp,

    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_skill_id FOREIGN KEY (skill_id) REFERENCES skill (id)
);