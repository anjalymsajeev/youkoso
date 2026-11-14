# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and this project
adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html). Versions before 1.0.0 may
contain breaking changes in any minor release; they are listed under **Breaking**.

## [Unreleased]

### Added

- Project foundation (Sprint 1). No library functionality yet:
  - Gradle multi-module build: `youkoso-core`, `youkoso-compose`, `youkoso-material3`,
    `youkoso-datastore` and a `sample` app, with shared configuration in `build-logic`
    convention plugins and versions in `gradle/libs.versions.toml`.
  - Quality gates on every build: Kotlin explicit API mode, warnings as errors,
    binary-compatibility validation (`apiCheck`), Android Lint (warnings as errors, plus Compose
    lint checks) and Spotless/ktlint formatting.
  - Aggregated API reference generation with Dokka (`./gradlew :dokkaGenerate`).
  - GitHub Actions CI, Dependabot, issue and pull request templates.
  - Documentation: README, contribution guide, code of conduct, security policy, project proposal
    and architecture decision records.
