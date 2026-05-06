-- Create core tables for smart-reservation-api

create table if not exists users (
    id bigserial primary key,
    name text not null,
    email text not null unique
);

create table if not exists rooms (
    id bigserial primary key,
    name text not null,
    capacity integer not null check (capacity > 0)
);

create table if not exists reservations (
    id bigserial primary key,
    date date not null,
    start_time time not null,
    end_time time not null,
    user_id bigint not null references users(id) on delete restrict,
    room_id bigint not null references rooms(id) on delete restrict,
    check (start_time < end_time)
);

create index if not exists idx_reservations_room_date_time
    on reservations (room_id, date, start_time, end_time);

create index if not exists idx_reservations_user
    on reservations (user_id);
