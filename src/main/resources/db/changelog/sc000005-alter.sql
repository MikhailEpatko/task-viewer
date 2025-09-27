alter table task
    add column if not exists
        last_execution_status text;

alter table task
    rename column status to activity_status;