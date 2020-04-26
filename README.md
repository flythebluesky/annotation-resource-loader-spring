# annotation-resource-loader-spring

An example of how to create a custom annotation that loads a Java resource into a String.

# Example

Resource file located at:

```/src/main/resources/query.sql```

To load the query.sql into a String, apply the annotation as follows:

```$xslt
@Service
public class ExampleService {

  @StringResourceLoader("classpath:query.sql")
  public String sqlQuery;

  ...

}
```

# Requirements

Install Java 8, then build and run with gradle:

```$xslt
./gradlew clean bootRun
```

The webserver calls ExampleService and shows the contents of the string that was loaded from the file resource:

```$xslt
http://localhost:8080
```