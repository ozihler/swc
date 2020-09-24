# Exercise 2 - Separation of Concerns, Primitive Obsession, Emerging SW Design
* Introduce domain logic
* start on 1-automated-refactoring-solved
* start separation of concerns: 

1.
- split DTO from API from DTO for Resource
```
    public MarsWeatherDto createDtoFrom(Map<String, Object> object) {

        String season = seasonFrom(object);
        List<Double> temperaturesInFahrenheit = temperaturesInFahrenheitFrom(object);
        var averageTemperatureInFahrenheit = averageOf(temperaturesInFahrenheit);
        double averageTemperatureInCelsius = inCelsius(averageTemperatureInFahrenheit);

        // Presentation logic
        MarsWeatherDto marsWeather = new MarsWeatherDto();
        marsWeather.location.latitude = INSIGHT_LANDING_SITE_LATITUDE;
        marsWeather.location.longitude = INSIGHT_LANDING_SITE_LONGITUDE;
        marsWeather.season = season;
        marsWeather.averageTemperatureInCelsius = averageTemperatureInCelsius;

        return marsWeather;
    }
```
2.
- add Season domain enum and switch statement in forName that returns the correct enum (Parallel Change)
- replace String season = seasonFrom(object) with Season season1 = Season.forName(seasonFrom(object)); season = season1.name()
- inline season and rename season1 to season
```
public enum Season {
    Spring, Summer, Fall, Winter;

    public static Season forName(String season) {
        return switch (season.toLowerCase()) {
            case "spring" -> Spring;
            case "summer" -> Summer;
            case "fall" -> Fall;
            case "winter" -> Winter;
            default -> throw new RuntimeException("Could not find season with name " + season);
        };
    }
}

    public MarsWeatherDto createDtoFrom(Map<String, Object> object) {
        float latitude = INSIGHT_LANDING_SITE_LATITUDE;
        float longitude = INSIGHT_LANDING_SITE_LONGITUDE;
        Season season = Season.forName(seasonFrom(object));
        List<Double> temperaturesInFahrenheit = temperaturesInFahrenheitFrom(object);
        var averageTemperatureInFahrenheit = averageOf(temperaturesInFahrenheit);
        double averageTemperatureInCelsius = inCelsius(averageTemperatureInFahrenheit);


        MarsWeatherDto marsWeather = new MarsWeatherDto();
        marsWeather.location.latitude = latitude;
        marsWeather.location.longitude = longitude;
        marsWeather.season = season.name();
        marsWeather.averageTemperatureInCelsius = averageTemperatureInCelsius;

        return marsWeather;
    }

```
3. Introduce FahrenheitTemperature and FahrenheitTemperatures
- extract and move averageOf/inCelsius to FahrenheitTemperatures 
```
public class FahrenheitTemperatures {
    private List<FahrenheitTemperature> temperatures;

    public FahrenheitTemperatures(List<FahrenheitTemperature> temperatures) {
        this.temperatures = temperatures;
    }

    public static FahrenheitTemperatures from(List<Double> temperaturesInFahrenheitFrom) {
        return new FahrenheitTemperatures(
                temperaturesInFahrenheitFrom.stream()
                        .map(FahrenheitTemperature::new)
                        .collect(toList())
        );
    }

    public double average() {
        return this.temperatures.stream()
                .mapToDouble(FahrenheitTemperature::value)
                .average()
                .orElse(0.0);
    }

    public double inCelsius() {
        // Degree Celsius = (degree Fahrenheit-32)*(5/9)
        return (average() - 32d) * (5 / 9d);
    }
}

public class FahrenheitTemperature {
    private double temperature;

    public FahrenheitTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double value() {
        return temperature;
    }
}

public class MarsWeatherDtoFactory {
    // ...
    public MarsWeatherDto createDtoFrom(Map<String, Object> object) {
        float latitude = INSIGHT_LANDING_SITE_LATITUDE;
        float longitude = INSIGHT_LANDING_SITE_LONGITUDE;
        Season season = Season.forName(seasonFrom(object));

        List<Double> temperaturesInFahrenheit = temperaturesInFahrenheitFrom(object);
        FahrenheitTemperatures temperatures = FahrenheitTemperatures.from(temperaturesInFahrenheit);
        double averageTemperatureInCelsius = temperatures.inCelsius();

        MarsWeatherDto marsWeather = new MarsWeatherDto();
        marsWeather.location.latitude = latitude;
        marsWeather.location.longitude = longitude;
        marsWeather.season = season.name();
        marsWeather.averageTemperatureInCelsius = averageTemperatureInCelsius;

        return marsWeather;
    }
}
```

