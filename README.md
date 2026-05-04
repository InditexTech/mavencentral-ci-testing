# Maven Central CI Testing

Reference implementation and testing project for the [Maven Java Archetype](https://github.com/InditexTech/archetype/tree/main/maven-java-archetype) CI/CD workflows. This project demonstrates best practices for publishing Java libraries to Maven Central using the Central Portal API.

[![Maven Central](https://img.shields.io/maven-central/v/dev.inditex/mavencentral-ci-testing-core.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/dev.inditex/mavencentral-ci-testing-core)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

## Overview

This project serves multiple purposes:

- **Reference Implementation**: Working example of the Maven Java Archetype with all recommended plugins and workflows
- **CI/CD Testing**: Validation environment for Maven Central publishing workflows
- **Best Practices**: Demonstrates proper configuration for GPG signing, snapshot publishing, and automated releases
- **Learning Resource**: Clear examples of multi-module Maven projects with modern tooling

## Project Structure

```
mavencentral-ci-testing/
├── code/
│   ├── pom.xml                              # Parent POM with all plugin configurations
│   ├── .tool-versions                        # asdf tool version management (Java, Maven)
│   ├── CHANGELOG.md                          # Keep a Changelog format
│   ├── .mvn/settings.xml                    # Maven Central credentials configuration
│   ├── mavencentral-ci-testing-core/        # Main library module
│   │   ├── pom.xml
│   │   └── src/
│   │       ├── main/java/                   # Library code
│   │       └── test/java/                   # Unit tests with JaCoCo coverage
│   └── jacoco-report-aggregate/             # Aggregated coverage reports
│       └── pom.xml                          # maven.deploy.skip=true
└── .github/
    └── workflows/
        ├── code-maven-release.yml           # Automated release to Maven Central
        ├── code-maven-build-snapshot.yml    # Snapshot publishing (on-demand)
        ├── code-maven-PR-verify.yml         # PR verification with tests
        ├── code-maven-sonarcloud-analysis.yml
        └── code-release-preview.yml         # Release preview with version calculation
```

## Key Features

### Maven Central Publishing

- **Automated Releases**: Merge PR → automatic publish to Maven Central (15-30 min)
- **Snapshot Publishing**: On-demand via PR comment `/publish-snapshot` or manual workflow dispatch
- **GPG Signing**: All artifacts signed with organization GPG key
- **Auto-Publish**: Releases automatically published after validation (`autoPublish=true`)

### CI/CD Workflows

- **Release Management**: Semantic versioning with `release-type/*` labels
- **Changelog Automation**: Keep a Changelog format with automatic version updates
- **Preview System**: See computed version and changelog before merge
- **Quality Gates**: SonarCloud analysis, JaCoCo coverage, conventional commits

### Development Tools

- **asdf Integration**: Technology-agnostic builds with version pinning
- **Multi-module Support**: Parent POM + multiple artifact modules
- **Test Automation**: JUnit 5 with Mockito, aggregated coverage reports
- **Gitflow Compatible**: Automated sync PRs from `main` to `develop`

## Quick Start

### Prerequisites

- Java 21+ (managed via asdf)
- Maven 3.9+ (managed via asdf)
- [asdf](https://asdf-vm.com/) for version management

### Building Locally

```bash
# Install required tool versions
asdf install

# Build and test
cd code
mvn clean verify

# View coverage report
open jacoco-report-aggregate/target/site/jacoco-aggregate/index.html
```

### Using as a Dependency

Add to your `pom.xml`:

```xml
<dependency>
  <groupId>dev.inditex</groupId>
  <artifactId>mavencentral-ci-testing-core</artifactId>
  <version>0.2.0</version>
</dependency>
```

For snapshots, add the snapshot repository:

```xml
<repositories>
  <repository>
    <id>central-snapshots</id>
    <url>https://central.sonatype.com/repository/maven-snapshots/</url>
    <snapshots>
      <enabled>true</enabled>
    </snapshots>
  </repository>
</repositories>
```

## Creating a Release

1. **Open PR from `develop` to `main`**
2. **Update `code/CHANGELOG.md`** - Add changes under `[Unreleased]`
3. **Add release label**: `release-type/major`, `release-type/minor`, or `release-type/patch`
4. **Review preview** - Workflow comments on PR with version and changelog
5. **Merge PR** - Release automatically deploys to Maven Central

Example:
```bash
# Create feature branch
git checkout -b feature/new-utility develop

# Make changes, commit
git add .
git commit -m "feat: add string manipulation utility"

# Update CHANGELOG.md
# Add entry under [Unreleased] section

# Push and create PR to main
git push origin feature/new-utility
gh pr create --base main --head feature/new-utility \
  --title "feat: add string manipulation utility" \
  --label "release-type/minor"
```

## Publishing a Snapshot

**From a Pull Request:**
```bash
# Comment on any open PR (requires admin)
/publish-snapshot
```

**Manual Workflow Dispatch:**
1. Go to Actions → "code-maven-build-snapshot"
2. Click "Run workflow"
3. Select branch (default: `develop`)
4. Artifacts available at `https://central.sonatype.com/repository/maven-snapshots/`

## Configuration Examples

### Parent POM Essentials

Key configurations from `code/pom.xml`:

```xml
<!-- Maven Central Portal Publishing -->
<plugin>
  <groupId>org.sonatype.central</groupId>
  <artifactId>central-publishing-maven-plugin</artifactId>
  <version>0.7.0</version>
  <extensions>true</extensions>
  <configuration>
    <publishingServerId>central</publishingServerId>
    <autoPublish>true</autoPublish>  <!-- Auto-publish releases -->
  </configuration>
</plugin>

<!-- Snapshot Repository -->
<distributionManagement>
  <snapshotRepository>
    <id>central</id>
    <url>https://central.sonatype.com/repository/maven-snapshots/</url>
  </snapshotRepository>
</distributionManagement>

<!-- SCM for maven-release-plugin -->
<scm>
  <connection>scm:git:git://github.com/InditexTech/mavencentral-ci-testing.git</connection>
  <developerConnection>scm:git:https://github.com/InditexTech/mavencentral-ci-testing.git</developerConnection>
  <url>https://github.com/InditexTech/mavencentral-ci-testing/tree/main</url>
</scm>
```

### Tool Version Management

`code/.tool-versions`:
```
java temurin-21.0.4+7.0.LTS
maven 3.9.9
```

### Maven Settings

`code/.mvn/settings.xml`:
```xml
<settings>
  <servers>
    <server>
      <id>central</id>
      <username>${env.MAVEN_CENTRAL_USERNAME}</username>
      <password>${env.MAVEN_CENTRAL_PASSWORD}</password>
    </server>
    <server>
      <id>gpg.passphrase</id>
      <passphrase>${env.MAVEN_GPG_PASSPHRASE}</passphrase>
    </server>
  </servers>
</settings>
```

## Required Secrets

Configure in GitHub repository settings:

| Secret | Purpose |
|--------|---------|
| `CI_GPG_SECRET_KEY` | GPG private key for artifact signing |
| `CI_GPG_SECRET_KEY_PASSWORD` | GPG key passphrase |
| `MAVEN_CENTRAL_USERNAME` | Maven Central Portal token username |
| `MAVEN_CENTRAL_PASSWORD` | Maven Central Portal token password |
| `SONAR_TOKEN` | SonarCloud authentication (optional) |

## Repository Variables

| Variable | Value | Purpose |
|----------|-------|---------|
| `DEVELOPMENT_FLOW` | (empty) | Gitflow mode (empty = enabled) |
| `IS_INDITEXTECH_REPO` | `true` | Enable SonarCloud analysis |

## Workflows

### Release Workflow (`code-maven-release.yml`)

**Triggers:** PR merge to `main` with `release-type/*` label

**Steps:**
1. Validate CHANGELOG.md has changes
2. Compute next version from label
3. Update CHANGELOG.md with version and date
4. Run `maven-release-plugin` (version bump, tag, deploy)
5. Create GitHub Release
6. Create sync PR to `develop` (Gitflow)

### Snapshot Workflow (`code-maven-build-snapshot.yml`)

**Triggers:** 
- PR comment `/publish-snapshot` (admin only)
- Manual workflow dispatch

**Steps:**
1. Validate admin permissions
2. Checkout PR branch or specified branch
3. Run `mvn deploy` with `-DskipEnforceSnapshots=true`
4. Upload to Maven Central snapshot repository

### PR Verify (`code-maven-PR-verify.yml`)

**Triggers:** PR with changes in `code/` or `.github/workflows/code*`

**Steps:**
1. Run unit tests with JaCoCo coverage
2. SonarCloud incremental analysis (if enabled)

## Best Practices Demonstrated

✅ **Security**: GPG signing, credential management, minimal permissions
✅ **Automation**: Automated releases, snapshot publishing, changelog updates
✅ **Quality**: Test coverage, SonarCloud integration, conventional commits
✅ **Versioning**: Semantic versioning, Keep a Changelog format
✅ **CI/CD**: GitHub Actions workflows, asdf tool management
✅ **Multi-module**: Parent POM, aggregated coverage, selective deployment

## Troubleshooting

### Release Not Appearing on Maven Central

- **Check deployment status**: https://central.sonatype.com/publishing/deployments
- **Verify autoPublish**: Should see "Deployment will publish automatically" in logs
- **Wait time**: Artifacts appear 15-30 minutes after successful workflow
- **Manual publish**: If needed, find deployment ID in logs and publish via Portal UI

### Snapshot Publishing Failed

- **Check namespace**: Snapshot publishing must be enabled for `dev.inditex` namespace
- **Verify version**: Must end with `-SNAPSHOT` in pom.xml
- **Review logs**: Check workflow run for detailed error messages

### Release Workflow Failed

- **CHANGELOG.md**: Ensure `[Unreleased]` section has changes
- **SCM URL**: Must use HTTPS format for `developerConnection`
- **Permissions**: Verify all required secrets are configured

## Related Projects

- [Maven Java Archetype](https://github.com/InditexTech/archetype/tree/main/maven-java-archetype) - Full archetype documentation
- [InditexTech OSS](https://github.com/InditexTech) - Organization repositories

## License

This project is licensed under the [Apache License 2.0](LICENSE).

## Contributing

This is a testing/reference project. For contributing to the archetype itself, see [archetype/maven-java-archetype](https://github.com/InditexTech/archetype/tree/main/maven-java-archetype).
