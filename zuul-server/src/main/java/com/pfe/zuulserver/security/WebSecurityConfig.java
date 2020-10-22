package com.pfe.zuulserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
                .cors().and()
                // dont authenticate this particular request
                .authorizeRequests().antMatchers("/authenticate","/entites/**", "/organisations/**", "/thematiques**",
                "/microservice-users/users/findByUsername/**",
                "/microservice-users/users/reset",
                "/microservice-users/users/findByUsernameOrEmail/**",
                "/microservice-users/users/signup",
                "/microservice-users/confirmation/**",
                "/microservice-users/reset-password/**",
                "/microservice-offers/offres/getAll", "/microservice-entites/entites/getAll",
                "/microservice-offers/offres/getOffreById/**",
                "/microservice-offers/offres/getAllPaginate",
                "/microservice-offers/offres/getAllNotEnded",
                "/microservice-offers/offres/getAllNotEndedBefore/**",
                "/microservice-offers/offres/getAllByDescription/**",
                "/microservice-offers/offres/getAllByTag/**",
                "/microservice-offers/offres/getAllByTagPaginate/**").permitAll()

                .antMatchers("/microservice-users/users/addPhoto/**").authenticated()
                .antMatchers("/microservice-users/users/update-password/**").authenticated()
                .antMatchers("/microservice-users/candidats/findById/**").authenticated()
                //les autorisations de candidat
                .antMatchers("/microservice-users/candidats/update/**").hasAuthority("CANDIDAT")


                //les autorisations de manager
                .antMatchers("/microservice-users/managers/**").hasAuthority("MANAGER")
                //.antMatchers("/microservice-cv/cvs/getOne/**").hasAuthority("MANAGER")
                .antMatchers("/microservice-cv/cvs/getAll").hasAuthority("MANAGER")
                .antMatchers("/microservice-cv/cvs/getAllPaginate").hasAuthority("MANAGER")
                .antMatchers("/microservice-cv/cvs/getByTagPaginate/**").hasAuthority("MANAGER")
                .antMatchers("/microservice-cv/cvs/getByTag/**").hasAuthority("MANAGER")
                //.antMatchers("/microservice-cv/cvs/getByCandidat/**").hasAuthority("MANAGER")
                //.antMatchers("/microservice-cv/cvs/getByCandidatPaginate/**").hasAuthority("MANAGER")
                .antMatchers("/microservice-cv/cvs/delete/**").hasAuthority("MANAGER")
                .antMatchers("/microservice-offers/competences/**").hasAuthority("MANAGER")
                .antMatchers("/microservice-offers/organismes/**").hasAuthority("MANAGER")
                .antMatchers("/microservice-offers/offres/**").hasAuthority("MANAGER")
                .antMatchers("/microservice-offers/candidatures/**").hasAuthority("MANAGER")

                //autorisé l'admin de faire tous
                .antMatchers("/microservice-users/**").hasAuthority("ADMIN")
                .antMatchers("/microservice-cv/**").hasAuthority("ADMIN")
                .antMatchers("/microservice-offers/**").hasAuthority("ADMIN")
                .antMatchers("/microservice-entites/**").hasAuthority("ADMIN")
                // all other requests need to be authenticated
                .anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new
                UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }*/
}
