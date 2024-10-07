// package com.kaushal.av.demo.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;


// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Autowired
//     private UserDetailsService userDetailService;

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         // to disable the csrf, now we do not need to send the csrf token
//         http.csrf(customizer -> customizer.disable());
//         // to authorize all requests -> but only this will create issue
//         // as we are not using the login details here
//         http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated());

//         // enable the form login
//         http.formLogin(Customizer.withDefaults());

//         // to enable basic auth - so that we can use in postman
//         http.httpBasic(Customizer.withDefaults());

//         // to make http stateless
//         // this will make the fromlogin stop working because it is creating new session
//         // everytime, it will work only after
//         // by commenting http.formLogin(Customizer.withDefaults());
//         http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//         return http.build();
//     }

//     @Bean
//     public AuthenticationProvider authenticationProvider() {
//         DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//         provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//         provider.setUserDetailsService(userDetailService);
//         return provider;
//     }

//     // customize our own UserDetailsService
//     // we are using in memory user details here
//     // @Bean
//     // public UserDetailsService userDetailsService() {
//     // UserDetails user1 = User
//     // .withDefaultPasswordEncoder()
//     // .username("admin")
//     // .password("admin123")
//     // .roles("USER")
//     // .build();

//     // UserDetails user2 = User
//     // .withDefaultPasswordEncoder()
//     // .username("avanish")
//     // .password("kaushal")
//     // .roles("ADMIN")
//     // .build();

//     // return new InMemoryUserDetailsManager(user1,user2);
//     // }

// }


package com.kaushal.av.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();

    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //
    // UserDetails user1 = User
    // .withDefaultPasswordEncoder()
    // .username("kiran")
    // .password("k@123")
    // .roles("USER")
    // .build();
    //
    // UserDetails user2 = User
    // .withDefaultPasswordEncoder()
    // .username("harsh")
    // .password("h@123")
    // .roles("ADMIN")
    // .build();
    // return new InMemoryUserDetailsManager(user1, user2);
    // }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // no password encoder is used
        // provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }
}