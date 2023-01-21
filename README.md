# Escape From Koç

## Dependencies

This project needs the following library dependencies:

- [`Gson v2.10.1`](https://github.com/google/gson/releases/tag/gson-parent-2.10.1): Convert Java objects into JSON and back.
- [`MongoDB Java Driver v3.12.5`](https://mvnrepository.com/artifact/org.mongodb/mongo-java-driver/3.12.5): Use MongoDB from within Java.

## Features

### Secure Login

Multiple accounts can be created, so that each account can have its separate
data.

Account passwords are not saved directly to disk. We save a hash of the password
instead. This protects account passwords even if the attacker gets read access
to the database. Moreover, we use a random salt with each password, to make
dictionary attacks harder. Also, responding to correct and incorrect passwords
take the same time, to prevent side channel attacks like timing attacks.

The `SHA` family of hash functions is easy to compute, so brute-force attacks are
easy to carry, especially with modern GPUs. We use the `PBKDF2WithHmacSHA512`
hashing algorithm that is more secure against brute-force attacks as it's harder
to compute.

Example entries in the account database:

| Username | Salt                                         | Hash                                         |
|----------|----------------------------------------------|----------------------------------------------|
| xyz      | 5_362eOJ0oy93mcw1o1E01bzv6K9JYV7uD9vLwYcLgE  | SHrmP_3BYqL0njmhyEkEo3v9O7G3m03g7niki3WvMpM  |
| abc      | 9XkrtLHEhvCSAteyyc4gQ9zrS7XWpbpzARWckbKXjoU  | mT3Gvj6PYxhyNBnbQnXvhPgBw_oaN_rHF4IACslVxBk  |
| admin    | Ty9XVhQrxVP6XJsfRZzGDH0TGebaf8AWK2-6nY2LsZs  | qOe_yN8RGfTPuPA588JIz2wKL3Qu62ToJdyugBN3PP0  |

### Config Directory

For this feature we created the `ConfigManager` class; which is 
used to handle writing to and reading data from the config file.

the date is stored in a file placed in the `.config` file that is stored
in the home directory; and that is because we are following the XDG
convention to store configuration files.

## License

After we finish COMP302, we want to open this project by making this repository
public. For that, we chose to license our project under the **GNU General Public
License v3.0**, or **GPL-3.0** for short.

We chose GPL-3.0 because it's very permissive. It allows people to distribute
and modify the code, even for commercial or private use. However, it does not
allow people to distribute the project in a closed-source manner, or under a
different license. It also does not provide a warranty of any sort.