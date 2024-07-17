INSERT INTO users (name, email, password, active, uuid)
VALUES
    /* Password  para todos los usuarios: 654321 */
    ('Pedro Almodovar', 'pedro@gmail.com', '$2a$12$6uBlHEX7e/LZUKsfBIgWC.rwXjk4j8s8YXuLp23CYT93tlPeOgCc2', true, uuid()),
    ('Chavo del 8', 'chavo@gmail.com', '$2a$12$6uBlHEX7e/LZUKsfBIgWC.rwXjk4j8s8YXuLp23CYT93tlPeOgCc2', true, uuid()),
    ('Don Ramón', 'ramon@gmail.com', '$2a$12$6uBlHEX7e/LZUKsfBIgWC.rwXjk4j8s8YXuLp23CYT93tlPeOgCc2', true, uuid());

INSERT INTO topics (title, message, created_at, modified_at, author_id, status, uuid)
VALUES
    ('Need help, please', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ultricies vel est pharetra egestas.', NOW(), NOW(), 1, true, uuid()),
    ('Como empezar el código', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ultricies vel est pharetra egestas.', NOW(), NOW(), 1, true, uuid()),
    ('¿Llegaré a ternminar?', 'Mauris aliquam efficitur neque et tincidunt. Duis sodales massa vel risus luctus tempus. Integer mattis erat vitae ultrices tincidunt.', NOW(), NOW(), 2, true, uuid()),
    ('¿Cuando sería la entrega del proyecto?', 'Mauris aliquam efficitur neque et tincidunt. Duis sodales massa vel risus luctus tempus. Integer mattis erat vitae ultrices tincidunt.', NOW(), NOW(), 2, true, uuid()),
    ('Necesito ayuda', 'Mauris aliquam efficitur neque et tincidunt. Duis sodales massa vel risus luctus tempus. Integer mattis erat vitae ultrices tincidunt.', NOW(), NOW(), 3, true, uuid()),
    ('Oigan, a alguien le pasa lo mismo que a mí?', 'Donec vel est sollicitudin, molestie nunc non, tincidunt velit. Integer convallis lacinia massa, eu vestibulum purus cursus ac.', NOW(), NOW(), 2, true, uuid());
