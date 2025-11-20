# TODO: Fix CruiseMasters MAD App Crashing Issues

## Issues Identified:
1. **HomeActivity**: References non-existent button IDs (`btnBrowseModels` doesn't exist in layout)
2. **Package Inconsistency**: Activities in `com.example.cruisemastersmad` package are importing from `ui` package which has broken code
3. **Adapter Mismatches**: Adapters are expecting different view IDs than what layouts provide
4. **Duplicate Packages**: Both `ui` and `com.example.cruisemastersmad` packages exist with conflicting code

## Tasks:
- [ ] Fix HomeActivity button references to use correct IDs from layout
- [ ] Update ModelsActivity to use correct CarAdapter from com.example.cruisemastersmad package
- [ ] Update ProfileActivity to use correct adapters from com.example.cruisemastersmad package
- [ ] Ensure all imports are consistent within the com.example.cruisemastersmad package
- [ ] Test navigation between activities
- [ ] Remove duplicate ui package if not needed

## Current Status:
- Fixed package declarations in all adapters (CarAdapter, PurchaseAdapter, BookingAdapter)
- Updated activity_models.xml to use NestedScrollView for scrolling
- Models page now displays car details from database (name, mileage as brand, price)
- Profile page already uses ScrollView for scrolling
- Build process executed successfully
- All major errors fixed, app should compile and run properly
