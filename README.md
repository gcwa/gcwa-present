# Government of Canada Web Archive


## Introduction

The Library and Archives of Canada Act received Royal Assent on April 22, 2004. For the purposes of preservation it allows Library and Archives Canada (LAC) to collect a representative sample of Canadian websites. 


## Dependencies

In order to include the [WET-BOEW](http://wet-boew.github.io) themes, use these commands:

    git submodule init
    git submodule update
    
This will download the compiled files for Web Experience Toolkit themes `gcwu-fegc` and `gc-intranet` under `src/main/resources/static/`


## Getting Started

Configuration is in `src/main/resources/application.properties`

The app will run on port defined in the configuration file (default 8081).

An admin interface is available on `your-url.com/admin`, using the credentials defined in the configuration file.

Every modifications from the admin will be logged and will be visible in the "Audit log" in the admin.


## Developers

This is built using [Spring Boot](http://spring.io), using hibernate, thymeleaf, flywaydb. We are creating a fully executable jar (see http://docs.spring.io/spring-boot/docs/current/reference/html/deployment-install.html).

We are using WET-BOEW releases tags from: https://github.com/wet-boew/themes-dist/releases (using git submodules)

[EditorConfig](http://editorconfig.org/) is used to enforce some minimally consistent coding styles, please install the appropriate plugin for your IDE if it's not supported out of the box.