
# Coeus Spring

Basic integration for the Coeus and Spring frameworks.

For a complete example application that uses SpringFramework and Coeus see the
__petclinic__ at [coeus-samples](http://github.com/coeusframework/coeus-samples).


## Using Spring for dependency injection in a Coeus application

To use Spring for dependency injection you must define your controllers as
__singleton__ Spring beans in a `WebApplicationContext` and mix the
`SpringSupport` trait in your `WebModule`. For example: 

	import com.coeusweb.config.WebModule
	import com.coeusweb.spring.config.SpringSupport

	class MyModule(sc: ServletConfig) extends WebModule(sc) with SpringSupport


If you are also using _class-path scanning_ you could also annotate you controllers
with the `org.springframework.stereotype.Controller` stereotype annotation.
Your controllers will then be automatically detected and registered as singleton
beans.
 

## License

Licensed under the Apache License, Version 2.0. See the LICENSE and NOTICE
files for more information.


