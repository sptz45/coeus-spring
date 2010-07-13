
# Coeus Spring

Basic integration for the Coeus and Spring frameworks.


## Using Spring for dependency injection in a Coeus application

To use Spring for dependency injection you have to:

1. Define your Coeus controllers in a Spring `WebApplicationContext`.
2. Configure Coeus to use `SpringControllerFactory` for creating the controller
   instances from the Spring application context.
3. Use `SpringRegistrar` to register the controllers in Coeus    

If you are using classpath scanning you could also annotate you controllers
with the `com.coeusweb.spring.mvc.Controller` meta-annotation so that your
controllers can be detected in classpath scanning and also automatically
registered as prototype beans.
 

## License

Licensed under the Apache License, Version 2.0. See the LICENSE and NOTICE
files for more information.


