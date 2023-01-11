create table rooms.gadget
(
    idGadget    varchar(100) null,
    cost        int(255)     null,
    description varchar(100) null,
    unityShape  varchar(100) null,
    constraint idGadget
        unique (idGadget)
);
create table rooms.purchase
(
    idUser   varchar(100) null,
    idGadget varchar(100) null
);
create table rooms.user
(
    idUser           varchar(100) not null,
    name             varchar(50)  null,
    surname          varchar(50)  null,
    birthday         varchar(50)  null,
    email            varchar(50)  null,
    password         varchar(50)  null,
    currentlyPlaying tinyint(1)   null,
    coins            int(255)     null,
    experience       int(255)     null,
    constraint email
        unique (email),
    constraint idUser
        unique (idUser)
);

