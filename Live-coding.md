### Before Starting : 
- Fermer tous les conteneurs docker
- Désactiver github copilot
- Lancer le cli redoc
- Ouvrir les onglets : 
  - `:8080/v3/api-docs` et collapse les trucs
  - `:8080/swagger-ui/index.html` swagger
  - `:8081` Redoc



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
- Cacher les réponse 400 en rajouter @Hidden sur le Controller advice
- Rajouter une réponse sur le endpoint de create :
```kotlin
@ApiResponse(
  responseCode = "400",
  description = "Bad Request",
  content = [Content(
    schema = Schema(implementation = BadRequestErrorDto::class),
    examples = [
      ExampleObject(name = "user not found", value = """{errorCode: USER_MUST_BE16, message: "User must be 16 years old"}"""),
      ExampleObject(name = "bar", value = """{errorCode: FO, message: "User must be 16 years old"}"""),
    ]
  )],
)
fun foo()
  ```
- Puis y'a plus qu'à faire pareil pour conflict et not found est on est bien ! Ou pas...
- L'idéal serait une annotation (la live coder) à utiliser : 
```kotlin
annotation class ErrorApiResponse(
  val badRequestErrorCodes: Array<ErrorCode> = [],
)
```
- Puis faire pareil avec les autres errorCodes > **nfe**
- Rajouter sur le controllers 
  - Create
    - _badRequest_ : `USER_MUST_BE_16_OR_OLDER`
    - _conflict_ : `EMAIL_ALREADY_TAKEN`, `COMPANY_NOT_UNIQUE`
  - byId
    - _notFound_ : `USER_NOT_FOUND`

#### Customizer itself
- path `presentation/documentation/ErrorResponseCustomizer`
- Implémenter `GlobalOperationCustomizer`
- Parler des arguments de l'interface
- sous méthode > **cust**

WARNING : Tout ce qu'on fait => Uniquement de la documentation. Ça ne change rien au code


### SIDE QUESTS 

#### Auth 
```kotlin
@OpenAPIDefinition(
    //info is required
    info = Info(title = "SpringDoc Talk", version = "1.0"),
    security = [SecurityRequirement(name = SECURITY_SCHEME)],
)
@SecurityScheme(
    name = SECURITY_SCHEME,
    type = SecuritySchemeType.OAUTH2,
    flows = OAuthFlows(
        implicit = OAuthFlow(
            authorizationUrl = "https://dev-qbztblpx2ly1bvr8.eu.auth0.com/oauth/authorize",
            tokenUrl = "https://dev-qbztblpx2ly1bvr8.eu.auth0.com/oauth/token",
            scopes = [OAuthScope(name = "openid", description = "OpenID")]
        ),
    ),
)
class OpenApiConfig
```
