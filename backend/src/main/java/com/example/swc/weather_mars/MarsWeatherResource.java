package com.example.swc.weather_mars;

import com.example.swc.common.HttpRequest;
import com.example.swc.domain.Location;
import com.example.swc.domain.Latitude;
import com.example.swc.domain.Longitude;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MarsWeatherResource {

    private final String api_key;
    private final String baseUrl;

    /**
     * https://api.nasa.gov/assets/insight/InSight%20Weather%20API%20Documentation.pdf
     * AT.av = Average Temperature in Fahrenheit
     * Season = winter, spring, summer, fall
     *
     * @param api_key
     * @param baseUrl
     */
    public MarsWeatherResource(
            @Value("${mars.weather.api.key}") String api_key,
            @Value("${mars.weather.api.baseUrl}") String baseUrl
    ) {
        this.api_key = api_key;
        this.baseUrl = baseUrl;
    }

    @GetMapping("/api/mars-weather")
    public ResponseEntity<MarsWeatherDto> getCurrentMarsWeather() throws IOException {
        //TODO FIXME refactor to Ports/Adapters

        String url = String.format("%s?api_key=%s&feedtype=json&ver=1.0", baseUrl, api_key);

        Map<String, Object> object = new HttpRequest<Map<String, Object>>(url).getAsType(new TypeReference<Map<String, Object>>() {
        });


        MarsWeatherDto marsWeather = new MarsWeatherDto();
        Location inSightLandingSiteLocation = new Location(new Latitude(4.5024f), new Longitude(135.6234f));
        marsWeather.location.latitude = inSightLandingSiteLocation.getLatitude().getFloatValue();
        marsWeather.location.longitude = inSightLandingSiteLocation.getLongitude().getFloatValue();

        for (Map.Entry<String, Object> o : object.entrySet()) {
            try {
                Integer.parseInt(o.getKey());
                Map<String, Object> value = (Map<String, Object>) o.getValue();
                marsWeather.season = (String) value.get("Season");
                break;
            } catch (NumberFormatException e) {
                continue;
            }
        }
        List<Double> temperatures = new ArrayList<>();
        for (Map.Entry<String, Object> o : object.entrySet()) {
            try {
                Integer.parseInt(o.getKey());
                Map<String, Object> value = (Map<String, Object>) o.getValue();
                Map<String, Object> at = (Map<String, Object>) value.get("AT");
                double av = (Double) at.get("av");
                temperatures.add(av);
                break;
            } catch (NumberFormatException e) {
                continue;
            }
        }


        double temperatureInFahrenheit = temperatures.stream().mapToDouble(a -> a).average().getAsDouble();

        marsWeather.averageTemperatureInCelsius = (temperatureInFahrenheit - 32d) * (5 / 9d);

        return ResponseEntity.ok(marsWeather);
    }
}
