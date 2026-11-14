# ADR-0004: Binary-compatibility validation with BCV, wired manually for Android

- **Status:** Accepted
- **Date:** 2026-11-14
- **Deciders:** Anjaly Sajeev (maintainer)

## Context

A library must not break apps that compile against it. Every published module therefore records
its public binary API in `api/<module>.api`, and CI fails when the compiled API differs from that
file. Two tools can do this:

1. **Kotlin's built-in ABI validation** (`kotlin { abiValidation {} }`), the designated successor
   of BCV.
2. **kotlinx Binary Compatibility Validator (BCV)**, a standalone Gradle plugin, now in maintenance
   mode.

The project uses AGP 9, whose *built-in Kotlin* support compiles Kotlin in Android modules without
applying the `org.jetbrains.kotlin.android` plugin.

## What was tested (Sprint 1, Kotlin 2.4.21, AGP 9.5.1, BCV 0.18.2)

- Built-in ABI validation is still annotated `@ExperimentalAbiValidation` in Kotlin 2.4.21. It
  worked for the JVM module (`youkoso-core`). For the Android modules, the dump task wrote nothing,
  and `updateKotlinAbi`/`checkKotlinAbi` failed with "Cannot query the value of this provider".
  Setting `binariesSource = NON_TEST_COMPILATIONS` did not change this.
- The BCV plugin, applied to an Android module, created **no tasks**. It only configures Android
  modules that apply `org.jetbrains.kotlin.android`, which AGP 9's built-in Kotlin does not.

## Decision

- Use **BCV 0.18.2** for every published module, with the same task names everywhere: `apiDump`
  and `apiCheck`. `apiCheck` is part of `check`.
- JVM module: apply the BCV plugin normally.
- Android modules: register BCV's own public task types (`KotlinApiBuildTask`,
  `KotlinApiCompareTask`) against the `compileReleaseKotlin` output in the `build-logic` convention
  plugin. This is the same wiring BCV does itself for `kotlin-android` modules.
- Verified by adding a temporary public class to `youkoso-core` and `youkoso-compose`: `apiCheck`
  failed in both and printed the new signature. It passed again once the class was removed.

## Consequences

- One consistent workflow (`apiDump`/`apiCheck`) across all modules from day one.
- About 30 lines of build-logic to maintain (`AbiConventions.kt`), relying on BCV's public task
  classes and AGP's `compileReleaseKotlin` task name.
- BCV is in maintenance mode. **Revisit** when Kotlin's built-in ABI validation is stable and
  supports AGP's built-in Kotlin. Migrating then is mechanical, and the `.api` file format is
  compatible.

## Alternatives considered

- **Opt out of AGP's built-in Kotlin** to make BCV work automatically: rejected, because AGP is
  removing the opt-out.
- **Wait and ship without ABI checks:** rejected, because API checks must run from the first public
  declaration.
- **Metalava:** powerful, but heavyweight for a project of this size.
