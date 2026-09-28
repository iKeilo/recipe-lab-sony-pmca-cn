# Localization preparation

This project currently keeps almost all user-facing text in Java constants. The
first localization step is therefore deliberately additive: `res/values-zh-rCN/`
contains a stable Chinese key set, while the rendering code remains unchanged.
This keeps the camera protocol, recipe data, and existing unit-test expectations
untouched while the strings are migrated in small, reviewable batches.

## Scope

Translate these surfaces first, in this order:

1. Main HUD labels and the active/preview badges (`Params.ROW_NAME`,
   `MainActivity.render`).
2. Navigation legends and picker prompts (`HintBar`, `PickerView`, `MenuView`,
   `PromptView`).
3. Toasts and write/error messages (`MainActivity`, `Params`).
4. Developer-only diagnostics (`DevTools`) after the production UI is complete.
5. Recipe and brand names only where a Chinese display label is useful. Keep the
   underlying recipe key/name stable because favourites are persisted by name.

## Keep unchanged

The following are identifiers or data formats, not translatable UI text:

- `RAW`, `JPEG`, `JPG`, `DRO`, `PE`, `CS`, `AEL`, `Fn`, `C1`, and camera key names.
- Recipe keys, group keys, `samples.txt`, settings-slot IDs, and values written to
  the camera.
- `Recipes.STYLE_NAMES`, `Recipes.PE_KEYS`, and sub-effect keys used by the
  settings codec.

## Migration rules

- Add a string key to both `res/values/strings.xml` and the locale file before
  changing Java code to call `getString(...)`.
- Use positional format arguments (`%1$s`, `%1$d`) for messages assembled with
  runtime values; never concatenate translated fragments around a number.
- Keep labels short because the A6000 UI has a fixed-width canvas. Verify every
  changed screen on the camera or an equivalent 320px-wide preview.
- Do not translate persisted recipe names in place. Add a display-label mapping
  and continue storing the canonical English name in favourites.

## Current inventory

| Area | Source | Preparation status |
|---|---|---|
| Android app name | `res/values/strings.xml` | Chinese resource added |
| HUD field labels | `Params.java`, `MainActivity.java` | Keys added; wiring pending |
| Navigation legends | `HintBar.java`, `PickerView.java`, `MenuView.java`, `PromptView.java` | Keys reserved; wiring pending |
| Toasts and prompts | `MainActivity.java`, `Params.java` | Keys reserved; wiring pending |
| Favourites | `Favourites.java` | Chinese title/hint added; persistence format unchanged |
| Recipe/brand catalog | `Recipes.java` | Canonical names unchanged; display mapping pending |
| Developer tools | `DevTools.java` | Deferred until production UI is migrated |

## Suggested next change

Extract the HUD labels first. `Params.ROW_NAME` can be split into canonical
field IDs plus a localized display array supplied by the activity. This is the
smallest migration that exercises locale selection without changing recipe
encoding or camera writes.
