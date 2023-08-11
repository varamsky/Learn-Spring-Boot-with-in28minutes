# Learn Spring Security

### Note: For this particular project we are using Gradle-groovy as the build tool.

Spring Security provides various methods for authentication:-

- Form based authentication
    - Active by default
- Basic Authentication
    - Works well but has many flaws
    - The username and password is encoded and sent in the Authorization header but anyone can easily decode it and see
      the username and password.
    - It only sends the credentials but no information about the authorization i.e., user roles and privileges.

### CSRF

- This is basically tied to your SESSION with the help of a cookie.
- So if your REST API is STATELESS then you need not worry about CSRF.
- CSRF can be handled in a number of ways
    - Synchronizer token pattern
        - A token is created for each request
        - To make an update(POST, PUT, DELETE, etc.)  you need a CSRF from the previous request.
    - SameSite cookie(Set-Cookie: SameSite=Strict)
        - We can set this in `application.properties` as `server.servlet.session.cookie.same-site=strict`
- For our REST API we will make it __STATELESS__ and therefore, __disable CSRF altogether__.

### CORS(Cross-Origin Resource Sharing)

- Browsers don't allow AJAX calls to access resources outside current origin.
- CORS configuration allows you to configure which CORS requests are allowed. There are 2 ways to do this:
    - Global Configuration: This will be applicable for all requests in the API
        - Configure `addCorsMappings` callback method in the `WebMvcConfigurer`
    - LocalConfiguration
        - Use `@CrossOrigin` at class or method level

### Storing User Credentials

- There are various ways to store user details:
    - In Memory: For test purposes, not recommended in production.
        - Storing user details in `application.properties`
        - Storing user details with `InMemoryUserDetailsManager()`
    - Database: You can use JDBC/JPA to access the credentials
    - LDAP: Lightweight Directory Access Protocol
        - Open protocol for directory services and authentication

### Encoding vs Hashing vs Encryption

- Encoding
    - Transform data from one form to another
    - This does not use a key or password
    - This is easily reversible
    - Typically NOT used for securing data
    - Use-cases: Compression, Streaming
    - Example: Base64, Wav, MP3

Hashing: [Hashing_Password_Login_diagram](https://upload.wikimedia.org/wikipedia/commons/5/5e/CPT-Hashing-Password-Login.svg)

- Convert data into a hash(string)
- One-way process
- This is not reversible i.e., you cannot get the original data back
- Use-cases: Validate the integrity of the data
- Example: BCrypt, SCrypt
- Password storing, hashing and matching procedure:
- When we store a password in the database we don't store the original password string as it is. We store it as
  a hash.
- Now when user enters the password, the password string is hashed using the same algorithm.
- Finally, the hashed password string in the database is matched with the hashed password that the user entered.
- If they match then success login else failure.

- Encryption: [Encryption_diagram](https://upload.wikimedia.org/wikipedia/commons/7/70/Public_key_encryption_keys.svg)
    - Encoding data using a key or password
    - Not easily reversible: You need a key to decrypt
    - Example: RSA

### SpringSecurity - Storing Passwords

- Hashes like SHA-256 are no longer secure as modern systems can perform billions of hash calculations in a second.
- In case of computer algorithms, we always want fast algorithms
    - But, in case of hashing we need to use algorithms that take a lot of time as they will be easily defeated
- Recommended approach: Use adaptive one-way functions with Work Factor of 1 second
    - One-way functions means, like hashing where we cannot retrieve the original data
    - Work Factor represents how much time it takes to verify a password on your system
        - It should take at least 1 second to verify a password on your system
    - Adaptive means that you should be able to configure the complexity of your algorithm as computers are faster very
      quick!
        - For example, when using `BCryptPasswordEncoder` we have the option to change the algorithm complexity
    - Examples of good password hashing algorithms are: bcrypt, scrypt, argon2, etc.
        - The naming of the algorithm is a bit confusing. They should have been named as hashing algorithms.
        - Although they are named as encoders, they perform __hashing__.

### JWT

- JWT can be of 2 types
    - Symmetric: same key for encoding and decoding the token
    - Asymmetric: different keys(public and private for encoding and decoding the token)
- We will use Asymmetric keys
- Process of JWT with Spring
    1. Create key pair
        - We will use `java.security.KeyPairGenerator`
        - One can manually generate with `openssl` as well
    2. Create RSA key object using KeyPair
        - `com.nimbusds.jose.jwk.RSAKey`
    3. Create JWKSource(JSON Web Key Source)
        - Create JWKSet(a new JSON Web Key set) with the RSA key
        - Create JWKSource using the JWKSet
    4. Use RSA Public Key for decoding
        - `NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build()`
