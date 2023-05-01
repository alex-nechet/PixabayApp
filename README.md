# PixabayApp

**Architecture and tech stack:**

- Arch: MVVM with clean architecture approach
- DI: Koin
- Data Storage : Room
- Async image loading : Coil
- Async requests : Retrofit + Okhttp (familiar with ktor but have more experience with Retrofit)
- JSON Parsing : Moshi (picked because of support and because its kotlin written)
- Work with UI : ViewBinding

**Remarks:**

- obfuscation (proguard files) is not done in favor of time management
- No Compose - because xml approach is more time effective in this case
- Koin -> Hilt - because of timing and because koin is kotlin based (might be important for KMM)
- Securing API KEY and BASE URL is very basic
- Versions controlling for gradle is not done
- Its possible to make physical resize of images according to data from API (for example for large
  image this parameter  "imageHeight":3240" could be recalculated and scaled to real device
  screen size)

