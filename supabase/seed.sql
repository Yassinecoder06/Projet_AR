-- Optional seed data for local development

insert into users (name, email)
values
    ('Alice Johnson', 'alice@example.com'),
    ('David Kim', 'david@example.com')
on conflict do nothing;

insert into rooms (name, capacity)
values
    ('Ocean Room', 6),
    ('Sky Room', 12)
on conflict do nothing;
