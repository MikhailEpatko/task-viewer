<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task Viewer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h3>Task Viewer</h3>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Cron</th>
            <th>Enabled</th>
            <th>Activity status</th>
            <th>Last execution status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <#list tasks as task>
            <tr>
                <td>${task.name}</td>
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
                <td>
                    <#if task.lastExecutionStatus == "SUCCESS">
                        <span class="badge bg-success">${task.lastExecutionStatus}</span>
                    <#elseif task.lastExecutionStatus == "STARTED">
                        <span class="badge bg-warning">${task.lastExecutionStatus}</span>
                    <#elseif task.lastExecutionStatus == "FAILED">
                        <span class="badge bg-danger">${task.lastExecutionStatus}</span>
                    <#else>
                        <span class="badge bg-secondary">${task.lastExecutionStatus}</span>
                    </#if>
                </td>
                <td>
                    <a href="/tasks/${task.id}" class="btn btn-sm btn-info">View</a>
                    <a href="/tasks/${task.id}/edit" class="btn btn-sm btn-warning">Edit</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>