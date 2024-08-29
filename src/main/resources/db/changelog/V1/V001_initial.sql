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
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR(64)  NOT NULL,
    surname       VARCHAR(64)  NOT NULL,
    patronymic    VARCHAR(64),
    about         TEXT,
    date_of_birth DATE         NOT NULL,
    city          BIGINT,
    email         VARCHAR(128) NOT NULL UNIQUE,
    phone         VARCHAR(64) UNIQUE,
    active        BOOLEAN      NOT NULL DEFAULT true,
    created_at    TIMESTAMP             DEFAULT now(),
    updated_at    TIMESTAMP             DEFAULT now(),

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
    id           BIGSERIAL PRIMARY KEY,
    skill_id     BIGINT NOT NULL,
    guarantor_id BIGINT NOT NULL,

    CONSTRAINT fk_skill_id FOREIGN KEY (skill_id) REFERENCES skill (id),
    CONSTRAINT fk_guarantee_id FOREIGN KEY (guarantor_id) REFERENCES users (id)
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

CREATE TABLE IF NOT EXISTS mentorship_request
(
    id        BIGSERIAL PRIMARY KEY,
    mentee_id BIGINT       NOT NULL,
    mentor_id BIGINT       NOT NULL,
    status    VARCHAR(128) NOT NULL,
    content   TEXT         NOT NULL,

    CONSTRAINT fk_mentee_id FOREIGN KEY (mentee_id) REFERENCES users (id),
    CONSTRAINT fk_mentor_id FOREIGN KEY (mentor_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS contact
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT       NOT NULL,
    contact VARCHAR(255) NOT NULL,
    type    VARCHAR(128) NOT NULL,

    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS event
(
    id            BIGSERIAL PRIMARY KEY,
    created_at    TIMESTAMP DEFAULT now(),
    updated_at    TIMESTAMP DEFAULT now(),
    start_date    TIMESTAMP NOT NULL,
    end_date      TIMESTAMP NOT NULL,
    content       TEXT         NOT NULL,
    title         VARCHAR(128) NOT NULL,
    owner_id      BIGINT       NOT NULL,
    city_id       BIGINT       NOT NULL,
    location      VARCHAR(255) NOT NULL,
    max_attendees BIGINT,

    CONSTRAINT fk_owner_id FOREIGN KEY (owner_id) REFERENCES users (id),
    CONSTRAINT fk_city_id FOREIGN KEY (city_id) REFERENCES ref_common_reference (id)
);

CREATE TABLE IF NOT EXISTS event_skill
(
    id BIGSERIAL PRIMARY KEY,
    skill_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS m2m_user_skill
(
    id       BIGSERIAL PRIMARY KEY,
    user_id  BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,

    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_skill_id FOREIGN KEY (skill_id) REFERENCES skill (id)
);

CREATE TABLE IF NOT EXISTS m2m_mentorship
(
    id        BIGSERIAL PRIMARY KEY,
    mentee_id BIGINT NOT NULL,
    mentor_id BIGINT NOT NULL,

    CONSTRAINT fk_mentee_id FOREIGN KEY (mentee_id) REFERENCES users (id),
    CONSTRAINT fk_mentor_id FOREIGN KEY (mentor_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS m2m_subscription
(
    id          BIGSERIAL PRIMARY KEY,
    follower_id BIGINT NOT NULL,
    author_id   BIGINT NOT NULL,

    CONSTRAINT fk_follower_id FOREIGN KEY (follower_id) REFERENCES users (id),
    CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES users (id)
);