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
    created_at TIMESTAMP             DEFAULT now(),
    updated_at TIMESTAMP             DEFAULT now(),

    CONSTRAINT fk_city FOREIGN KEY (city) REFERENCES ref_common_reference (id)
);

CREATE TABLE IF NOT EXISTS skill
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(64) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS recommendation
(
    id          BIGSERIAL PRIMARY KEY,
    author_id   BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    content     TEXT,
    created_at  TIMESTAMP DEFAULT now(),
    updated_at  TIMESTAMP DEFAULT now(),

    CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES users (id),
    CONSTRAINT fk_receiver_id FOREIGN KEY (receiver_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS user_skill_guarantee
(
    id BIGSERIAL PRIMARY KEY,
    skill_id BIGINT NOT NULL,
    guarantee_id BIGINT NOT NULL,

    CONSTRAINT fk_skill_id FOREIGN KEY (skill_id) REFERENCES skill (id),
    CONSTRAINT fk_guarantee_id FOREIGN KEY (guarantee_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS offer_skill
(
    id                BIGSERIAL PRIMARY KEY,
    skill_id          BIGINT NOT NULL,
    recommendation_id BIGINT NOT NULL,
    created_at        TIMESTAMP DEFAULT now(),
    updated_at        TIMESTAMP DEFAULT now(),

    CONSTRAINT fk_skill_id FOREIGN KEY (skill_id) REFERENCES skill (id),
    CONSTRAINT fk_recommendation_id FOREIGN KEY (recommendation_id) REFERENCES recommendation (id)
);

CREATE TABLE IF NOT EXISTS m2m_user_skill
(
    id       BIGSERIAL PRIMARY KEY,
    user_id  BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,

    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_skill_id FOREIGN KEY (skill_id) REFERENCES skill (id)
);