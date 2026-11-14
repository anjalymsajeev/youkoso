# youkoso

[![CI](https://github.com/anjalymsajeev/youkoso/actions/workflows/ci.yml/badge.svg)](https://github.com/anjalymsajeev/youkoso/actions/workflows/ci.yml)
[![License](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](LICENSE)
![minSdk](https://img.shields.io/badge/minSdk-23-green.svg)

**A modern, accessible, Compose-first onboarding library for Android.**
Pages, navigation, indicators and transitions, with every visual piece replaceable.

*Youkoso* (ようこそ) is Japanese for "welcome".

> [!IMPORTANT]
> **Status: pre-alpha, under active development.** There is no release yet and the API does not
> exist yet. The repository currently contains the project foundation (Sprint 1 of the
> [roadmap](#roadmap)). Please don't depend on it yet. Watch the repository to follow progress.

## Why youkoso?

The popular [`welcome-android`](https://github.com/stephentuso/welcome-android) library is archived
and can no longer be resolved from a standard build (it was published to the now-closed jCenter).
It also predates AndroidX, Jetpack Compose, predictive back and edge-to-edge. youkoso is a new
library built for the current platform, aiming for:

- **Compose-first and embeddable.** A composable you can put in an Activity, a navigation
  destination or a sheet. Apps built with Views can launch it through an `ActivityResultContract`.
- **Correct state handling.** The current page survives rotation and process death.
- **A real navigation engine.** Skip, back and per-page gating rules live in a pure-Kotlin state
  machine that is fully unit-tested, rather than in a collection of flags.
- **Accessibility from the start.** Page-position semantics, page-change announcements, large-font
  layouts and reduced motion.
- **Your design system, not ours.** A design-system-agnostic foundation layer, with optional
  Material 3 defaults on top.
- **A stable API.** Explicit API mode and binary-compatibility checks run on every change.

## Planned modules

| Artifact | Purpose |
|---|---|
| `youkoso-core` | Pure Kotlin engine: navigation state machine, policies, results, events. No Android dependencies. |
| `youkoso-compose` | Compose foundation layer: the onboarding screen, state holder, pager, page scope, accessibility. No Material dependency. |
| `youkoso-material3` | Material 3 defaults: ready-to-use screen, standard page and controls. |
| `youkoso-datastore` | Optional "show once" completion store backed by Jetpack DataStore. |

The Maven group will be `io.github.anjalymsajeev`. Installation instructions will appear here with
the first alpha release.

## Roadmap

| Milestone | Scope | Status |
|---|---|---|
| Sprint 1: Foundation | Build setup, modules, quality gates, CI, docs | **In progress** |
| Sprint 2: Core engine | Navigation state machine, policies, results, events | Planned |
| Sprint 3: Compose UI | Onboarding screen, indicator, controls, standard page | Planned |
| Sprint 4: `0.1.0-alpha01` | Maven Central publishing, docs site | Planned |
| Sprint 5: `0.2.0-alpha01` | Transitions, theming, accessibility, predictive back | Planned |
| Sprint 6: `0.3.0-beta01` | DataStore persistence, View-based app integration, API freeze | Planned |
| Sprint 7: `1.0.0` | Stabilisation and first stable release, when ready | Planned |

The full plan, including requirements, architecture and risks, is in
[docs/proposal.md](docs/proposal.md). Key decisions are recorded as
[architecture decision records](docs/adr/).

## Building locally

Requirements: JDK 17 or newer (CI uses 21) and the Android SDK (set `ANDROID_HOME` or
`sdk.dir` in `local.properties`).

```bash
./gradlew check
```

`check` runs formatting (Spotless/ktlint), Android Lint, unit tests and the binary-compatibility
check. See [CONTRIBUTING.md](CONTRIBUTING.md) for the full workflow.

## Contributing

Contributions are welcome once the core API settles. Please read [CONTRIBUTING.md](CONTRIBUTING.md)
and the [Code of Conduct](CODE_OF_CONDUCT.md). Report security issues privately as described in
[SECURITY.md](SECURITY.md).

## Acknowledgements

youkoso is inspired by the feature set of
[`welcome-android`](https://github.com/stephentuso/welcome-android) by Stephen Tuso (Apache-2.0).
It is an independent reimplementation: no code from that project is used.

## License

```
Copyright 2026 Anjaly Sajeev

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
