# task-viewer
Template project that demonstrates kotlin stack

### Features

Here's a list of features included in this project:
- **Routing** - allows to define structured routes and associated handlers
- **StatusPages** - a plugin that handles exceptions and status codes
- **ContentNegotiation** - a plugin that serves two primary purposes:
   + uses the Accept and Content-Type headers
   + serializing/deserializing the content in a specific format (JSON)
- **ktlint** - Kotlin linter
- **Exposed** - a Kotlin SQL library
- **PostgreSQL** - database
- **HikariCP** - provides a pool of connections to the database
- **Liquibase** - database change management
- **Authentication** - JWT authentication


### Automatic update of dependency versions
1) When adding a new dependency to build.gradle.kts, execute:

```bash
gradle refreshVersionsMigrate --mode=VersionsPropertiesOnly
```
The plugin will change the contents of the build.gradle.kts.

2) Validate and fix the build.gradle.kts, if there are any errors.

3) Refresh dependencies versions:
```bash
gradle refreshVersions
```
The plugin will search for new dependency versions and specify them in the versions.properties file.

4) Select the necessary dependency versions in versions.properties.

5) To update versions without adding new dependencies, you need to follow paragraphs 3, 4.
