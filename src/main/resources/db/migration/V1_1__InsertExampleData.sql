INSERT INTO users (name, email, password , active)
VALUES
    ('Pedro Almodovar', 'pedro@gmail.com', '$2a$12$6uBlHEX7e/LZUKsfBIgWC.rwXjk4j8s8YXuLp23CYT93tlPeOgCc2', true),
    ('Chavo del 8', 'chavo@gmail.com', '$2a$12$6uBlHEX7e/LZUKsfBIgWC.rwXjk4j8s8YXuLp23CYT93tlPeOgCc2', true),
    ('Don Ramón', 'ramon@gmail.com', '$2a$12$6uBlHEX7e/LZUKsfBIgWC.rwXjk4j8s8YXuLp23CYT93tlPeOgCc2', true);

INSERT INTO topics (title, message, created_at, author_id, status)
VALUES
    ('Need help, please', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis ultricies vel est pharetra egestas.', NOW(), 1, true),
    ('Una frase', 'Mauris aliquam efficitur neque et tincidunt. Duis sodales massa vel risus luctus tempus. Integer mattis erat vitae ultrices tincidunt.', NOW(), 2, true),
    ('Otra frase', 'Donec vel est sollicitudin, molestie nunc non, tincidunt velit. Integer convallis lacinia massa, eu vestibulum purus cursus ac.', NOW(), 2, true);
