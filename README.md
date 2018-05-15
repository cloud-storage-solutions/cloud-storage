# cloud-storage
min requirements
java 8

## Modules
./mvnw clean install -P  or ./mvnw clean install -P mainbuild
 *runs:*
 cloud-worker
	command-line-interface
	commons
        
./mvnw clean install -P dev
 *runs:*
  cloud-worker
	command-line-interface
	commons
	integration-tests

./mvnw clean install -P it
 *runs:*   
  commons
	cloud-worker
	integration-tests

## license
tbd

## block diagram

![Block-diagram](/docs/imgs/block-diagram.png)
