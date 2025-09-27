alter table task
    drop column if exists status,
    drop column if exists last_execution_status;

alter table task_log
    drop column if exists finished_at;

alter table task_log
    rename column started_at to created_at;

