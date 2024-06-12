create table users(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    password varchar(100) not null,

    primary key(id)
);

create table topics(
    id bigint not null auto_increment,
    title varchar(100) not null,
    message varchar(255) not null,
    createdAt datetime not null,
    author_id bigint not null,
    status tinyint not null,

    primary key(id),

    constraint fk_topics_author_id foreign key(author_id) references users(id)
);
