# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [UNRELEASED]

### Added
- Initial Maven project structure with parent POM and core module
- Maven Central Portal snapshot publishing configuration
- GitHub Actions workflows for snapshot publishing (PR-triggered and manual)
- GitHub Actions workflow for PR verification with tests and SonarCloud
- GitHub Actions workflow for SonarCloud analysis
- GitHub Actions workflow for Maven release
- GitHub Actions workflow for release preview
- JaCoCo coverage aggregation module
- Maven enforcer plugin configuration
- GPG signing configuration for artifacts
- asdf tool version management support

### Changed
- Simplified snapshot workflow success/failure messages

### Fixed
- Fixed 403 Forbidden error when publishing snapshots to Maven Central Portal
- Configured distributionManagement for Maven Central snapshot repository
- Excluded jacoco-report-aggregate module from deployment
