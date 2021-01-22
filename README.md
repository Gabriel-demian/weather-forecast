# SOLAR SYSTEM WEATHER FORECAST

 
Ejercicio: [Examen ML Sistema Solar](https://github.com/Gabriel-demian/weather-forecast/blob/main/Examen%20ML%20Sistema%20Solar.pdf)

#URL Swagger
Se utiliza Swagger para la documentación de la API  \
http://localhost:8080/swagger-ui.html#!


Se podrá consultar el pronóstico del día de la siguiente forma: \
Curl -X GET --header 'Accept: application/json' 'http://localhost:8080/forecast?day=998' \
Request URL http://localhost:8080/forecast?day=998 \
```
Response Body
{
  "day": 998,
  "weather": "RAIN",
  "perimeter": 5671.592180487045
}

```

Para solicitar un reporte informando el día de mayor perímetro (por lo tanto el día de mayor cantidad de lluvias) y cuantos periodos de cada tipo de clima se tuvo en los 10 años. \
Curl -X GET --header 'Accept: application/json' 'http://localhost:8080/forecast-report' \
Request URL http://localhost:8080/forecast-report \
```
{
  "maxPerimeterDay": 3528,
  "forecastsPeriods": {
    "DROUGHT": 41,
    "RAIN": 82,
    "HEAVY_RAIN": 1,
    "NONE": 82
  }
}

```

 ## Datos a considerar 
  - Para correr el proyecto localmente hay que instalar el pluggin de Lombok y levantarlo con el perfil 'test' (spring_profiles_active=test como variable de entorno) en caso de querer probarlo con la base de datos en memoria. Y el perfil 'dev' (spring_profiles_active=dev) para una base de datos local (mysql) realizando las configuraciones pertinentes en application-dev.properties \
  - La carga de datos a la base se realiza cuando la aplicación levanta.
  - En el día 0 del día solar los planetas se encuentran alineados en el punto más alto de la órbita.
  - La alineación se realiza cuando el triángulo tienen área igual 0.
  - El sol esta contenido dentro del triángulo, si el área del mismo es igual a la suma de las áreas de los 3 triángulos que se pueden formar entre el punto del sol y los 3 planetas. 
  - Para calcular el día máximo de lluvia una vez cargados todos los datos se revisa en la base de datos el día con mayor perímetro, se modifica la tabla informando el clima de "HEAVY_RAIN" y luego se informa. 







