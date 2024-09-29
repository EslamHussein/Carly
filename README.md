![Carly](/screenshots/carly_logo.png)

# Vehicle Management App

## Project Overview

This Android app enables users to manage their vehicles by allowing them to create, view, and delete
vehicles. The app dynamically displays features specific to the selected vehicle. Users can interact
with vehicle data such as brand, series, build year, and fuel type, and view specific vehicle
features like Diagnostics, Live Data, and Battery Check.

---

## Screenshots

<table>
    <tr>
    <td><img src="/screenshots/Screenshot_dashboard_add_new_car.png" width="200"/></td>
    <td><img src="/screenshots/Screenshot_dashboard_with_selected_car.png" width="200"/></td>
    <td><img src="/screenshots/Screenshot_your_cars.png" width="200"/></td>

  </tr>
  <tr>
    <td><img src="/screenshots/Screenshot_add_new_car_brand_step.png" width="200"/></td>
    <td><img src="/screenshots/Screenshot_add_new_car_series_step.png" width="200"/></td>
    <td><img src="/screenshots/Screenshot_add_new_car_build_year_step.png" width="200"/></td>
    <td><img src="/screenshots/Screenshot_add_new_car_fuel_step.png" width="200"/></td>
  </tr>

 
</table>

---

## Features Implemented

### 1. Vehicle Creation Flow

- **Brand Selection**: Users can select a vehicle brand from the provided list (BMW, Mercedes, Audi,
  Volkswagen).
- **Series Selection**: After selecting a brand, users can choose a series from the available
  options.
- **Build Year Selection**: Users can select the vehicle's build year based on the chosen series.
- **Fuel Type Selection**: Users can choose the vehicle’s fuel type from Diesel, Gasoline, Hybrid,
  Electric, or Other.
- **Search Functionality**: Each selection screen includes a search bar for filtering available
  options for quick and easy selection.

### 2. Vehicle List and Management

- **Vehicle List**: Displays a list of vehicles the user has added, showing the brand, series, year,
  and fuel type.
- **Vehicle Selection**: Users can select a vehicle from the list. The selected vehicle is
  highlighted for better identification.
- **Delete Functionality**: Users can delete any vehicle except the currently selected vehicle.

### 3. Dashboard

- **No Vehicles**: If no vehicles have been added, the dashboard will display an "Add Vehicle"
  button.
- **With Vehicles**: If vehicles have been added, the dashboard displays the details (brand, series,
  year, fuel type) of the currently selected vehicle, along with a default vehicle image.
- **Features List**: Dynamically displays a list of features specific to the selected vehicle.

---

## Project Architecture

- **MVI**: The MVI (Model-View-Intent) with Clean architecture.

---

## Package structure

##### In this project, the package structure is divided into the following packages using feature per package:

- **di** - contains the dependency injection modules for the feature.
- **domain** - contains the business logic classes for the feature.
- **rp** - contains the repository classes for the feature.
- **vm** - contains the view model classes(view model - state - actions - sideffect) for the
  feature.
- **ui** - contains the composable functions for the feature.

Note: The `core` package contains common classes that are shared across various features, such as
database entities and data sources, as these are used in multiple parts of the application.

---

## Tech Stack

- **Kotlin**: Used for Android development.
- **Jetpack Compose**: Used for building the UI.
- **Room**: For local database storage and management.
- **Kotlin Coroutines**: For handling background operations.
- **Koin**: For dependency injection.
- **kotlin-serialization-**: For json serialization and deserialization.

---

## DB Digram

![alt text](/screenshots/carly_db_diagram.png)

---



## Setup Instructions
