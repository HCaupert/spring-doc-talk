### Before Starting : 
- Fermer tous les conteneurs docker
- Désactiver github copilot
- Lancer le cli redoc
- Ouvrir les onglets : 
  - `:8080/v3/api-docs` et collapse les trucs
  - `:8080/swagger-ui/index.html` swagger
  - `:8081` Redoc
- Préparer la commande openApiDiff


### LVL 1 -> 2

#### Premières annots
- On aimerait bien : 
  - Renommer le `external-user-controller`
  - Renommer les endpoints
- path `presentation/controller/external/ExternalUserController`
- Les trois objets principaux avec lesquels on interagit:
  - _OpenAPI_ > La documentation entière (titre, version, operations)
  - _Operation_ > Un endpoint (url, method, request, response, schema)
  - _Schema_ > Une définition d'objet (composé ou "primitif")
- Renommer le premier endpoint cmd P `@Operation(summary = "Create a user")`. Montrer toutes les possibilités. Il y a plein d'options qui sont auto remplies, mais on peut override.
- Faire les deux autres endpoints avec **oid** et **oall**

- Passer au controller, on peut pas mettre de `@Operation`, par contre beaucoup des paramètres ont leur propre annotation directe
- Rajouter le `@Tag`. Name et Description

####
- Changer l'application.yml et rajouter :
```yml
springdoc:
  default-produces-media-type: application/json
```

#### OpenApiGroupsConfiguration
- Créer la classe
- path `prensentation/documentation/OpenApiGroupsConfiguration`
- _@Configuration_

#### GroupedOpenApi
- path `prensentation/documentation/OpenApiGroupsConfiguration`
- Deux _@Bean_ de type _GroupedOpenApi_ à créer avec **goa**. _external_ et _internal_
- Penser à changer le `UserServiceImpl.cheatOpenApi` et rajouter `/external`
- `cmd + F9`
- Montrer Swagger

### LVL 2 -> 3

L'objectif est maintenant d'avoir une doc qui représente notre business !

#### Schema 
Séparons-nous de tout ce qui est "_dto_"
- Endpoint de list : 
  - Rajouter `@ParameterObject` sur l'argument du endpoint all.
- Endpoint create, rajouter un `@Schema` sur 
  - `PersonCreateExternalRequestDto` > `@Schema(title = Person)`
  - Parler de name vs title.
  - `CompanyCreateExternalRequestDto` > **sco**
- Endpoint get: 
  - Soucis avec le polymorphisme
  - Rajouter `@Schema(oneOf = [xx])` sur `UserResponseDto`
    - `PersonCreateExternalRequestDto` > **scp**
    - `CompanyCreateExternalRequestDto` > **sco**


#### Customizer Intro
- Parler des réponses 400
  - C'est bizarre d'avoir des 400 sur un list
  - Un not found sur un create
  - Les error code etc..
- Cacher les réponse 40X en rajouter @Hidden sur le Controller advice

##### WARNING : Tout ce qu'on fait => Uniquement de la documentation. Ça ne change rien au code


- Rajouter une réponse sur le endpoint de create :
- **> apir**

- Puis y'a plus qu'à faire pareil pour conflict et not found et on est bien ! Ou pas...
- Puis faire pareil avec les autres errorCodes > **nfe**
- Rajouter sur le controllers 
  - Create
    - _badRequest_ : `USER_MUST_BE_16_OR_OLDER`
    - _conflict_ : `EMAIL_ALREADY_TAKEN`, `COMPANY_NOT_UNIQUE`
  - byId
    - _notFound_ : `USER_NOT_FOUND`


#### Customizer itself

### SIDE QUESTS 

#### Auth 
- Dans la group config
- **> oad**
- **> ssc**
- Montrer que les annots c'est pas forcément pertinent -> openApiCustomizer
