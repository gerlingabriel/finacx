package com.sistema.finacx.security;

import com.sistema.finacx.service.ImplementUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementUserDetails acesso;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();   // precisa dele para passar em "password"

        auth.inMemoryAuthentication()                                                           // deixa um acesso em mémora do sistema
                .withUser("teste").password(encoder.encode("teste")).roles("ADMIN");            // não vai funcionar devido ao password não é criotgrafado
        // Teste se precisar criar class para pegar autozizacao  - extends AuthorizationServerConfigurerAdapter

        auth.userDetailsService(acesso).passwordEncoder(new BCryptPasswordEncoder());            // ele fará a validação no banco de dados

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()                                                                         // começa dzendo que como será as autorizações a baixo
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()                                                        // autorizei que essa requisição qualquer posso pode fazer
                 //.antMatchers("pessoas/admin//**").hasRole("ADMIN")                                                // quando quer daixar aquela autorization somente quem tem role ADMIN
                .anyRequest().authenticated()                                                             // todos outros acessos tem que ser com acesso ao sistema
                .and().logout().logoutSuccessUrl("/index")
                //.and().httpBasic()                                                                        // sessão será com acesso básico //
                // não terá session o sistema  //                                                                                       
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and().csrf().disable();                                                                  // dispensa configuração csrf
                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());         //proteção para não acesso em token

    }

}
