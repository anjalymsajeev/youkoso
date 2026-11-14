# ADR-0003: Hoisted state holder and pure reducer instead of a ViewModel

- **Status:** Accepted
- **Date:** 2026-11-14
- **Deciders:** Anjaly Sajeev (maintainer)

## Context

The initial brief asked for MVVM and Clean Architecture. Those patterns are designed for
applications. A UI library has different constraints: it must work inside any host (Activity,
navigation destination, bottom sheet, dialog), must not dictate the app's architecture, and should
expose the smallest possible API.

## Decision

- **No ViewModel in the public API.** Screen state lives in a plain, saveable state holder
  (`OnboardingState`, created with `rememberOnboardingState()`) that the caller can hoist. This is
  the same pattern as Compose's `PagerState`, `ScrollState` and `DrawerState`.
- **Unidirectional data flow.** UI interactions become actions. A pure function
  `(state, action, policy) -> (new state, effects)` in `youkoso-core` computes the next state and
  the side effects (events, result). The UI renders the state and forwards the effects to callbacks.
- **No use-case classes per action.** The reducer is the domain logic.
- Clean Architecture's *dependency rule* is kept at module level: `youkoso-core` is the domain,
  the Compose modules are presentation, and `youkoso-datastore` is a data adapter behind an
  interface defined in core (see ADR-0001).

## Consequences

- The library works in any composable host without needing a `ViewModelStoreOwner`.
- The navigation rules can be tested exhaustively as plain function calls on the JVM.
- Apps that use ViewModels can still keep their own data in one and pass it to youkoso.
- Navigation state survives process death through `rememberSaveable`. Page content that has its
  own state must also use `rememberSaveable`; this will be documented.

## Alternatives considered

- **Library-owned ViewModel:** hides state, ties the library to a `ViewModelStoreOwner`, and
  conflicts with hosting in sheets and dialogs.
- **Use-case classes and repositories:** adds indirection around what are pure functions, with no
  testability gain.
