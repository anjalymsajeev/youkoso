# Architecture decision records

Significant technical decisions are recorded here so future contributors can see what was decided
and why, including the alternatives that were rejected.

| ADR | Title | Status |
|---|---|---|
| [0001](0001-module-structure.md) | Module structure and dependency rules | Accepted |
| [0002](0002-compose-first-ui.md) | Compose-first UI; View-based apps integrate through an Activity | Accepted |
| [0003](0003-state-holder-not-viewmodel.md) | Hoisted state holder and pure reducer instead of a ViewModel | Accepted |
| [0004](0004-abi-validation.md) | Binary-compatibility validation with BCV, wired manually for Android | Accepted |

## Writing a new ADR

Copy the structure of an existing record (Status, Context, Decision, Consequences, Alternatives),
number it sequentially, and add it to the table above in the same pull request as the change it
describes. Accepted ADRs are not rewritten. A new ADR supersedes an old one, and the old one's
status is changed to "Superseded by NNNN".
