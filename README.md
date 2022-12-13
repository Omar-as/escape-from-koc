# Escape From Koç

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