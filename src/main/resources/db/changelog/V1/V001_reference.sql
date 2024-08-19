INSERT INTO ref_common_reference_type(code, name)
SELECT 'COUNTRY', 'Страны'
WHERE NOT EXISTS(SELECT *
                 FROM ref_common_reference_type
                 WHERE code = 'COUNTRY'
                 AND name = 'Страны');

INSERT INTO ref_common_reference_type(code, name)
SELECT 'CITY', 'Города'
WHERE NOT EXISTS(SELECT *
                 FROM ref_common_reference_type
                 WHERE code = 'CITY'
                 AND name = 'Города');

INSERT INTO ref_common_reference (code, type_id, name)
VALUES ('KYRGYZSTAN', (SELECT id FROM ref_common_reference_type WHERE code = 'COUNTRY'), 'Кыргызстан'),
       ('RUSSIA', (SELECT id FROM ref_common_reference_type WHERE code = 'COUNTRY'), 'Россия'),
       ('KAZAKHSTAN', (SELECT id FROM ref_common_reference_type WHERE code = 'COUNTRY'), 'Казакстан'),
       ('UZBEKISTAN', (SELECT id FROM ref_common_reference_type WHERE code = 'COUNTRY'), 'Узбекистан');

INSERT INTO ref_common_reference (code, type_id, name, parent_id)
VALUES ('BISHKEK', (SELECT id FROM ref_common_reference_type WHERE code = 'CITY'), 'Бишкек', (SELECT id FROM ref_common_reference WHERE code = 'KYRGYZSTAN')),
       ('ASTANA', (SELECT id FROM ref_common_reference_type WHERE code = 'CITY'), 'Астана', (SELECT id FROM ref_common_reference WHERE code = 'KAZAKHSTAN')),
       ('ALMATY', (SELECT id FROM ref_common_reference_type WHERE code = 'CITY'), 'Алматы', (SELECT id FROM ref_common_reference WHERE code = 'KAZAKHSTAN')),
       ('TASHKENT', (SELECT id FROM ref_common_reference_type WHERE code = 'CITY'), 'Ташкент', (SELECT id FROM ref_common_reference WHERE code = 'UZBEKISTAN')),
       ('MOSCOW', (SELECT id FROM ref_common_reference_type WHERE code = 'CITY'), 'Москва', (SELECT id FROM ref_common_reference WHERE code = 'RUSSIA'));