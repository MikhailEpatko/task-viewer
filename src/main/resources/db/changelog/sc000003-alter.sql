alter table task
    add column if not exists
        status text not null default 'PENDING';