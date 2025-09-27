create table if not exists task
(
    id                    bigint primary key generated always as identity,
    name                  text        not null unique,
    description           text        not null,
    cron                  text        not null,
    status                text        not null default 'PENDING',
    last_execution_status text        not null default 'PENDING',
    enabled               bool        not null default false,
    created_at            timestamptz not null default current_timestamp,
    modified_at           timestamptz not null default current_timestamp
);

create table if not exists task_log
(
    id          bigint primary key generated always as identity,
    task_id     bigint      not null references task (id),
    status      text        not null,
    error       text,
    started_at  timestamptz not null default current_timestamp,
    finished_at timestamptz
);

