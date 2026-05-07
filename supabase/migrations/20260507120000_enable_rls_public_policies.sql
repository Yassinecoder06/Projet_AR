-- Enable RLS and allow public access for demo purposes

alter table if exists users enable row level security;
alter table if exists rooms enable row level security;
alter table if exists reservations enable row level security;

-- Users: public read/write (demo only)
drop policy if exists users_public_select on users;
create policy users_public_select
on users for select
using (true);

drop policy if exists users_public_insert on users;
create policy users_public_insert
on users for insert
with check (true);

drop policy if exists users_public_update on users;
create policy users_public_update
on users for update
using (true)
with check (true);

drop policy if exists users_public_delete on users;
create policy users_public_delete
on users for delete
using (true);

-- Rooms: public read/write (demo only)
drop policy if exists rooms_public_select on rooms;
create policy rooms_public_select
on rooms for select
using (true);

drop policy if exists rooms_public_insert on rooms;
create policy rooms_public_insert
on rooms for insert
with check (true);

drop policy if exists rooms_public_update on rooms;
create policy rooms_public_update
on rooms for update
using (true)
with check (true);

drop policy if exists rooms_public_delete on rooms;
create policy rooms_public_delete
on rooms for delete
using (true);

-- Reservations: public read/write (demo only)
drop policy if exists reservations_public_select on reservations;
create policy reservations_public_select
on reservations for select
using (true);

drop policy if exists reservations_public_insert on reservations;
create policy reservations_public_insert
on reservations for insert
with check (true);

drop policy if exists reservations_public_update on reservations;
create policy reservations_public_update
on reservations for update
using (true)
with check (true);

drop policy if exists reservations_public_delete on reservations;
create policy reservations_public_delete
on reservations for delete
using (true);
