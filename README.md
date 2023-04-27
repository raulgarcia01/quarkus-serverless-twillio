# quarkus-serverless-twilio
Example how to use quarkus and AWS Lambda serverless using Twilio cloud provider to send SMS to US phone numbers.

Please replace the following information

- "REPLACE_ACCOUNT_SID"
- "REPLACE_AUTH_TOKEN"

Located in MessageService class using your twilio account and credentials.

On addition replace the following line in **aplication.properties**:

- quarkus.mongodb.connection-string = [YOUR_MONGODB_CONNECTION_HERE]

Using your MongoDB credentials.

To execute the project use the following commands:

Execute dev environment ->
> quarkus dev

Execute test environment ->
> quarkus test

Build native ->

> quarkus build --native --no-tests -Dquarkus.native.container-build=true

f you are building on a non-Linux system, you will need to also pass in a property instructing quarkus to use a Docker build as Amazon Lambda requires Linux binaries. You can do this by passing -Dquarkus.native.container-build=true to your build command. This requires you to have Docker installed locally, however.

Executable lambda locally with sam local ->

> sam local start-api --template target/sam.native.yaml

Deploy to AWS Lambda ->

> sam deploy -t target/sam.native.yaml -g

Useful documentation about Quarkus and Lamdda ->

https://quarkus.io/guides/amazon-lambda-http
