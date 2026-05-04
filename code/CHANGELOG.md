# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

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
- Configured distributionManagement for Maven Central snapshot repository
- Excluded jacoco-report-aggregate module from deployment

[Unreleased]: https://github.com/InditexTech/mavencentral-ci-testing/compare/0.1.0...HEAD

[0.1.0]: https://github.com/InditexTech/mavencentral-ci-testing/releases/tag/0.1.0
