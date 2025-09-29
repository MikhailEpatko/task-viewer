create table if not exists task
(
    id                    bigint generated always as identity
        primary key,
    name                  text                                               not null
        unique,
    description           text                                               not null,
    cron                  text                                               not null,
    enabled               boolean                  default false             not null,
    created_at            timestamp with time zone default CURRENT_TIMESTAMP not null,
    modified_at           timestamp with time zone default CURRENT_TIMESTAMP not null,
    activity_status       text                     default 'PENDING'::text   not null,
    last_execution_status text
);

create table if not exists task_log
(
    id          bigint generated always as identity
        primary key,
    task_id     bigint                                             not null
        references task,
    status      text                                               not null,
    error       text,
    started_at  timestamp with time zone default CURRENT_TIMESTAMP not null,
    finished_at timestamp with time zone
);