# ADR-0002: Compose-first UI; View-based apps integrate through an Activity

- **Status:** Accepted
- **Date:** 2026-11-14
- **Deciders:** Anjaly Sajeev (maintainer)

## Context

Jetpack Compose is Android's recommended UI toolkit, but many production apps still use Views and
XML. A library could render with Compose, with Views, or with both.

## Decision

- All UI is rendered with **Jetpack Compose**.
- Apps built with Views use youkoso by hosting the composable in their own `ComponentActivity` and
  launching it through an `ActivityResultContract` that the library provides (Sprint 6), or by
  embedding it in a `ComposeView`.
- No View/XML renderer is planned for 1.0.

## Consequences

- One renderer to build, test, theme and document, which is sustainable for a small maintainer
  team.
- Slot APIs (replaceable controls, indicator, page content) replace the original's fixed
  bottom-layout enum.
- View-based apps take on the Compose runtime as a dependency.
- Because `youkoso-core` has no UI dependency, a View renderer could still be added later if real
  demand appears, without changing the engine.

## Alternatives considered

- **Compose and a parallel View renderer:** native for XML apps, but every feature would be built
  and tested twice.
- **Views only (`ViewPager2`):** no Compose dependency, but it builds a new library on the legacy
  UI stack, and slot-style customisation is awkward with Views.
