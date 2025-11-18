# TODO: Fix Profile and Models Button Crashing

## Information Gathered
- The app has activities in `com.example.cruisemastersmad.activities` that import from `com.example.cruisemastersmad.ui`
- The UI-related files (models, adapters, viewmodels, activities) are currently in `app/src/main/java/ui` with package `com.example.cruisemastersmad.ui`
- The directory structure (`ui`) doesn't match the package name (`com.example.cruisemastersmad.ui`), causing import failures and resulting in white screen and app crashes when clicking profile or models buttons
- Activities like `HomeActivity` start intents for `ModelsActivity` and `ProfileActivity`, but these activities fail to load due to unresolved imports

## Plan
- Move all files from `app/src/main/java/ui` to `app/src/main/java/com/example/cruisemastersmad/ui` to match the package structure
- This will ensure the package `com.example.cruisemastersmad.ui` corresponds to the correct directory path
- No code changes needed as the package declarations are already correct

## Dependent Files to be edited
- None (file move operation only)

## Followup steps
- Build the Android project to verify no compilation errors
- Test the profile and models buttons to ensure they navigate correctly without crashing
- Run the app on an emulator/device to confirm the fix

## Status
- [ ] Move UI files to correct directory structure
- [ ] Build project
- [ ] Test profile and models navigation
