<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h3>${task.name}</h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Cron</th>
            <th>Enabled</th>
            <th>Activity status</th>
            <th>Created</th>
            <th>Updated</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${task.id?c}</td>
            <td>${task.description}</td>
            <td>${task.cron}</td>
            <td>
                <#if task.enabled>
                    <span class="badge bg-success">Enabled</span>
                <#else>
                    <span class="badge bg-secondary">Disabled</span>
                </#if>
            </td>
            <td>
                <#if task.activityStatus == "IN_PROGRESS">
                    <span class="badge bg-success">${task.activityStatus}</span>
                <#elseif task.activityStatus == "PENDING">
                    <span class="badge bg-secondary">${task.activityStatus}</span>
                <#else>
                    <span class="badge bg-secondary">${task.activityStatus}</span>
                </#if>
            </td>
            <td>${task.createdAt}</td>
            <td>${task.modifiedAt}</td>
        </tr>
        </tbody>
    </table>
    <div class="card-body">
        <a href="/tasks/${task.id}/edit" class="btn btn-warning">Edit</a>
        <a href="/tasks" class="btn btn-secondary">Back to List</a>
    </div>
</div>
<div class="container mt-4">
    <h3>Execution logs</h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Started</th>
            <th>Finished</th>
            <th>ExecutionStatus</th>
            <th>Error</th>
        </tr>
        </thead>
        <tbody>
        <#list task.logs as log>
            <tr>
                <td>${log.id?c}</td>
                <td>${log.startedAt}</td>
                <td>${log.finishedAt}</td>
                <td>
                    <#if log.executionStatus == "SUCCESS">
                        <span class="badge bg-success">${log.executionStatus}</span>
                    <#elseif log.executionStatus == "STARTED">
                        <span class="badge bg-warning">${log.executionStatus}</span>
                    <#elseif log.executionStatus == "FAILED">
                        <span class="badge bg-danger">${log.executionStatus}</span>
                    <#else>
                        <span class="badge bg-secondary">${log.executionStatus}</span>
                    </#if>
                </td>
                <td>${log.error}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>