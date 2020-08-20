## Project description
- **API first approach** - Web Controllers and DTO objects are generate from RAML (`src/main/resources/api.raml`) during build 
- For this exercise (small application I skipped full JUnit tests) I created only Integration tests which covers 100% classes and 69% code lines
- Sample requests can be found in `src/test/test.http`
- Due to limited time I skipped Service layer DTO objects. So I'm mapping Entities in controllers what is very bad.

## Build
mvc clean install

java -jar target\social-0.0.1-SNAPSHOT.jar