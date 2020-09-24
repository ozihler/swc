# Exercise 2 - Separation of Concerns, Primitive Obsession, Emerging SW Design
* start on 1-automated-refactoring-solved
* start separation of concerns: 
- split DTO from API from DTO for Resource
```
    public MarsWeatherDto createDtoFrom(Map<String, Object> object) {

        String season = seasonFrom(object);
        List<Double> temperaturesInFahrenheit = temperaturesInFahrenheitFrom(object);
        var averageTemperatureInFahrenheit = averageOf(temperaturesInFahrenheit);
        double averageTemperatureInCelsius = asCelsius(averageTemperatureInFahrenheit);

        // Presentation logic
        MarsWeatherDto marsWeather = new MarsWeatherDto();
        marsWeather.location.latitude = INSIGHT_LANDING_SITE_LATITUDE;
        marsWeather.location.longitude = INSIGHT_LANDING_SITE_LONGITUDE;
        marsWeather.season = season;
        marsWeather.averageTemperatureInCelsius = averageTemperatureInCelsius;

        return marsWeather;
    }
```
- add Season domain object (Parallel Change Refactoring, create class, create field, bind to field)
- replace String season = seasonFrom(object) with var season1 = new Season(seasonFrom(object)); String season = season1.value()
- inline season and rename season1 to season
```
    public MarsWeatherDto createDtoFrom(Map<String, Object> object) {
        float latitude = INSIGHT_LANDING_SITE_LATITUDE;
        float longitude = INSIGHT_LANDING_SITE_LONGITUDE;
        Season season = new Season(seasonFrom(object));
        List<Double> temperaturesInFahrenheit = temperaturesInFahrenheitFrom(object);
        var averageTemperatureInFahrenheit = averageOf(temperaturesInFahrenheit);
        double averageTemperatureInCelsius = asCelsius(averageTemperatureInFahrenheit);

        // Presentation logic
        MarsWeatherDto marsWeather = new MarsWeatherDto();
        marsWeather.location.latitude = latitude;
        marsWeather.location.longitude = longitude;
        marsWeather.season = season.value();
        marsWeather.averageTemperatureInCelsius = averageTemperatureInCelsius;

        return marsWeather;
    }

```