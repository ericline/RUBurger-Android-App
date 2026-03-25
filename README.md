# RU Burger - Android Food Ordering Application

A full-featured Android food ordering application for a restaurant chain, allowing customers to browse a menu, customize items, build orders with combo deals, and manage order history.

## What It Is

A mobile point-of-sale application with 8 interconnected Activities covering the full ordering workflow: browsing menus (burgers, sandwiches, beverages, sides), customizing items with toppings and sizes, assembling combo meals, reviewing a live cart with tax calculations, and archiving completed orders.

## Technologies

- Kotlin/Java, Android SDK (compileSdk 35, minSdk 24)
- Gradle (Kotlin DSL) build system
- AndroidX libraries: AppCompat, ConstraintLayout, RecyclerView, Material Design
- UI components: RecyclerView with custom Adapter/ViewHolder, Spinner, RadioButton, CheckBox, AlertDialog, ImageButton
- Design patterns: Singleton (Order, Archive), Adapter, Strategy (polymorphic MenuItem pricing), MVC
- Serializable objects for inter-Activity data passing via Intents

## Summarized Bullets

- Built an Android food ordering app with 8 Activities and a custom RecyclerView Adapter handling real-time price updates, size selection spinners, and confirmation dialogs for 15+ beverage options
- Architected a polymorphic MenuItem class hierarchy (Burger, Sandwich, Beverage, Side, Combo) with Strategy pattern pricing and Singleton-based order/archive management
- Implemented dynamic UI with 5+ event listeners per Activity (RadioButtons, CheckBoxes, Spinners) driving real-time subtotal recalculation and 6.625% tax computation across the ordering workflow
- Designed a multi-step combo builder passing Serializable objects between Activities via Intents, with image asset swapping and ScaleTransition hover animations for a polished UX
