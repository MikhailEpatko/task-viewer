alter table task_log
    add column if not exists
        finished_at timestamptz;

alter table task_log
    rename column created_at to started_at;