4. Introduce Location/Latitude/Longitude
```
public class Latitude {
    private final float value;

    public Latitude(float value) {
        if (value < -90 || value > 90) {
            throw new IllegalLatitudeException(format("Latitude cannot be %s", value));
        }
        this.value = value;
    }

    public float getFloatValue() {
        return value;
    }
}

public class Longitude {
    private final float value;

    public Longitude(float value) {
        if (value < -180 || value > 180) {
            throw new IllegalLongitudeException(format("Longitude cannot be %s", value));
        }
        this.value = value;
    }

    public float getFloatValue() {
        return value;
    }
}

public class Location {
    private final Latitude latitude;
    private final Longitude longitude;

    public Location(Latitude latitude, Longitude longitude) {
        if (latitude == null || longitude == null) {
            throw new IllegalLocationException(format("Both latitude and longitude need to be set! lat: %s , lon: %s", latitude, longitude));
        }
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Latitude getLatitude() {
        return latitude;
    }

    public Longitude getLongitude() {
        return longitude;
    }
}

public class MarsWeatherDtoFactory { 
    // ...

    public MarsWeatherDto createDtoFrom(Map<String, Object> object) {
        List<Double> temperaturesInFahrenheit = temperaturesInFahrenheitFrom(object);
        String seasonData = seasonFrom(object);

        Location location = new Location(
                new Latitude(INSIGHT_LANDING_SITE_LATITUDE),
                new Longitude(INSIGHT_LANDING_SITE_LONGITUDE)
        );
        Season season = Season.forName(seasonData);
        FahrenheitTemperatures temperatures = FahrenheitTemperatures.from(temperaturesInFahrenheit);

        MarsWeatherDto marsWeather = new MarsWeatherDto();
        marsWeather.location.latitude = location.getLatitude().getFloatValue();
        marsWeather.location.longitude = location.getLongitude().getFloatValue();
        marsWeather.season = season.name();
        marsWeather.averageTemperatureInCelsius = temperatures.inCelsius();

        return marsWeather;
    }
}
```
5. Introduce MarsWeather
```
public class MarsWeather {
    private final Location location;
    private final Season season;
    private final FahrenheitTemperatures temperatures;

    public MarsWeather(
            Location location,
            Season season,
            FahrenheitTemperatures temperatures) {
        this.location = location;
        this.season = season;
        this.temperatures = temperatures;
    }

    public Location getLocation() {
        return location;
    }

    public Season getSeason() {
        return season;
    }

    public FahrenheitTemperatures getTemperatures() {
        return temperatures;
    }
}

public class MarsWeatherDtoFactory {
    // ...
    public MarsWeatherDto createDtoFrom(Map<String, Object> object) {
        List<Double> temperaturesInFahrenheit = temperaturesInFahrenheitFrom(object);
        String seasonData = seasonFrom(object);

        Location location = new Location(
                new Latitude(INSIGHT_LANDING_SITE_LATITUDE),
                new Longitude(INSIGHT_LANDING_SITE_LONGITUDE)
        );
        Season season = Season.forName(seasonData);
        FahrenheitTemperatures temperatures =
                FahrenheitTemperatures
                        .from(temperaturesInFahrenheit);

        MarsWeather marsWeather = new MarsWeather(
                location,
                season,
                temperatures
        );

        MarsWeatherDto marsWeatherDto = new MarsWeatherDto();
        marsWeatherDto.location.latitude = marsWeather.getLocation().getLatitude().getFloatValue();
        marsWeatherDto.location.longitude = marsWeather.getLocation().getLongitude().getFloatValue();
        marsWeatherDto.season = marsWeather.getSeason().name();
        marsWeatherDto.averageTemperatureInCelsius = marsWeather.getTemperatures().inCelsius();

        return marsWeatherDto;
    }
}
```
6. extract public creation methods for DTO and DomainObject and inline createDtoFrom
```
public class MarsWeatherDtoFactory {
    // ...
    public MarsWeather createDomainObjectFrom(Map<String, Object> object) {
        List<Double> temperaturesInFahrenheit = temperaturesInFahrenheitFrom(object);
        String seasonData = seasonFrom(object);

        Location location = new Location(
                new Latitude(INSIGHT_LANDING_SITE_LATITUDE),
                new Longitude(INSIGHT_LANDING_SITE_LONGITUDE)
        );
        Season season = Season.forName(seasonData);
        FahrenheitTemperatures temperatures =
                FahrenheitTemperatures
                        .from(temperaturesInFahrenheit);

        MarsWeather marsWeather = new MarsWeather(
                location,
                season,
                temperatures
        );
        return marsWeather;
    }

    public MarsWeatherDto createDtoFrom(MarsWeather marsWeather) {
        MarsWeatherDto marsWeatherDto = new MarsWeatherDto();
        marsWeatherDto.location.latitude = marsWeather.getLocation().getLatitude().getFloatValue();
        marsWeatherDto.location.longitude = marsWeather.getLocation().getLongitude().getFloatValue();
        marsWeatherDto.season = marsWeather.getSeason().name();
        marsWeatherDto.averageTemperatureInCelsius = marsWeather.getTemperatures().inCelsius();

        return marsWeatherDto;
    }
    //....
}

public class MarsWeatherResource {
    // ...

    @GetMapping("/api/mars-weather")
    public ResponseEntity<MarsWeatherDto> getCurrentMarsWeather() throws IOException {
        String url = String.format("%s?api_key=%s&feedtype=json&ver=1.0", baseUrl, api_key);

        Map<String, Object> object = new HttpRequest<Map<String, Object>>(url).getAsType(new TypeReference<>() {});
        
        MarsWeatherDtoFactory marsWeatherDtoFactory = new MarsWeatherDtoFactory();
        
        MarsWeather marsWeather = marsWeatherDtoFactory.createDomainObjectFrom(object);

        MarsWeatherDto dto = marsWeatherDtoFactory.createDtoFrom(marsWeather);

        return ResponseEntity.ok(dto);
    }
    // ...
}
```
7. Introduce MarsWeatherFactory and move createDomainObjectFrom there
- also move lat/long constants there
- also move all private methods needed there
```
public class MarsWeatherFactory {
    public static final float INSIGHT_LANDING_SITE_LATITUDE = 4.5024f;
    public static final float INSIGHT_LANDING_SITE_LONGITUDE = 135.6234f;

    public MarsWeather createDomainObjectFrom(Map<String, Object> object) {
        List<Double> temperaturesInFahrenheit = temperaturesInFahrenheitFrom(object);
        String seasonData = seasonFrom(object);

        Location location = new Location(
                new Latitude(INSIGHT_LANDING_SITE_LATITUDE),
                new Longitude(INSIGHT_LANDING_SITE_LONGITUDE)
        );
        Season season = Season.forName(seasonData);
        FahrenheitTemperatures temperatures =
                FahrenheitTemperatures
                        .from(temperaturesInFahrenheit);

        MarsWeather marsWeather = new MarsWeather(
                location,
                season,
                temperatures
        );
        return marsWeather;
    }
    // ...
}

public class MarsWeatherDtoFactory {
    public MarsWeatherDto createDtoFrom(MarsWeather marsWeather) {
        MarsWeatherDto marsWeatherDto = new MarsWeatherDto();
        marsWeatherDto.location.latitude = marsWeather.getLocation().getLatitude().getFloatValue();
        marsWeatherDto.location.longitude = marsWeather.getLocation().getLongitude().getFloatValue();
        marsWeatherDto.season = marsWeather.getSeason().name();
        marsWeatherDto.averageTemperatureInCelsius = marsWeather.getTemperatures().inCelsius();

        return marsWeatherDto;
    }
}
```