To run redocly preview :

```
redocly preview-docs doc.json -p 8081
```

To run open-api diff : 
```
docker run -v $(pwd)/doc_old.json:/old.json -v $(pwd)/doc.json:/new.json -v $(pwd)/changes:/changes openapitools/openapi-diff:latest /old.json /new.json --markdown /changes/changes.md
```
