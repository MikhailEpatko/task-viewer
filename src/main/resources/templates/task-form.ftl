<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${isEdit?then('Edit Task', 'Create Task')}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h3>${taskForm.name}</h3>

    <form action="${isEdit?then('/tasks/' + taskId + "/edit", '/tasks')}" method="post">
        <div class="mb-3">
            <label for="description" class="form-label"><b>Description</b></label>
            <textarea class="form-control" id="description" name="description"
                      rows="3">${taskForm.description}</textarea>
        </div>

        <div class="mb-3">
            <label for="cron" class="form-label"><b>Cron Expression</b></label>
            <input type="text" class="form-control" id="cron" name="cron"
                   value="${taskForm.cron}" placeholder="0 * * * * *">
            <div class="form-text">Cron expression for task scheduling</div>
        </div>

        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="enabled" name="enabled" value="true"
                   <#if taskForm.enabled>checked</#if>>
            <label class="form-check-label" for="enabled"><b>Enabled</b></label>
        </div>

        <button type="submit" class="btn btn-primary">
            ${isEdit?then('Update', 'Create')}
        </button>
        <a href="/tasks" class="btn btn-secondary">Cancel</a>
    </form>
</div>
<div class="container mt-4">
    <h5>Cron expression format</h5>
    <img src="/images/cron.jpeg" alt="cron-img" width="537" height="241">
    <h5>Cron expression examples:</h5>
    <table class="table table-striped">
        <tbody>
        <tr>
            <td><b>0 0 * * * *</b></td>
            <td>top of every hour of every day</td>
        </tr>
        <tr>
            <td><b>*/10 * * * * *</b></td>
            <td>every ten seconds</td>
        </tr>
        <tr>
            <td><b>0 0 8-10 * * *</b></td>
            <td>8, 9 and 10 o’clock of every day</td>
        </tr>
        <tr>
            <td><b>0 0 6,19 * * *</b></td>
            <td>6:00 AM and 7:00 PM every day</td>
        </tr>
        <tr>
            <td><b>0 0/30 8-10 * * *</b></td>
            <td>8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day</td>
        </tr>
        <tr>
            <td><b>0 0 9-17 * * MON-FRI</b></td>
            <td>on the hour nine-to-five weekdays</td>
        </tr>
        <tr>
            <td><b>0 0 0 25 DEC ?</b></td>
            <td>every Christmas Day at midnight</td>
        </tr>
        <tr>
            <td><b>0 0 0 L * *</b></td>
            <td>last day of the month at midnight</td>
        </tr>
        <tr>
            <td><b>0 0 0 L-3 * *</b></td>
            <td>third-to-last day of the month at midnight</td>
        </tr>
        <tr>
            <td><b>0 0 0 * * 5L</b></td>
            <td>last Friday of the month at midnight</td>
        </tr>
        <tr>
            <td><b>0 0 0 * * THUL</b></td>
            <td>last Thursday of the month at midnight</td>
        </tr>
        <tr>
            <td><b>0 0 0 1W * *</b></td>
            <td>first weekday of the month at midnight</td>
        </tr>
        <tr>
            <td><b>0 0 0 LW * *</b></td>
            <td>last weekday of the month at midnight</td>
        </tr>
        <tr>
            <td><b>0 0 0 ? * 5#2</b></td>
            <td>the second Friday in the month at midnight</td>
        </tr>
        <tr>
            <td><b>0 0 0 ? * MON#1</b></td>
            <td>the first Monday in the month at midnight</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>