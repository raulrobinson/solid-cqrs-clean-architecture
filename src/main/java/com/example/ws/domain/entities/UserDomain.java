package com.example.ws.domain.entities;

public class UserDomain {
    private final Long id;
    private final String name;
    private final String email;

    public UserDomain(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    private UserDomain(UserBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public static class UserBuilder {
        private Long id;
        private String name;
        private String email;

        public UserBuilder() {}

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDomain build() {
            return new UserDomain(this);
        }
    }
}
