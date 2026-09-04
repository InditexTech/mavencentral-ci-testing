# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [0.2.5] - 2026-09-04

### Fixed

- Governed release lane: a single reactor now collects its jars from the whole
  reactor instead of only the root `target/`, and a failed build no longer
  advances the baseline to the next snapshot.

## [0.2.4] - 2026-09-04

### Changed

- Governed release lane: releases are staged on a run-scoped ref and promoted
  only after the build succeeds, the baseline branch must be protected before a
  release can start, and merges of `automated/ci-governance-sync` no longer cut
  a release.
- Governed release lane: the project descriptor is now read after the checkout,
  so releases no longer abort with `Missing descriptor`.

## [0.2.3] - 2026-09-02

### Fixed

- Delegated release lane: the Maven Central publish job now deploys the
  already-checked-out release-tag tree directly instead of re-running
  `release:perform` in a job that lacks `release.properties`/`-Dtag`, and
  skips the snapshot enforcer for the final (non-SNAPSHOT) build.

## [0.2.2] - 2026-09-02

## [0.2.1] - 2026-09-02

### Changed

- Validated the governed Java CI/CD lifecycle end-to-end on the canary consumer:
  ADOPT convergence, snapshot publishing to Maven Central Portal, and the
  delegated git-flow release lane (release:prepare / release:perform).

## [0.2.0] - 2026-05-04

## [0.1.1] - 2026-05-04

### Changed

- Enabled automatic publishing in central-publishing-maven-plugin for releases

## [0.1.0] - 2026-05-04

### Added

- Initial project structure with multi-module Maven setup
- Core module with StringUtils utility class
- JaCoCo aggregated coverage report module
- Maven Central Portal snapshot publishing configuration with central-publishing-maven-plugin v0.7.0+
- GitHub Actions workflows for snapshot publishing (PR-triggered and manual)
- GitHub Actions workflow for PR verification with tests and SonarCloud
- GitHub Actions workflow for SonarCloud analysis
- GitHub Actions workflow for Maven release
- GitHub Actions workflow for release preview
- Maven enforcer plugin configuration
- GPG artifact signing configuration
- asdf tool version management support

### Changed

- Simplified snapshot workflow success/failure messages

### Fixed

- Fixed 403 Forbidden error when publishing snapshots to Maven Central Portal
- Fixed SCM developerConnection URL in pom.xml to use HTTPS for maven-release-plugin compatibility
- Configured distributionManagement for Maven Central snapshot repository
- Excluded jacoco-report-aggregate module from deployment

[Unreleased]: https://github.com/InditexTech/mavencentral-ci-testing/compare/0.2.5...HEAD

[0.2.5]: https://github.com/InditexTech/mavencentral-ci-testing/compare/0.2.4...0.2.5

[0.2.4]: https://github.com/InditexTech/mavencentral-ci-testing/compare/0.2.3...0.2.4

[0.2.3]: https://github.com/InditexTech/mavencentral-ci-testing/compare/0.2.2...0.2.3

[0.2.2]: https://github.com/InditexTech/mavencentral-ci-testing/compare/0.2.1...0.2.2

[0.2.1]: https://github.com/InditexTech/mavencentral-ci-testing/compare/0.2.0...0.2.1

[0.2.0]: https://github.com/InditexTech/mavencentral-ci-testing/compare/0.1.1...0.2.0

[0.1.1]: https://github.com/InditexTech/mavencentral-ci-testing/compare/0.1.0...0.1.1

[0.1.0]: https://github.com/InditexTech/mavencentral-ci-testing/releases/tag/0.1.0
