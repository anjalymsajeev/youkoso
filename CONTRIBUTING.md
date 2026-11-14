# Contributing to youkoso

Thanks for your interest! This guide explains how the project is organised and what a change needs
before it can be merged.

> youkoso is pre-alpha. Until the core API settles (Sprint 3), the most helpful contributions are
> bug reports, design feedback on open issues, and documentation fixes.

## Ground rules

- Be respectful. Everyone taking part agrees to the [Code of Conduct](CODE_OF_CONDUCT.md).
- For anything larger than a small fix, **open an issue first** so we can agree on the approach
  before you invest time.
- Never commit secrets: signing keys, tokens, passwords or `local.properties`.

## Project layout

| Path | What lives there |
|---|---|
| `youkoso-core/` | Pure Kotlin engine. Must not depend on Android. |
| `youkoso-compose/` | Compose foundation UI. Must not depend on Material. |
| `youkoso-material3/` | Material 3 defaults on top of `youkoso-compose`. |
| `youkoso-datastore/` | Optional DataStore completion store. |
| `sample/` | Demo app (not published). |
| `build-logic/` | Gradle convention plugins shared by all modules. |
| `docs/` | Proposal, architecture decision records (`docs/adr/`), guides. |
| `gradle/libs.versions.toml` | Every dependency and plugin version. |

Module dependency rules are explained in [ADR-0001](docs/adr/0001-module-structure.md).

## Development setup

1. JDK 17 or newer (CI uses Temurin 21).
2. Android SDK. Point Gradle at it with `ANDROID_HOME` or `sdk.dir=...` in `local.properties`.
   AGP downloads the required platform automatically if its license has been accepted.
3. Clone, then run:

```bash
./gradlew check
```

## What `check` verifies

| Gate | Task | Fix with |
|---|---|---|
| Formatting (ktlint via Spotless) | `spotlessCheck` | `./gradlew spotlessApply` |
| Android Lint (warnings are errors) | `lintDebug` | Fix the reported issue |
| Unit tests | `test` | Fix the code or the test |
| Binary compatibility | `apiCheck` | See below |

Kotlin compiler warnings are errors in every module.

### Public API changes

Library modules use Kotlin **explicit API mode**, and each records its public API in
`<module>/api/<module>.api`. If you change public API on purpose:

```bash
./gradlew apiDump
```

Commit the updated `.api` file and explain the change in your pull request. Reviewers treat every
diff in an `.api` file as a deliberate API decision. After 1.0, binary-incompatible changes need a
major version.

## Coding conventions

- Follow the [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html) and
  the [Compose API guidelines](https://github.com/androidx/androidx/blob/androidx-main/compose/docs/compose-api-guidelines.md).
  Formatting is enforced automatically.
- Every public declaration needs KDoc.
- Keep the public API minimal. Prefer `internal` unless something must be public.
- Avoid public `data class`es in library modules: `copy` and `componentN` make adding a property
  a binary-incompatible change.
- Business logic needs unit tests. UI behaviour needs Compose UI tests (from Sprint 3).

## Commits and pull requests

- Branch from `main`: `feature/...`, `fix/...`, `docs/...` or `build/...`.
- Use [Conventional Commits](https://www.conventionalcommits.org/) (`feat:`, `fix:`, `docs:`,
  `build:`, `test:`, `refactor:`, `chore:`). Mark breaking changes with `!` (for example
  `feat!:`).
- Fill in the pull request template. CI must be green before review.
- User-visible changes get an entry under **Unreleased** in [CHANGELOG.md](CHANGELOG.md).
- Pull requests are squash-merged.

## Releases

Versioning follows [Semantic Versioning](https://semver.org/). The release process (Maven Central
publishing, signing, tagging) is documented in Sprint 4.

## License

By contributing, you agree that your contributions are licensed under the
[Apache License 2.0](LICENSE).
