# TODO: Fix App Crashing on Profile and Models Button Clicks

## Issues Identified
- **Adapter ID Mismatches**: RecyclerView adapters are using incorrect view IDs that don't match the layout XML files, causing `findViewById` to return null and leading to NullPointerExceptions when setting text.
- **Incorrect Imports**: ModelsActivity has wrong import for CarDetailsActivity.
- **Image Loading Issues**: CarDetailsActivity image loading cases don't match sample car data.
- **Data Parsing**: Potential issues in ProfileActivity data loading from SharedPreferences.

## Tasks
- [x] Fix CarAdapter view IDs to match item_car.xml
- [x] Fix PurchaseAdapter view IDs to match item_purchase.xml
- [x] Fix BookingAdapter view IDs to match item_booking.xml
- [x] Correct import in ModelsActivity for CarDetailsActivity
- [x] Update image loading logic in CarDetailsActivity
- [ ] Test Profile and Models activities after fixes
- [ ] Verify RecyclerViews display data correctly
