1. Things I learned:
-> 1. Column
   Main axis = vertical → control spacing along this axis with verticalArrangement.
   Cross axis = horizontal → control alignment across this axis with horizontalAlignment.

   2. Row
   Main axis = horizontal → control spacing along this axis with horizontalArrangement.
   Cross axis = vertical → control alignment across this axis with verticalAlignment.

-> (alt + j) is used to select similar strings; simply select a string and then alt plus j

-> custom typographies can be created outside the Typography
-> using the Pager composable for creating beautiful pager ui

-> coroutine: it is a lightweight, cooperative thread-like unit of asynchronous work that can be paused, and resumed without blocking the main thread.
    -> crucial to keep your Ui responsive while performing long-running or background tasks like animations, network calls, or scrolling
    -> it lets you run code asynchronously without freezing the UI
    -> LaunchedEffect ties coroutines to Compose lifecycle, so coroutines run when certain state changes and cancel when no longer needed
    -> it prevents UI freezes by offloading work the main thread
    -> using the suspend keyword:
    -> StateFlow enables state observation;
    -> example use-cases: image loading & caching, periodic background refreshing, like comment section, video playback, socket connections or
       REST calls, typing indicators and presence, large file transfers, database sync,
       lading of product images, background notification push handling, media upload/download, secure authentication


-> LaunchedEffect: runs coroutine-based side effects that response to state changes in a lifecycle-aware manner.
    -> it reacts to state changes asynchronously, executes coroutines with lifecycle awareness
    -> performs automatic cancellation and restart on key updates
    -> this ensures UI stays in sync with state changes without memory leaks or redundant coroutine runs
    -> react counterpart is useEffect; python counterpart is async

-> Here is an explanation addressing each of your questions in the context of your Compose + ViewModel setup:

   ***

   ## 1. Why is `val state = rememberPagerState` declared inside `ArtApp`? Should it be moved to ViewModel?

   `val state = rememberPagerState(...)` holds UI state specifically related to the pager's internal behavior (e.g., current scroll position, page count) that is tightly coupled to Compose UI and lifecycle. This state is:

   - **Local UI state**, held in Compose's memory to survive recompositions.
   - Directly related to UI components behavior, not business logic or app data.

   **Should it move to ViewModel?**
   No, generally `PagerState` and other UI controller states should remain inside the composable layer because:

   - They deal with **UI interaction & animation details** tightly bound to the composable lifecycle.
   - Moving it to ViewModel would mix UI controller state with app data and complicate separation of concerns.
   - The ViewModel should contain **pure data and business state** like your `uiState` (`MasterpieceUiState`).

   So keeping `rememberPagerState` in the composable is best practice.

   ***

   ## 2. Purpose of the two `LaunchedEffect`s

   - **First `LaunchedEffect(uiState.currentIndex)`**
     When the ViewModel's `currentIndex` changes, this effect scrolls the pager to that page immediately. It keeps the **pager UI in sync** with the central UI state from the ViewModel.

   - **Second `LaunchedEffect(state.currentPage)`**
     When the user scrolls the pager manually, this effect updates the ViewModel's `currentIndex` if it differs. This makes sure the central UI state reflects **user interaction**, maintaining synchronization in the other direction.

   Together, these two form a **two-way sync between pager UI state and ViewModel state**.

   ***

   ## 3. Alternatives to `LaunchedEffect`

   - `rememberCoroutineScope()` lets you manually launch coroutines tied to the composable lifecycle for **event-triggered asynchronous work** (like button taps) rather than declarative, dependency-driven effects.
   - `DisposableEffect` is for imperative setup/cleanup side effects, not suitable for suspend functions like those run by `LaunchedEffect`.
   - Async work can also be run in ViewModel using `viewModelScope` when it doesn't depend on UI composition.

   ***

   ## 4. Alternatives to `StateFlow<MasterpieceUiState>` and its necessity

   - **`StateFlow`** is a good choice for exposing **observable, immutable, reactive state** from the ViewModel, seamlessly integrated with Compose's `collectAsState()`.
   - Alternatives:
     - `LiveData`: Traditional Android observable; still supported but less idiomatic for Compose.
     - `MutableState` and `mutableStateOf`: Works well for volatile UI state but less scalable for complex or shared app state.
     - Other reactive streams (RxJava, etc.) with adapters exist but add complexity.

   **Should you always use StateFlow?**
   It's not mandatory but strongly recommended for production apps using Compose + Kotlin coroutines because:

   - It supports **unidirectional data flow** and clean **state immutability**.
   - Integrates naturally with Compose's lifecycle and recomposition model.
   - Enables easy testing and state separation from UI.




2. Useful links
-> pager


3. Upgrades and improvements:
-> adapting for different screensizes

